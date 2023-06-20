package com.finanteq.multithreading.spawn;

public class SimpleExampleWithRunnable {
    public static void main(String[] args)  {
        Thread thread = new Thread(new AsyncRunnable());
        thread.start();

        System.out.println("Thread name: " + thread.getName());
        System.out.println("Is Alive: " + thread.isAlive());
    }
}

class AsyncRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello World (asynchronously)!");
    }
}