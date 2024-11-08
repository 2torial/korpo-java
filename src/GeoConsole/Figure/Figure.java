package GeoConsole.Figure;

import GeoConsole.UserInput.Context.Pair;
import GeoConsole.UserInput.Context.Translator.*;

import java.util.Comparator;
import java.util.Date;

public abstract class Figure {
    static {
        Translator.save(Lang.PL, Identifier.ERR_UNREACHABLE,
                "Niemożliwy stan");
        Translator.save(Lang.EN, Identifier.ERR_UNREACHABLE,
                "Unreachable state");

        Translator.save(Lang.PL, Identifier.ERR_FIGURE_NOT_EXISTS,
                "Dana figura nie istnieje");
        Translator.save(Lang.EN, Identifier.ERR_FIGURE_NOT_EXISTS,
                "Given figure does not exist");

        Translator.save(Lang.PL, Identifier.ERR_N_GREATER,
                "N musi być większe od 0");
        Translator.save(Lang.EN, Identifier.ERR_N_GREATER,
                "N must be greater than or equal to 0");

        Translator.save(Lang.PL, Identifier.ERR_POSITIVE_TOO_MANY,
                "Wprowadzono za dużo dodatnich parametrów");
        Translator.save(Lang.EN, Identifier.ERR_POSITIVE_TOO_MANY,
                "Passed too many positive parameters");

        Translator.save(Lang.PL, Identifier.ERR_POSITIVE_TOO_LITTLE,
                "Wprowadzono za mało dodatnich parametrów");
        Translator.save(Lang.EN, Identifier.ERR_POSITIVE_TOO_LITTLE,
                "Passed too little positive parameterów");
    }

    double area, perimeter;
    int id;

    private final Date dateOfCreation = new Date();

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    protected final void throwIfNaN(double ...attributes) {
        for (var attr : attributes)
            if (Double.isNaN(attr))
                throw new IllegalStateException(Translator.read(Identifier.ERR_FIGURE_NOT_EXISTS));
    }

    protected final void throwIfZero(double ...attributes) {
        for (var attr : attributes)
            if (attr == 0)
                throw new IllegalStateException(Translator.read(Identifier.ERR_FIGURE_NOT_EXISTS));
    }

    protected final void checkForPositives(int n, double ...parameters) {
        if (n < 0)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_N_GREATER));
        for (var param : parameters) {
            if (param > 0)
                n--;
            if (n < 0)
                throw new IllegalStateException(Translator.read(Identifier.ERR_POSITIVE_TOO_MANY));
        }
        if (n > 0)
            throw new IllegalStateException(Translator.read(Identifier.ERR_POSITIVE_TOO_LITTLE));
    }

    protected transient Comparator<Double> roundedComparator =
        (a, b) -> Double.compare(round(10, a), round(10, b));

    protected double round(int n, double value) {
        double rounder = Math.pow(10, n);
        return Math.round(value * rounder) / rounder;
    }

    protected String stringRounded(String msg, int n, Object ...args) {
        String replacement = "%." + String.format("%d", n) + "f";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(msg);

        while( stringBuilder.indexOf("%f") != -1 )
        {
            int index = stringBuilder.indexOf("%f");
            stringBuilder.replace(index, index + 2, replacement);
        }

        return String.format(stringBuilder.toString(), args);
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setId(int value) { id = value; }
    public int getId() { return id; }

    public abstract Circle getCircumcircle();

    public abstract String getDescription(int roundTo);

    public void print(int roundTo) {
        System.out.print(getDescription(roundTo));
    }

    public abstract Figure doubleSelf();

    public Pair<Figure, Class<? extends  Figure>> simplify() {
        return null;
    }
}
