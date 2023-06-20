package com.finanteq.multithreading.spawn;

public class SimpleExampleWithLambda {
    public static void main(String[] args)  {
        Thread thread = new Thread(() -> {
            System.out.println("Hello World (asynchronously)!");
        });
        thread.start();

        System.out.println("Thread name: " + thread.getName());
        System.out.println("Is Alive: " + thread.isAlive());
    }
}
