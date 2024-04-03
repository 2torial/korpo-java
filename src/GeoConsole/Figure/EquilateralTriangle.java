package GeoConsole.Figure;

//trojkat rownoboczny
public class EquilateralTriangle extends Triangle{
    double side, height, area;
    @Override
    public double getArea() {
        return area;
    }
    public final void setSide(double value){
        if (value <= 0)
            throw new IllegalArgumentException("The side has to be greater than 0");
        side = value;
        height = value * Math.sqrt(3.0) / 2.0;
        area = value * value * Math.sqrt(3.0) / 4.0;
    }

    public final void setHeight(double value){
        if (value <= 0)
            throw new IllegalArgumentException("The height has to be greater than 0");
        side = value * 2.0 / Math.sqrt(3.0);
        height = value;
        area = value * value * Math.sqrt(3.0) / 3.0;
    }

    public final void setArea(double value){
        if (value <= 0)
            throw new IllegalArgumentException("The area has to be greater than 0");
        side = Math.sqrt(value * 4.0 / Math.sqrt(3.0));
        height = side * Math.sqrt(3.0) / 2.0;
        area = value;
    }

    public void print() {
        throwIfZero(side, height, area);
        System.out.printf("Triangle:\n\tside: %f\n\theight: %f\n\tarea: %f\n", side, height, area);
    }
}
