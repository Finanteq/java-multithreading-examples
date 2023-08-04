package com.finanteq.multithreading.filewatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileWatcherExtended {
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

                Thread existingThread = threads.get(fileName);
                // If a thread ended its work, then it will not remove itself.
                // We need to check if thread in the map is still working. If it is, then don't create a new one.
                if (existingThread != null && existingThread.isAlive()) {
                    System.out.println("File " + fileName + " is already being watched.");
                } else {
                    Thread thread = new FileWatchThread(fileName);
                    thread.start();

                    threads.put(fileName, thread);
                }
            } else if (line.startsWith("STOP")) {
                // remove "STOP" prefix from user input
                String fileName = line.substring("STOP ".length());
                Thread thread = threads.get(fileName);
                if (thread != null) {
                    if (thread.isAlive()) {
                        thread.interrupt(); // only working threads should be interrupted
                    }
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