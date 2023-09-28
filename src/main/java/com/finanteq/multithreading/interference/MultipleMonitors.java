package com.finanteq.multithreading.interference;

import java.time.LocalDateTime;
import java.util.Random;

public class MultipleMonitors {
    public static void main(String[] args) {
        final var waiting = new SynchronizedWaiting();

        var thread1 = new Thread(() -> {
            waiting.waitFor(500);
            System.out.println("Thread 1 finished at " + LocalDateTime.now());
        });

        var thread2 = new Thread(() -> {
            waiting.doSomethingForOneSecond();
            System.out.println("Thread 2 finished at " + LocalDateTime.now());
        });

        var thread3 = new Thread(() -> {
            System.out.println("Random number: " + waiting.getRandomNumber());
            System.out.println("Thread 3 finished at " + LocalDateTime.now());
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class SynchronizedWaiting {
    private final Object waitMonitor = new Object();
    private final Object otherMonitor = new Object();

    private final Random random = new Random();

    public void waitFor(int ms) {
        synchronized (waitMonitor) {
            sleep(ms);
        }
    }

    public void doSomethingForOneSecond() {
        synchronized (waitMonitor) {
            // pretend we're working
            sleep(1000);
        }
    }

    public int getRandomNumber() {
        synchronized (otherMonitor) {
            // pretend we're rolling the dice for our random number
            sleep(500);
            return random.nextInt();
        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}