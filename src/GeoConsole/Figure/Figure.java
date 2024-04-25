package GeoConsole.Figure;

import java.time.LocalDate;

public abstract class Figure {
    double area, perimeter;
    int id;

    private final LocalDate dateOfCreation = LocalDate.now();

    public String getDateOfCreation() {
        return dateOfCreation.toString();
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

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setId(int value) { id = value; }
    public int getId() { return id; }

    public abstract Circle getCircumcircle();

    public abstract void print();
}
