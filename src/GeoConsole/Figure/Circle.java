package GeoConsole.Figure;

public class Circle extends Figure {
    double radius;

    public Circle(double radiusValue, double areaValue, double circumferenceValue) {
        if( radiusValue < 0.0 && areaValue < 0.0 && circumferenceValue < 0.0 )
            throw new IllegalArgumentException("An argument (radius/area/circumference) has to be greater than 0");
        if( radiusValue > 0.0 ) {
            radius = radiusValue;
            area = Math.PI * radius * radius;
            circumference = Math.PI * 2.0 * radius;
        }
        else if( areaValue > 0.0 ) {
            area = areaValue;
            radius = Math.sqrt( area / (Math.PI) );
            circumference = Math.PI * 2.0 * radius;
        }
        else if( circumferenceValue > 0.0 ) {
            circumference = circumferenceValue;
            radius = circumference / (2.0 * Math.PI);
            area = Math.PI * radius * radius;
        }
        throwIfNaN(radius, area, circumference);
        throwIfZero(radius, area, circumference);
    }

    public void print() {
        System.out.printf("Circle:\n\tradius:%f,\n\tarea:%f,\n\tcircumference:%f\n\t", radius, area, circumference);
    }
}
