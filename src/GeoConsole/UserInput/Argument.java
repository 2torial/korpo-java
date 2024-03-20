package GeoConsole.UserInput;

import java.util.function.Consumer;

public class Argument {
    private String value;
    public final int position;
    public final boolean isParameter;

    private int expectedArguments = 0;
    private Consumer<Argument[]> handler = args -> {};
    private Runnable finalizer = () -> {};

    public Argument(String value, int position, boolean isParameter) {
        this.value = value;
        this.position = position;
        this.isParameter = isParameter;
    }

    public Argument supplyValue(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Argument value cannot be empty");
        this.value = value;
        return this;
    }

    public Argument supplyExpectations(int expectedArguments) {
        if (expectedArguments < 0)
            throw new IllegalArgumentException("Number of expected arguments cannot be negative");
        this.expectedArguments = expectedArguments;
        return this;
    }

    public Argument supplyHandler(Consumer<Argument[]> handler) {
        if (handler == null)
            throw new IllegalArgumentException("Handler cannot be null");
        this.handler = handler;
        return this;
    }

    public void supplyFinalizer(Runnable finalizer) {
        if (finalizer == null)
            throw new IllegalArgumentException("Finalizer cannot be null");
        this.finalizer = finalizer;
    }

    public String getValue() {
        return value;
    }

    public int getExpectedArguments() {
        if (!isParameter)
            throw new IllegalArgumentException("Only parameters support expected arguments");
        return expectedArguments;
    }

    public Consumer<Argument[]> getHandler() {
        if (!isParameter)
            throw new IllegalArgumentException("Only parameters support handler");
        return handler;
    }

    public Runnable getFinalizer() {
        if (!isParameter)
            throw new IllegalArgumentException("Only parameters support finalizers");
        return finalizer;
    }

    @Override
    public String toString() {
        return isParameter ? "-"+value : value;
    }

    public boolean hasValue(String value) {
        return this.value.equals(value);
    }
}
