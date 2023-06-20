package com.finanteq.multithreading.spawn;

public class ThreadWithNameExample {
    public static void main(String[] args) {
        Thread sampleThread = new Thread(ThreadWithNameExample::doNotStop, "Our sample thread");
        sampleThread.start();
    }

    private static void doNotStop() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted " + Thread.currentThread().getName());
            }
        }
    }
}