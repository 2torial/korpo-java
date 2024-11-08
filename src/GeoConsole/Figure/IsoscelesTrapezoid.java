package GeoConsole.Figure;

import GeoConsole.UserInput.Context.Pair;
import GeoConsole.UserInput.Context.Translator.*;

public class IsoscelesTrapezoid extends Figure{
    static {
        Translator.save(Lang.PL, Identifier.FIG_TRAPEZOID_ISOSCELES_DESCRIPTION,
                "[ID:%d] Trapez równoramienny, a:%f, b:%f, bok: %f, pole: %f, obwód: %f\n");
        Translator.save(Lang.EN, Identifier.FIG_TRAPEZOID_ISOSCELES_DESCRIPTION,
                "[ID:%d] Isosceles Trapezoid, a:%f, b:%f, side: %f, area: %f, perimeter: %f\n");
    }

    public double a, b, side, height;

    public IsoscelesTrapezoid(double aValue, double bValue, double sideValue, double heightValue, double areaValue)
    {
        checkForPositives(3, aValue, bValue, sideValue, heightValue, areaValue);

        a = aValue; b = bValue; side = sideValue; height = heightValue; area = areaValue;

        if (a > 0.0 && b > 0.0 && side > 0.0) {
            height = Math.sqrt(4 * side * side - (a - b) * (a - b)) / 2.0;
        }
        else if (a > 0.0 && b > 0.0 && height > 0.0) {
            side = Math.sqrt( (a - b) * (a - b) * 0.25 + height * height );
        }
        else if (a > 0.0 && b > 0.0 && area > 0.0) {
            height = 2.0 * area / (a + b);
            side = Math.sqrt( (a - b) * (a - b) * 0.25 + height * height );
        }
        else if (a > 0.0 && side > 0.0 && height > 0.0) {
            b = a - 2.0 * Math.sqrt(side * side - height * height);
        }
        else if (a > 0.0 && side > 0.0 && area > 0.0) {
            //TODO
        }
        else if (a > 0.0 && height > 0.0 && area > 0.0) {
            b = (2.0 * area / height) - a;
            side = Math.sqrt( (a - b) * (a - b) * 0.25 + height * height );
        }
        else if (b > 0.0 && side > 0.0 && height > 0.0) {
            a = b + 2.0 * Math.sqrt(side * side - height * height);
        }
        else if (b > 0.0 && side > 0.0 && area > 0.0) {
            //TODO
        }
        else if (b > 0.0 && height > 0.0 && area > 0.0) {
            a = (2.0 * area / height) - b;
            side = Math.sqrt( (a - b) * (a - b) * 0.25 + height * height );
        }
        else if (side > 0.0 && height > 0.0 && area > 0.0) {
            a = area / height + Math.sqrt(side * side - height * height);
            b = (2.0 * area) / height - a;
        }

        if( area <= 0.0 )
            area = (a + b) * height * 0.5;
        perimeter = a + b + side * 2.0;

        throwIfZero(area, perimeter, a, b, height, side);
        throwIfNaN(area, perimeter, a, b, height, side);
    }

    @Override
    public Circle getCircumcircle(){
        double radius = side * Math.sqrt( (a * b + side * side) / (4.0 * side * side - (a - b) * (a - b)) );
        return new Circle(radius, -1, -1 );
    }

    @Override
    public String getDescription(int roundTo) {
        return stringRounded(Translator.read(Identifier.FIG_TRAPEZOID_ISOSCELES_DESCRIPTION),
                roundTo, id, a, b, side, area, perimeter);
    }

    @Override
    public Figure doubleSelf() {
        return new IsoscelesTrapezoid(a*Math.sqrt(2), b*Math.sqrt(2), -1, height*Math.sqrt(2), -1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IsoscelesTrapezoid that = (IsoscelesTrapezoid) o;
        return roundedComparator.compare(a, that.a) == 0 && roundedComparator.compare(b, that.b) == 0;
    }

    @Override
    public Pair<Figure, Class<? extends Figure>> simplify() {
        if (roundedComparator.compare(a, b) == 0) {
            if (roundedComparator.compare(a, height) == 0)
                return new Pair<>(new Square(a, height, -1), Square.class);
            return new Pair<>(new Rectangle(a, height, -1, -1), Rectangle.class);
        }
        return null;
    }
}
