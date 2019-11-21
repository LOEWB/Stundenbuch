package model;

import controller.Controller;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class Fetching implements Runnable {

    private Controller controller;
    private WatchService watcher = null;
    private WatchKey watchKey ;
    private boolean fetchingBool = true;


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

                // This key is registered only
                // for ENTRY_CREATE events,
                // but an OVERFLOW event can
                // occur regardless if events
                // are lost or discarded.
                System.out.println("event kind : " + kind);
                if (kind == OVERFLOW) {
                    continue;
                }

                // The filename is the
                // context of the event.
                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();

                // Verify that the new
                //  file is a text file.
                try {
                    // Resolve the filename against the directory.
                    // If the filename is "test" and the directory is "foo",
                    // the resolved name is "test/foo".
                    Path child = dir.resolve(filename);
                    System.out.println("type : " + Files.probeContentType(child));
                    if (!Files.probeContentType(child).equals("application/xml")) {
                        System.err.format("file '%s'" +
                                " is not an xml file.%n", filename);
                        continue;
                    }
                } catch (IOException x) {
                    System.err.println(x);
                    continue;
                }

                System.out.format("new file in dir : %s%n", filename);
            }

            // Reset the key -- this step is critical if you want to
            // receive further watch events.  If the key is no longer valid,
            // the directory is inaccessible so exit the loop.
            boolean valid = this.watchKey.reset();
            if (!valid) {
                break;
            }
        }
    }

    @Override
    public void run() {
        startFetching();
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
