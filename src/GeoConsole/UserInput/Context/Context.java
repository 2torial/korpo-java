package GeoConsole.UserInput.Context;

import GeoConsole.Figure.Figure;

import java.util.*;

public class Context {
    private static final Map<String, DataContainer> variables = new HashMap<>();
    private static final Map<Integer, Figure> figures = new HashMap<>();
    private static final Map<String, Integer> figureNameToIdMap = new HashMap<>();
    private static int figureCounter = 0;
    private static final LinkedList<Integer> removedIds = new LinkedList<>();

    public static <T> void declare(String variableName, T value) {
        variables.put(variableName, new Variable<>(value));
    }

    public static void addFigure(String figureName, Figure f){
        figureCounter++;
        int id = removedIds.isEmpty() ? figureCounter : removedIds.poll();
        f.setId(id);
        figureNameToIdMap.put(figureName, id);
        figures.put(id, f);
    }
    public static void addFigure(Figure f){
        addFigure(Integer.toString(removedIds.isEmpty() ? figureCounter+1 : removedIds.getFirst()), f);
    }

    public static void removeFigure(String figureName){
        Figure fig = figures.get(figureNameToIdMap.get(figureName));
        if (fig == null)
            throw new IllegalArgumentException("Could not find specified figure");
        figureNameToIdMap.remove(figureName);
        figures.remove(fig.getId());
        removedIds.add(fig.getId());
        figureCounter--;
    }

    public static Figure findFigureWithId(int idValue) {
        var figure = figures.get(idValue);
        if (figure == null)
            throw new IllegalArgumentException(String.format("There is no figure with Id=%s", idValue));
        return figure;
    }
    public static Figure findFigureWithName(String nameValue) {
        Integer id = figureNameToIdMap.get(nameValue);
        if (id == null)
            throw new IllegalArgumentException(String.format("There is no figure with Name=%s", nameValue));
        return findFigureWithId(id);
    }

    public static List<Figure> getFigureList() {
        return new LinkedList<>(figures.values());
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
