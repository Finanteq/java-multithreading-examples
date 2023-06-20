package com.finanteq.multithreading.spawn;

public class SimpleExampleWithJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() ->
                System.out.print("Hello")
        );
        Thread thread2 = new Thread(() ->
                System.out.print(" world?")
        );
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();

        System.out.println("I hope it works");
        System.out.println("Is thread1 alive? " + thread1.isAlive());
        System.out.println("Is thread2 alive? " + thread2.isAlive());
    }
}