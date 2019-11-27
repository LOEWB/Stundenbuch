package model;

import controller.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import static java.nio.file.StandardWatchEventKinds.*;

public class Fetching implements Runnable {

    private Controller controller;
    private WatchService watcher = null;
    private WatchKey watchKey ;
    private boolean fetchingBool = true;
    // Docs queue
    private ArrayBlockingQueue<File> actionPathQueue;
    private XmlHandling xmlHandler;

    private final static int QUEUE_SIZE = 100;
    private final static String STOP_MARKER = "INTERRUPT_QUEUE";

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
                actionPathQueue.add(filename.toFile());
                System.out.println("File added to queue");
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

        new Thread(() -> {
            File currFile;
            System.out.println("Queue attempting for a file");
            while(fetchingBool) {
                try {
                    currFile = actionPathQueue.take();
                    System.out.println("Processing file in queue");
                    xmlHandler.processXml(currFile);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void run() {
        xmlHandler = new XmlHandling();
        actionPathQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);
        startQueueProcessing();
        startFetching();
    }

    public boolean isFetchingBool() {
        return fetchingBool;
    }

    public void interrupt() {
        this.fetchingBool = false;
        try {
            actionPathQueue.add(new File(STOP_MARKER));
            this.watcher.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
