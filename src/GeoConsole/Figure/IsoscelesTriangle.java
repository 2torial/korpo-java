package GeoConsole.Figure;

import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

public class IsoscelesTriangle extends Triangle {
    static {
        Translator.save(Lang.PL, Identifier.FIG_TRIANGLE_ISOSCELES_TYPE,
                "równoramienny");
        Translator.save(Lang.EN, Identifier.FIG_TRIANGLE_ISOSCELES_TYPE,
                "Isosceles");
    }

    double side; //A - base

    {
        type = Translator.read(Identifier.FIG_TRIANGLE_ISOSCELES_TYPE);
    }

    public IsoscelesTriangle(double sideValue, double baseValue, double areaValue, double heightValue) {
        if (sideValue < 0.0 && baseValue < 0.0 && heightValue < 0.0 && areaValue < 0.0)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_TRIANGLE_ARGUMENT));
        if (sideValue > 0.0 && baseValue > 0.0) {
            side = sideValue;
            A = baseValue;
            height = Math.sqrt( side * side - ((A * A) / 4.0) );
            area = 0.5 * A * height;
        }
        if (sideValue > 0.0 && areaValue > 0.0) {
            side = sideValue;
            area = areaValue;
            height = (Math.sqrt(2) * area) / Math.sqrt( side * side - Math.sqrt(side * side * side * side - 4.0 * area * area) );
            A = 2.0 * Math.sqrt( side * side - height * height );
        }
        if (sideValue > 0.0 && heightValue > 0.0) {
            side = sideValue;
            height = heightValue;
            A = 2.0 * Math.sqrt( side * side - height * height );
            area = 0.5 * A * height;
        }
        if (baseValue > 0.0 && areaValue > 0.0) {
            A = baseValue;
            area = areaValue;
            height = 2.0 * (area / A);
            side = Math.sqrt( ((A * A) / 4.0) + height * height );
        }
        if (baseValue > 0.0 && heightValue > 0.0) {
            A = baseValue;
            height = heightValue;
            area = 0.5 * height * A;
            side = Math.sqrt( ((A * A) / 4.0) + height * height );
        }
        if (areaValue > 0.0 && heightValue > 0.0) {
            area = areaValue;
            height = heightValue;
            A = 2.0 * (area / height);
            side = Math.sqrt( ((A * A) / 4.0) + height * height );
        }
        B = side;
        C = side;
        perimeter = 2.0 * side + A;

        throwIfZero(area, perimeter, A, B, C, height, side);
        throwIfNaN(area, perimeter, A, B, C, height, side);
    }
    @Override
    public Circle getCircumcircle(){
        return new Circle((1.0 /( 4.0 * area)) * A * B * C, -1, -1);
    }

    @Override
    public Figure doubleSelf() {
        return new IsoscelesTriangle(-1, A*Math.sqrt(2), -1, height*Math.sqrt(2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IsoscelesTriangle that = (IsoscelesTriangle) o;
        return roundedComparator.compare(side, that.side) == 0;
    }
}
