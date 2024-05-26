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

        name = "Circle";
    }

    public Circle getCircumcircle() {
        return new Circle( radius, -1, -1 );
    }

    @Override
    public String getDescription(int roundTo) {
        return stringRounded("[ID:%d] Circle: radius: %f, area: %f, perimeter: %f\n", roundTo, id, radius, area, perimeter);
    }

    @Override
    public Figure doubleSelf() {
        return new Circle(radius*Math.sqrt(2), -1, -1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return roundedComparator.compare(radius, circle.radius) == 0;
    }
}
