package GeoConsole.Figure;

import GeoConsole.UserInput.Context.Pair;
import GeoConsole.UserInput.Context.Translator.*;

public class Rectangle extends Figure {
    static {
        Translator.save(Lang.PL, Identifier.FIG_RECTANGLE_DESCRIPTION,
                "[ID:%d] Prostokąt: bok: %f x %f, przekątna: %f, pole: %f, obwód: %f\n");
        Translator.save(Lang.EN, Identifier.FIG_RECTANGLE_DESCRIPTION,
                "[ID:%d] Rectangle: side: %f x %f, diagonal: %f, area: %f, perimeter: %f\n");

        Translator.save(Lang.PL, Identifier.ERR_RECTANGULAR_ARGUMENT,
                "Argument (bok/przekątna/pole) musi być większy 0");
        Translator.save(Lang.EN, Identifier.ERR_RECTANGULAR_ARGUMENT,
                "An argument (side/diagonal/area) has to be greater than 0");
    }

    double sideA, sideB, diagonal;

    public Rectangle(double sideAValue, double sideBValue, double diagonalValue, double areaValue) {
        if (sideAValue <= 0.0 && sideBValue <= 0.0 && diagonalValue < 0.0 && areaValue < 0.0)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_RECTANGULAR_ARGUMENT));
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
        return stringRounded(Translator.read(Identifier.FIG_RECTANGLE_DESCRIPTION),
                roundTo, id, sideA, sideB, diagonal, area, perimeter);
    }

    @Override
    public Circle getCircumcircle(){
        return new Circle(diagonal / 2.0, -1, -1);
    }

    @Override
    public Figure doubleSelf() {
        return new Rectangle(sideA*Math.sqrt(2), sideB*Math.sqrt(2), -1, -1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return roundedComparator.compare(sideA, rectangle.sideA) == 0 && roundedComparator.compare(sideB, rectangle.sideB) == 0;
    }

    @Override
    public Pair<Figure, Class<? extends Figure>> simplify() {
        if (sideA == sideB)
            return new Pair<>(new Square(sideA, -1, -1), Square.class);
        return null;
    }
}