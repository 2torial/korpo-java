package GeoConsole.Figure;

public class Triangle extends Figure{
    double side, height;
    @Override
    public double getArea() {
        return area;
    }
    public final void setSide(double value){
        side = value;
        height = value * Math.sqrt(3.0) / 2.0;
        area = value * value * Math.sqrt(3.0) / 4.0;
    }

    public final void setDiagonal(double value){
        side = value * 2.0 / Math.sqrt(3.0);
        height = value;
        area = value * value * Math.sqrt(3.0) / 3.0;
    }

    public final void setArea(double value){
        side = Math.sqrt(value * 4.0 / Math.sqrt(3.0));
        height = side * Math.sqrt(3.0) / 2.0;
        area = value;
    }

    @Override
    public void print(){
        System.out.printf("Triangle:\n\tside: %f\n\theight: %f\n\tarea: %f\n", side, height, area);
    }

}
