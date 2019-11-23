package model;

import controller.Controller;

import java.io.IOException;
import java.nio.file.*;
import java.util.Queue;

import static java.nio.file.StandardWatchEventKinds.*;

public class Fetching implements Runnable {

    private Controller controller;
    private WatchService watcher = null;
    private WatchKey watchKey ;
    private boolean fetchingBool = true;
    // Docs queue
    private Queue<String> actionPathQueue;
    private XmlHandling xmlHandler;


    public Fetching(Controller c) {
        this.controller = c;
        try {
            this.watcher = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startFetching() {
        Path dir = Paths.get("transactions_dir");
        System.out.println("fetching in : " + dir.toAbsolutePath().toString());


        try {
            this.watchKey = dir.register(watcher,
                    ENTRY_CREATE);
        } catch (IOException e) {
            System.err.println(e);
        }

        while (fetchingBool) {

            // wait for key to be signaled
            try {
                this.watchKey = watcher.take();
            } catch (Exception x) {
                System.out.println("fetching interrupted");
            }

            for (WatchEvent<?> event: this.watchKey.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();

                // Adding new file path to queue
                xmlHandler = new XmlHandling();
                xmlHandler.processXml(filename.toFile());

                System.out.format("new file in dir : %s%n", filename);
            }

            // Key reset
            boolean valid = this.watchKey.reset();
            if (!valid) {
                break;
            }
        }
    }

    private void startQueueProcessing() {
        xmlHandler = new XmlHandling();
        new Thread() {
            @Override
            public void run() {
                super.run();
            }
        }.start();
        while(fetchingBool) {

        }
    }

    @Override
    public void run() {
        startFetching();
        startQueueProcessing();
    }

    public boolean isFetchingBool() {
        return fetchingBool;
    }

    public void interrupt() {
        this.fetchingBool = false;
        try {
            this.watcher.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
