package GeoConsole.Figure;

import java.util.Date;

public abstract class Figure {
    double area, perimeter;
    int id;


    private final Date dateOfCreation = new Date();

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    protected final void throwIfNaN(double ...attributes) {
        for (var attr : attributes)
            if (Double.isNaN(attr))
                throw new IllegalStateException("Given figure does not exist");
    }

    protected final void throwIfZero(double ...attributes) {
        for (var attr : attributes)
            if (attr == 0)
                throw new IllegalStateException("Given figure does not exist");
    }

    protected final void checkForPositives(int n, double ...parameters) {
        if (n < 0)
            throw new IllegalArgumentException("N must be greater than or equal to 0");
        for (var param : parameters) {
            if (param > 0)
                n--;
            if (n < 0)
                throw new IllegalStateException("Passed too many positive parameters");
        }
        if (n > 0)
            throw new IllegalStateException("Passed too little positive parameters");
    }

    protected double round(int n, double value) {
        double rounder = Math.pow(10, n);
        return Math.round(value * rounder) / rounder;
    }

    protected void printfRounded(String msg, int n, Object ...args) {
        if (n >= 0) {
            for (int i = 0; i < args.length; i++) {
                var arg = args[i];
                if (arg instanceof Double)
                    args[i] = round(n, (Double)arg);
                else if (arg instanceof Float)
                    args[i] = round(n, (Float)arg);
            }
        }
        System.out.printf(msg, args);
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setId(int value) { id = value; }
    //public int getId() { return id; }

    public abstract Circle getCircumcircle();

    public abstract void print(int roundTo);

    public abstract Figure doubleSelf();
}
