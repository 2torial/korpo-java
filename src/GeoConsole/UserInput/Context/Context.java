package GeoConsole.UserInput.Context;

import GeoConsole.Figure.Figure;

import java.util.*;

public class Context {
    private static final Map<String, DataContainer> variables = new HashMap<>();
    private static final List<Figure> figures = new LinkedList<>();

    public static <T> void declare(String variableName, T value) {
        variables.put(variableName, new Variable<>(value));
    }

    public static void addFigure(Figure f){
        figures.add(f);
    }

    public static List<Figure> getFigureList() {
        return figures;
    }

    public static int readInt(String variableName) {
        var variable = variables.get(variableName);
        if (variable == null)
            throw new IllegalArgumentException(String.format("Variable [%s] is not declared", variableName));
        try {
            return variable.parse(Integer.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Variable [%s] is not an integer", variableName));
        }
    }

    public static double readDouble(String variableName) {
        var variable = variables.get(variableName);
        if (variable == null)
            throw new IllegalArgumentException(String.format("Variable [%s] is not declared", variableName));
        try {
            return variable.parse(Double.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Variable [%s] is not numeric", variableName));
        }
    }

    public static String readString(String variableName) {
        var variable = variables.get(variableName);
        if (variable == null)
            throw new IllegalArgumentException(String.format("Variable [%s] is not declared", variableName));
        try {
            return variable.parse(String.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Variable [%s] is not of type string", variableName));
        }
    }

    public static Figure readFigure(String variableName) {
        var variable = variables.get(variableName);
        if (variable == null)
            throw new IllegalArgumentException(String.format("Variable [%s] is not declared", variableName));
        try {
            return variable.parse(Figure.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Variable [%s] is not of type Figure", variableName));
        }
    }
}

interface DataContainer {
    <T> T parse(Class<T> type);
}

record Variable<T>(T value) implements DataContainer {
    @Override
    public <TResult> TResult parse(Class<TResult> type) {
        return (type.cast(value));
    }
}
