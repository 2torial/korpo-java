package GeoConsole.Figure;

public abstract class Figure {
    double area, circumference;
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

    public double getArea() {
        return area;
    }

    public abstract Circle getCircumcircle();

    public abstract void print();
}
