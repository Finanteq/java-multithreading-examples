package com.finanteq.multithreading.interference;

import java.util.Scanner;

public class WrappedStringInThreads {
    public static void main(String[] args) throws InterruptedException {
        var scanner = new Scanner(System.in);
        System.out.println("Enter string to display in another thread:");
        var stringWrapper = new StringWrapper(scanner.nextLine());

        var thread = new Thread(() -> {
            stringWrapper.setString("The string: " + stringWrapper.getString());
            System.out.println(stringWrapper.getString());
        });

        thread.start();
        thread.join();

        System.out.println(stringWrapper.getString());
    }
}

class StringWrapper {
    private String string;

    public StringWrapper(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
