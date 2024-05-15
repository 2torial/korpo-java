package GeoConsole.UserInput.Context;

import GeoConsole.Figure.Figure;

import java.util.*;

public class Context {
    private static final Map<Integer, Figure> figures = new HashMap<>();
    private static final LinkedList<Integer> removedIds = new LinkedList<>();

    public static void addFigure(Figure f) {
        int id = removedIds.isEmpty() ? figures.size()+1 : removedIds.poll();
        f.setId(id);
        figures.put(id, f);
    }

    public static void removeFigure(int figureId){
        if (!figures.containsKey(figureId))
            throw new IllegalArgumentException("Could not find specified figure");
        var fig = figures.get(figureId);
        figures.remove(fig.getId());
        removedIds.add(fig.getId());
    }

    public static Figure findFigure(int idValue) {
        var figure = figures.get(idValue);
        if (figure == null)
            throw new IllegalArgumentException(String.format("There is no figure with Id=%s", idValue));
        return figure;
    }

    public static List<Figure> getFigureList() {
        return new LinkedList<>(figures.values());
    }
}
