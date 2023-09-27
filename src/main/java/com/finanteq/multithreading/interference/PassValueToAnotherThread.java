package com.finanteq.multithreading.interference;

import java.util.Scanner;

public class PassValueToAnotherThread {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("Enter string to display in another thread:");
        var string = scanner.nextLine(); // value created in main thread

        var thread = new Thread(() -> {
            System.out.println(string); // but used in a new thread
        });

        thread.start();
    }
}
