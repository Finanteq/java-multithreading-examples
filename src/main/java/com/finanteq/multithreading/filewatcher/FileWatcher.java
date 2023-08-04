package com.finanteq.multithreading.filewatcher;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileWatcher {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("FILE DELETION WATCHER");
        System.out.println("Type WATCH <file name> to watch for file deletion.");
        System.out.println("Type STOP <file name> to stop file watch.");
        System.out.println("Type END to end the program.");

        Scanner scanner = new Scanner(System.in);
        Map<String, Thread> threads = new HashMap<>();

        while(scanner.hasNext()) {
            String line = scanner.nextLine();

            if (line.startsWith("WATCH")) {
                // remove "WATCH" prefix from user input
                String fileName = line.substring("WATCH ".length());

                Thread thread = new FileWatchThread(fileName);
                thread.start();

                threads.put(fileName, thread);
            } else if (line.startsWith("STOP")) {
                // remove "STOP" prefix from user input
                String fileName = line.substring("STOP ".length());
                Thread thread = threads.get(fileName);
                if (thread != null) {
                    thread.interrupt();
                    threads.remove(fileName);
                } else {
                    System.out.println("File " + fileName + " is not watched.");
                }
            } else if (line.startsWith("END")) {
                System.out.println("Bye!");
                System.exit(0);
            } else {
                System.out.println("Unknown command: " + line);
            }
        }
    }
}

class FileWatchThread extends Thread {
    private final String fileName;

    FileWatchThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File " + file.getName() + " does not exist!");
            return;
        }

        System.out.println("Watching " + file.getName());

        while (file.exists()) {
            if (Thread.interrupted()) {
                System.out.println("File " + file.getName() + " is no longer watched.");
                return;
            }
        }
        System.out.println("File " + file.getName() + " was deleted.");
    }
}
