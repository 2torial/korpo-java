package GeoConsole.Figure;

public class Rhombus extends Figure {
    public final double side, diagonalA, diagonalB;

    public Rhombus(double sideValue, double diagonalAValue, double diagonalBValue, double areaValue) {
        checkForPositives(2, sideValue, diagonalAValue, diagonalBValue, areaValue);
        if (diagonalAValue > 0 && diagonalBValue > 0) {
            diagonalA = diagonalAValue;
            diagonalB = diagonalBValue;
            side = Math.sqrt(diagonalA * diagonalA + diagonalB * diagonalB) / 2;
        } else if (diagonalAValue > 0 || diagonalBValue > 0) {
            diagonalA = diagonalAValue > 0 ? diagonalAValue : diagonalBValue;
            if (sideValue > 0) {
                side = sideValue;
                diagonalB = Math.abs(diagonalA - side * Math.sqrt(2)) < 0.0000005
                    ? diagonalA
                    : Math.sqrt(4 * side * side - diagonalA * diagonalA) / 2;
            } else if (areaValue > 0) {
                area = areaValue;
                diagonalB = area / diagonalA * 2;
                side = Math.sqrt(diagonalA * diagonalA + diagonalB * diagonalB) / 2;
            } else throw new RuntimeException("Unreachable state");
        } else if (sideValue > 0 && areaValue > 0) {
            side = sideValue;
            area = areaValue;
            diagonalA = Math.sqrt(2 * side * side + 2 * Math.sqrt(Math.pow(side, 4) - Math.pow(area, 2)));
            diagonalB = area * 2 / diagonalA;
        } else throw new RuntimeException("Unreachable state");
        area = (area > 0) ? area : diagonalA * diagonalB / 2;
        perimeter = 4.0 * sideValue;
        throwIfZero(area, perimeter, side, diagonalA, diagonalB);
        throwIfNaN(area, perimeter, side, diagonalA, diagonalB);
    }

    public void print(int roundTo) {
        System.out.println(stringRounded("[ID:%d] Rhombus:\n\tside: %ff\n\tdiagonals: %f x %f\n\tarea: %f\n\tperimeter: %f\n",
                roundTo, id, side, diagonalA, diagonalB, area, perimeter));
    }

    @Override
    public Circle getCircumcircle(){
        if (diagonalA != diagonalB)
            throw new IllegalArgumentException("To circumscribe circle on a rhombus, it must be a square");
        double biggerDiag = Math.max(diagonalA, diagonalB);
        return new Circle((1.0 /( 2.0 * area )) * side * side * biggerDiag, -1, -1);
    }

    @Override
    public Rhombus doubleSelf() {
        return new Rhombus(-1, diagonalA * 2, diagonalB * 2, -1);
    }
}
