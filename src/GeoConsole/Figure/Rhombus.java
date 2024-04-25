package GeoConsole.Figure;

public class Rhombus extends Figure {
    double side, diagonalA, diagonalB;

    public Rhombus(double sideValue, double diagonalAValue, double diagonalBValue, double areaValue) {
        if (sideValue <= 0.0 && diagonalAValue <= 0.0  && diagonalBValue <= 0.0 && areaValue < 0.0)
            throw new IllegalArgumentException("An argument (side/diagonal/area) has to be greater than 0");
        if (diagonalAValue > 0 && diagonalBValue > 0) {
            diagonalA = diagonalAValue;
            diagonalB = diagonalBValue;
            side = Math.sqrt(diagonalA * diagonalA + diagonalB * diagonalB) / 2;
        } else if (diagonalAValue > 0 && sideValue > 0) {
            diagonalA = diagonalAValue;
            side = sideValue;
            diagonalB = Math.sqrt(4 * side * side - diagonalA * diagonalA) / 2;
        } else if (diagonalAValue > 0 && areaValue > 0) {
            diagonalA = diagonalAValue;
            area = areaValue;
            diagonalB = area / diagonalA * 2;
            side = Math.sqrt(diagonalA * diagonalA + diagonalB * diagonalB) / 2;
        } else if (sideValue > 0 && areaValue > 0) {
            side = sideValue;
            area = areaValue;
            diagonalA = Math.sqrt(2 * side * side + 2 * Math.sqrt(Math.pow(side, 4) - Math.pow(area, 2)));
            diagonalB = area * 2 / diagonalA;
        }
        area = (area > 0) ? area : diagonalA * diagonalB / 2;
        perimeter = 4.0 * side;
        throwIfZero(area, perimeter, side, diagonalA, diagonalB);
        throwIfNaN(area, perimeter, side, diagonalA, diagonalB);
    }

    public void print() {
        System.out.printf("[ID:%d] Rhombus:\n\tside: %ff\n\tdiagonals: %f x %f\n\tarea: %f\n\tperimeter: %f\n",
                id, side, diagonalA, diagonalB, area, perimeter);
    }

    @Override
    public Circle getCircumcircle(){
        double biggerDiag = Math.max(diagonalA, diagonalB);
        return new Circle((1.0 /( 2.0 * area )) * side * side * biggerDiag, -1, -1);
    }

    @Override
    public Rhombus doubleSelf() {
        return new Rhombus(-1, diagonalA * 2, diagonalB * 2, -1);
    }
}
