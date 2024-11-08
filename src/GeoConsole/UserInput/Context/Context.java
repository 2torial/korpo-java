package GeoConsole.UserInput.Context;

import GeoConsole.Figure.Figure;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

import java.util.*;

public class Context {
    static {
        Translator.save(Lang.PL, Identifier.ERR_INCORRECT_FIGURE_ID,
                "Figura o Id=%s nie istnieje");
        Translator.save(Lang.EN, Identifier.ERR_INCORRECT_FIGURE_ID,
                "There is no figure with Id=%s");

        Translator.save(Lang.PL, Identifier.ERR_SPECIFIED_FIGURE_MISSING,
                "Nieodnaleziono podanej figury");
        Translator.save(Lang.EN, Identifier.ERR_SPECIFIED_FIGURE_MISSING,
                "Could not find specified figure");
    }

    private static final Map<Integer, Figure> figures = new HashMap<>();
    private static final LinkedList<Integer> removedIds = new LinkedList<>();

    public static void addFigure(Figure f) {
        int id = removedIds.isEmpty() ? figures.size()+1 : removedIds.poll();
        f.setId(id);
        figures.put(id, f);
    }

    public static void removeFigure(int figureId){
        if (!figures.containsKey(figureId))
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_SPECIFIED_FIGURE_MISSING));
        var fig = figures.get(figureId);
        figures.remove(fig.getId());
        removedIds.add(fig.getId());
    }

    public static Figure findFigure(int idValue) {
        var figure = figures.get(idValue);
        if (figure == null)
            throw new IllegalArgumentException(String.format(Translator.read(Identifier.ERR_INCORRECT_FIGURE_ID), idValue));
        return figure;
    }

    public static List<Figure> getFigureList() {
        return new LinkedList<>(figures.values());
    }
}
