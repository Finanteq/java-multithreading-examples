package com.finanteq.multithreading.state;

import java.time.LocalDateTime;

public class EarlyInterruptExample {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(EarlyInterruptExample::doSomethingAndSleep);
        thread.start();
        System.out.println(thread.getState());
//        thread.interrupt(); // interrupt when it's busy

        Thread.sleep(120);
        System.out.println(thread.getState());
        thread.interrupt(); // interrupt when it's sleeping

        thread.join();
    }

    private static void doSomethingAndSleep() {
        long start = System.currentTimeMillis();
        long end = start + 100;
        while (System.currentTimeMillis() < end) {
            // I'm busy!
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
