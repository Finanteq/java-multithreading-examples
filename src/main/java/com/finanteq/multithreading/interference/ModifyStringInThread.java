package com.finanteq.multithreading.interference;

import java.util.Scanner;

public class ModifyStringInThread {
    public static void main(String[] args) throws InterruptedException {
        var scanner = new Scanner(System.in);
        System.out.println("Enter string to display in another thread:");
        var string = scanner.nextLine();
        var thread = new PrefixingThread(string);
        thread.start();
        thread.join();
        System.out.println(string);
    }
}

class PrefixingThread extends Thread {
    private String string;

    PrefixingThread(String string) {
        this.string = string;
    }

    @Override
    public void run() {
        string = "The string: " + string;
        System.out.println(string);
    }
}