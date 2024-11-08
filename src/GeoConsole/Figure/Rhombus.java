package GeoConsole.Figure;

import GeoConsole.UserInput.Context.Pair;
import GeoConsole.UserInput.Context.Translator.*;

public class Rhombus extends Figure {
    static {
        Translator.save(Lang.PL, Identifier.FIG_RHOMBUS_DESCRIPTION,
                "[ID:%d] Romb: bok: %f, przekątne: %f x %f, pole: %f, obwód: %f\n");
        Translator.save(Lang.EN, Identifier.FIG_RHOMBUS_DESCRIPTION,
                "[ID:%d] Rhombus: side: %f, diagonals: %f x %f, area: %f, perimeter: %f\n");

        Translator.save(Lang.PL, Identifier.ERR_RHOMBUS_CIRCUMSCRIBE,
                "Nie można opisać okręgu na rombie, który nie jest kwadratem");
        Translator.save(Lang.EN, Identifier.ERR_RHOMBUS_CIRCUMSCRIBE,
                "Cannot circumscribe a circle on a rhombus which is not a square");
    }

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
            } else throw new RuntimeException(Translator.read(Identifier.ERR_UNREACHABLE));
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

    @Override
    public String getDescription(int roundTo) {
        return stringRounded(Translator.read(Identifier.FIG_RHOMBUS_DESCRIPTION),
                roundTo, id, side, diagonalA, diagonalB, area, perimeter);
    }

    @Override
    public Circle getCircumcircle(){
        if (diagonalA != diagonalB)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_RHOMBUS_CIRCUMSCRIBE));
        double biggerDiag = Math.max(diagonalA, diagonalB);
        return new Circle((1.0 /( 2.0 * area )) * side * side * biggerDiag, -1, -1);
    }

    @Override
    public Figure doubleSelf() {
        return new Rhombus(-1, diagonalA*Math.sqrt(2), diagonalB*Math.sqrt(2), -1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rhombus rhombus = (Rhombus) o;
        return roundedComparator.compare(diagonalA, rhombus.diagonalA) == 0 && roundedComparator.compare(diagonalB, rhombus.diagonalB) == 0;
    }

    @Override
    public Pair<Figure, Class<? extends Figure>> simplify() {
        if (diagonalA == diagonalB)
            return new Pair<>(new Square(-1, diagonalA, -1), Square.class);
        return null;
    }
}
