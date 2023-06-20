package com.finanteq.multithreading.spawn;

public class SimpleExampleWithThreadSubclass {
    public static void main(String[] args) {
        Thread thread = new CustomThread();
        thread.start();

        System.out.println("Thread name: " + thread.getName());
        System.out.println("Is Alive: " + thread.isAlive());
    }
}

class CustomThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello World (asynchronously)!");
    }

}