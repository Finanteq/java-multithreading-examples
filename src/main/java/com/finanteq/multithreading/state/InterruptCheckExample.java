package com.finanteq.multithreading.state;

import java.time.LocalDateTime;

public class InterruptCheckExample {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(InterruptCheckExample::doSomethingAndSleep);
        thread.start();
        System.out.println(thread.getState());
        thread.interrupt(); // interrupt when it's busy

        Thread.sleep(120);
        System.out.println(thread.getState());
        thread.interrupt(); // interrupt when it's sleeping
    }

    private static void doSomethingAndSleep() {
        long start = System.currentTimeMillis();
        long end = start + 100;
        while (System.currentTimeMillis() < end) {
            if (Thread.interrupted()) {
                System.out.println("Why do you interrupt my work?");
            }
        }

        try {
            System.out.println("Sleep started at " + LocalDateTime.now());
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted - sleep was shorter than 100 ms.");
        } finally {
            System.out.println("Sleep finished at " + LocalDateTime.now());
        }
    }
}
