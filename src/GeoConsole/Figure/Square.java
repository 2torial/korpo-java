package GeoConsole.Figure;

public class Square extends Figure {
    double side, diagonal;

    public final void setSide(double value){
        if (value <= 0)
            throw new IllegalArgumentException("The side has to be greater than 0");
        side = value;
        diagonal = value * Math.sqrt(2.0);
        area = value * value;
        perimeter = 4.0 * side;
    }

    public final void setDiagonal(double value){
        if (value <= 0)
            throw new IllegalArgumentException("The diagonal has to be greater than 0");
        side = value / Math.sqrt(2.0);
        diagonal = value;
        area = value * value / 2.0;
        perimeter = 4.0 * side;
    }

    public final void setArea(double value){
        if (value <= 0)
            throw new IllegalArgumentException("The area has to be greater than 0");
        side = Math.sqrt(value);
        diagonal = Math.sqrt(value * 2.0);
        area = value;
        perimeter = 4.0 * side;
    }

    @Override
    public Circle getCircumcircle(){
        return new Circle(diagonal / 2.0, -1, -1 );
    }

    @Override
    public void print() {
        throwIfZero(side, diagonal, area);
        System.out.printf("[ID:%d] Square:\n\tside: %f\n\tdiagonal: %f\n\tarea: %f\n\tperimeter: %f\n",
                id, side, diagonal, area, perimeter);
    }
}
