package com.finanteq.multithreading.interference;

public class MultithreadedCounterExample {
    public static void main(String[] args) throws InterruptedException {
        final var counter = new Counter();

        var thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        var thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        var thread3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.getValue());
    }
}

class Counter {
    private int value = 0;

    public void increment() {
        synchronized (this) {
            value += 1;
        }
    }

    public int getValue() {
        return value;
    }
}