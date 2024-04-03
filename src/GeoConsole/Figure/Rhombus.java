package GeoConsole.Figure;

public class Rhombus extends Figure {
    private int propertiesUntilDefined = 2;
    double side, diagonalA, diagonalB, area;

    @Override
    public double getArea() {
        return area;
    }
    public final void setSide(double value){
        if (propertiesUntilDefined <= 0)
            throw new IllegalStateException("Rectangle is already defined");
        if (value <= 0)
            throw new IllegalArgumentException("The side has to be greater than 0");
        if (side > 0)
            throw new IllegalStateException("Side is already defined");
        side = value;
        propertiesUntilDefined--;
        if (propertiesUntilDefined == 0)
            fillData();
    }

    public final void setDiagonal(double value){
        if (propertiesUntilDefined <= 0)
            throw new IllegalStateException("Rectangle is already defined");
        if (value <= 0)
            throw new IllegalArgumentException("The diagonal has to be greater than 0");
        if (diagonalA == 0)
            diagonalA = value;
        else
            diagonalB = value;
        propertiesUntilDefined--;
        if (propertiesUntilDefined == 0)
            fillData();
    }

    public final void setArea(double value){
        if (propertiesUntilDefined <= 0)
            throw new IllegalStateException("Rectangle is already defined");
        if (value <= 0)
            throw new IllegalArgumentException("The area has to be greater than 0");
        if (area > 0)
            throw new IllegalStateException("Area is already defined");
        area = value;
        propertiesUntilDefined--;
        if (propertiesUntilDefined == 0)
            fillData();
    }

    public void print() {
        throwIfZero(side, area, diagonalA, diagonalB);
        if (propertiesUntilDefined > 0)
            throw new IllegalStateException("Figure is not fully declared");
        System.out.printf("Rhombus:\n\tside: %ff\n\tdiagonals: %f x %f\n\tarea: %f\n", side, diagonalA, diagonalB, area);
    }

    private void fillData() {
        if (diagonalA > 0 && diagonalB > 0)
            side = Math.sqrt(diagonalA * diagonalA + diagonalB * diagonalB) / 2;
        else if (diagonalA > 0 && side > 0)
            diagonalB = Math.sqrt(4 * side * side - diagonalA * diagonalA) / 2;
        else if (diagonalA > 0 && area > 0) {
            diagonalB = area / diagonalA * 2;
            side = Math.sqrt(diagonalA * diagonalA + diagonalB * diagonalB) / 2;
        } else {
            diagonalA = Math.sqrt(2 * side * side + 2 * Math.sqrt(Math.pow(side, 4) - Math.pow(area, 2)));
            diagonalB = area * 2 / diagonalA;
        }
        area = (area > 0) ? area : diagonalA * diagonalB / 2;
        throwIfNaN(side, diagonalA, diagonalB, area);
    }
}
