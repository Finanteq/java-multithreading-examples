package com.finanteq.multithreading.state;

public class StateInspection {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(StateInspection::someJob);
        System.out.println(thread.getState());

        thread.start();
        System.out.println(thread.getState());

        Thread.sleep(100); // wait until while loop ends
        System.out.println(thread.getState());

        thread.join(); // wait until thread finishes
        System.out.println(thread.getState());
    }

    private static void someJob() {
        // Thread is RUNNABLE, let it run for 100 ms
        long start = System.currentTimeMillis();
        long end = start + 100;
        while (System.currentTimeMillis() < end) {
            // pretend we're doing something for 100 ms
        }

        // let it sleep
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // do nothing
        }
    }
}
