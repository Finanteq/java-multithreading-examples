package com.finanteq.multithreading.spawn;

public class ExceptionHandlerExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            throw new IllegalStateException("oops");
        });
        boolean[] didFail = new boolean[]{false};

        thread.setUncaughtExceptionHandler(new CustomExceptionHandler(didFail));
        thread.start();
        thread.join();

        System.out.printf("[%s] Hello World! Did our thread fail? %s%n", Thread.currentThread().getName(), didFail[0]);
    }
}

class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final boolean[] didFail;

    CustomExceptionHandler(boolean[] didFail) {
        this.didFail = didFail;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("[%s] Thread %s threw an exception (message: %s), don't bother about it.%n",
                Thread.currentThread().getName(), t.getName(), e.getMessage());
        didFail[0] = true;
    }
}
