package GeoConsole.Figure;

public class Circle extends Figure {
    double radius;

    public Circle(double radiusValue, double areaValue, double perimeterValue) {
        if( radiusValue <= 0.0 && areaValue <= 0.0 && perimeterValue <= 0.0 )
            throw new IllegalArgumentException("An argument (radius/area/perimeter) has to be greater than 0");
        if( radiusValue > 0.0 ) {
            radius = radiusValue;
            area = Math.PI * radius * radius;
            perimeter = Math.PI * 2.0 * radius;
        }
        else if( areaValue > 0.0 ) {
            area = areaValue;
            radius = Math.sqrt( area / (Math.PI) );
            perimeter = Math.PI * 2.0 * radius;
        }
        else if( perimeterValue > 0.0 ) {
            perimeter = perimeterValue;
            radius = perimeter / (2.0 * Math.PI);
            area = Math.PI * radius * radius;
        }
        throwIfZero(area, perimeter, radius);
        throwIfNaN(area, perimeter, radius);
    }

    public Circle getCircumcircle() {
        return new Circle( radius, -1, -1 );
    }

    public void print(int roundTo) {
        printfRounded("[ID:%d] Circle:\n\tradius: %f,\n\tarea: %f,\n\tperimeter: %f\n", roundTo, id, radius, area, perimeter);
    }

    @Override
    public Figure doubleSelf() {
        return new Circle(radius * 2, -1, -1);
    }
}
