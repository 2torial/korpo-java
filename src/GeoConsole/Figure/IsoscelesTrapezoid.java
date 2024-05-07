package GeoConsole.Figure;

public class IsoscelesTrapezoid extends Figure{
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
    }

    @Override
    public Circle getCircumcircle(){
        double radius = side * Math.sqrt( (a * b + side * side) / (4.0 * side * side - (a - b) * (a - b)) );
        return new Circle(radius, -1, -1 );
    }

    @Override
    public void print(int roundTo) {
        System.out.println(stringRounded("[ID:%d] Isosceles Trapezoid:\n\tside: %f\n\tarea: %f\n\tperimeter: %f\n",
                roundTo, id, side, area, perimeter));
    }

    @Override
    public IsoscelesTrapezoid doubleSelf() { return new IsoscelesTrapezoid( a * 2, b * 2, side * 2, -1, -1 ); }

}
