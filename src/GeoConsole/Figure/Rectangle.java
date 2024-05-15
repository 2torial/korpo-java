package GeoConsole.Figure;

import GeoConsole.UserInput.Context.Pair;

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
    @Override
    public String getDescription(int roundTo) {
        return stringRounded("[ID:%d] Rectangle: side: %f x %f, diagonal: %f, area: %f, perimeter: %f\n",
                roundTo, id, sideA, sideB, diagonal, area, perimeter);
    }

    @Override
    public Circle getCircumcircle(){
        return new Circle(diagonal / 2.0, -1, -1);
    }

    @Override
    public Pair<Figure, Class<? extends Figure>> doubleSelf() {
        return new Pair<>(
            new Rectangle(
                sideA*Math.sqrt(2),
                sideB*Math.sqrt(2),
                -1,
                -1),
            Rectangle.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return roundedComparator.compare(sideA, rectangle.sideA) == 0 && roundedComparator.compare(sideB, rectangle.sideB) == 0;
    }
}