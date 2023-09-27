package com.finanteq.multithreading.interference;

public class AtomicOperations {
    private final String finalValue = "This value is final";

    private int intValue = 0;
    private volatile String variableString = "Some string";

    private double doubleValue = 1.0;
    private String nonVolatileString = "aaa";

    public void atomic() {
        var someNumber = this.intValue; // reading primitive variable, atomic
        intValue = 2; // writing primitive variable, atomic

        var someString = this.finalValue; // reading final reference, atomic

        var anotherString = this.variableString; // reading volatile reference, atomic
        var loremIpsum = "Lorem " + "ipsum";
        this.variableString = loremIpsum; // writing volatile reference, atomic
    }

    public void nonAtomic() {
        // Following are technically not atomic, practically can be sometimes atomic if you have a 64-bit CPU
        var someNumber = doubleValue;
        doubleValue = 4.0;

        // This string is non-volatile, JVM might cache the reference and it might not be up-to-date
        var string1 = nonVolatileString;
        var string2 = nonVolatileString;

        // There are 4 operations - read, store 2, add, write
        intValue += 2;

        // This is not atomic - we are not only storing the reference, we also call multiple methods first to create an object
        variableString = new StringBuilder("Lorem ").append("ipsum").toString();
    }
}
