package GeoConsole.Figure;

public abstract class Figure {
    abstract double getArea();

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
}
