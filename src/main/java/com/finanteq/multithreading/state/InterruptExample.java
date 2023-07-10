package com.finanteq.multithreading.state;

import java.time.LocalDateTime;

public class InterruptExample {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(InterruptExample::sleepWell);
        thread.start();
        System.out.println(thread.getState());
        thread.interrupt();
    }

    private static void sleepWell() {
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
