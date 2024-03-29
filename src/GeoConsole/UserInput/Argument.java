package GeoConsole.UserInput;

import GeoConsole.UserInput.Exceptions.InvalidPositionException;

import java.util.function.Consumer;

public class Argument {
    private String name;
    public final String rawValue;
    public final int absolutePosition;
    private int relativePosition;
    private int enforcedPosition = -1;
    public final boolean isParameter;

    private int allowedDuplicates = 0;
    private int expectedSubArguments = 0;
    private Consumer<Argument[]> handler = args -> {};
    private Consumer<Argument[]> finalizer = args -> {};

    public Argument(String value, int position, boolean isParameter) {
        this.name = value.toLowerCase();
        this.rawValue = value;
        this.absolutePosition = position;
        this.isParameter = isParameter;
    }

    public Argument setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Argument name cannot be empty");
        this.name = name.toLowerCase();
        return this;
    }

    public void setRelativePosition(int position) throws InvalidPositionException {
        if (enforcedPosition > 0 && enforcedPosition != position)
            throw new InvalidPositionException(position, this);
        this.relativePosition = position;
    }
    public int getRelativePosition() {
        return this.relativePosition;
    }
    public Argument enforceRelativePosition(int position) {
        if (position < 0)
            throw new IllegalArgumentException("Enforced position must be a positive number");
        enforcedPosition = position;
        return this;
    }

    public Argument allowDuplicates(int number) {
        if (!isParameter)
            throw new IllegalArgumentException("Only parameters support allowing duplication");
        if (number <= 0)
            throw new IllegalArgumentException("Number of allowed duplicate parameters must be positive");
        allowedDuplicates = number;
        return this;
    }
    public int getNumberOfAllowedDuplicates() {
        return allowedDuplicates;
    }

    public Argument supplyHandler(int numberOfSubArguments, Consumer<Argument[]> handler) {
        if (numberOfSubArguments < 0)
            throw new IllegalArgumentException("Number of expected arguments cannot be negative");
        expectedSubArguments = numberOfSubArguments;
        if (handler == null)
            throw new IllegalArgumentException("Handler cannot be null");
        this.handler = handler;
        return this;
    }
    public Argument supplyHandler(Runnable handler) {
        return supplyHandler(0, args -> handler.run());
    }

    public void supplyFinalizer(Consumer<Argument[]> finalizer) {
        if (finalizer == null)
            throw new IllegalArgumentException("Finalizer cannot be null");
        this.finalizer = finalizer;
    }

    public double getNumericValue(int roundTo) {
        if (roundTo < 0)
            throw new IllegalArgumentException("Argument roundTo must be positive or 0");
        double value = Double.parseDouble(rawValue);
        if (roundTo == 0)
            return value;
        double rounder = Math.pow(10, roundTo);
        return Math.round(value * rounder) / rounder;
    }
    public double getNumericValue() {
        return getNumericValue(0);
    }
    public int getIntegerValue() {
        return Integer.parseInt(rawValue);
    }

    public int getNumberOfSubArguments() {
        if (!isParameter)
            throw new IllegalArgumentException("Only parameters support expected arguments");
        return expectedSubArguments;
    }

    public Consumer<Argument[]> getHandler() {
        if (!isParameter)
            throw new IllegalArgumentException("Only parameters support handler");
        return handler;
    }

    public Consumer<Argument[]> getFinalizer() {
        if (!isParameter)
            throw new IllegalArgumentException("Only parameters support finalizers");
        return finalizer;
    }

    @Override
    public String toString() {
        return (isParameter ? "--" : "") + name;
    }
}
