package GeoConsole.Figure;

public class Rectangle extends Figure {
    double sideA, sideB, diagonal;

    public Rectangle(double sideAValue, double sideBValue, double diagonalValue, double areaValue) {
        if (sideAValue <= 0.0 && sideBValue <= 0.0 && diagonalValue < 0.0 && areaValue < 0.0)
            throw new IllegalArgumentException("An argument (side/diagonal/area) has to be greater than 0");
        if (sideAValue > 0 && sideBValue > 0) {
            sideA = sideAValue;
            sideB = sideBValue;
            diagonal = Math.sqrt(sideA * sideA + sideB * sideB);
        } else if (sideAValue > 0 && diagonalValue > 0) {
            sideA = sideAValue;
            diagonal = diagonalValue;
            sideB = Math.sqrt(diagonal * diagonal - sideA * sideA);
        } else if (sideAValue > 0 && areaValue > 0) {
            sideA = sideAValue;
            area = areaValue;
            sideB = area / sideA;
            diagonal = Math.sqrt(sideA * sideA + sideB * sideB);
        } else if (diagonalValue > 0 && areaValue > 0){
            diagonal = diagonalValue;
            area = areaValue;
            sideA = Math.sqrt((Math.pow(diagonal, 2) + Math.sqrt(Math.pow(diagonal, 4) - 4 * Math.pow(area, 2))) / 2);
            sideB = area / sideA;
        }
        area = (area > 0) ? area : sideA * sideB;
        perimeter = (sideA + sideB) * 2.0;
        throwIfZero(area, perimeter, sideA, sideB, diagonal);
        throwIfNaN(area, perimeter, sideA, sideB, diagonal);
    }

    public void print(int roundTo) {
        System.out.println(stringRounded("[ID:%d] Rectangle:\n\tside: %f x %f\n\tdiagonal: %f\n\tarea: %f\n\tperimeter: %f\n",
                roundTo, id, sideA, sideB, diagonal, area, perimeter));
    }

    @Override
    public Circle getCircumcircle(){
        return new Circle(diagonal / 2.0, -1, -1);
    }

    @Override
    public Rectangle doubleSelf() {
        return new Rectangle(sideA * 2, sideB * 2, -1, -1);
    }
}