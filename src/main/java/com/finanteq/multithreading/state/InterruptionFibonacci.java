package com.finanteq.multithreading.state;

import java.util.Scanner;

public class InterruptionFibonacci {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.println("To stop Fibonacci sequence generator, type STOP or just S.");

        var fibThread = new Thread(InterruptionFibonacci::generateFib);
        fibThread.start();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().toUpperCase();
            if (line.startsWith("S")) {
                System.out.println("Generator will stop after next result.");
                fibThread.interrupt();
                break;
            }
        }

    }

    private static void generateFib() {
        long start = System.currentTimeMillis();
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (Thread.interrupted()) {
                // don't do any more calculations
                break;
            }
            System.out.println("fib(" + i + ") = " + fib(i));
        }
        long end = System.currentTimeMillis();
        long elapsed = end - start;
        System.out.println("Thank you for using Fibonacci sequence generator.");
        System.out.println("The generator was running for " + elapsed + "ms.");
    }

    // recursive implementation of Fibonacci's sequence
    private static long fib(long n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
