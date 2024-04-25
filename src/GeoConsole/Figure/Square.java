package GeoConsole.Figure;

public class Square extends Figure {
    double side, diagonal;

    public Square(double sideValue, double diagonalValue, double areaValue) {
        if (sideValue <= 0.0 && diagonalValue <= 0.0 && areaValue < 0.0)
            throw new IllegalArgumentException("An argument (side/diagonal/area) has to be greater than 0");
        if (sideValue > 0.0) {
            side = sideValue;
            diagonal = side * Math.sqrt(2.0);
            area = side * side;
            perimeter = 4.0 * side;
        }
        else if (diagonalValue > 0.0) {
            diagonal = diagonalValue;
            side = diagonal / Math.sqrt(2.0);
            area = diagonal * diagonal / 2.0;
            perimeter = 4.0 * side;
        }
        else if (areaValue > 0.0) {
            area = areaValue;
            side = Math.sqrt(area);
            diagonal = Math.sqrt(area * 2.0);
            perimeter = 4.0 * side;
        }
        throwIfZero(area, perimeter, side, diagonal);
        throwIfNaN(area, perimeter, side, diagonal);
    }

    @Override
    public Circle getCircumcircle(){
        return new Circle(diagonal / 2.0, -1, -1 );
    }

    @Override
    public void print() {
        System.out.printf("[ID:%d] Square:\n\tside: %f\n\tdiagonal: %f\n\tarea: %f\n\tperimeter: %f\n",
                id, side, diagonal, area, perimeter);
    }

    @Override
    public Square doubleSelf() {
        return new Square(side * 2, -1, -1);
    }
}
