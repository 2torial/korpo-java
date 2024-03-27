package GeoConsole.Figure;

public class Square extends Figure{
    double side, diagonal;
    @Override
    public double getArea() {
        return area;
    }
    public final void setSide(double value){
        side = value;
        diagonal = value * Math.sqrt(2.0);
        area = value * value;
    }

    public final void setDiagonal(double value){
        side = value / Math.sqrt(2.0);
        diagonal = value;
        area = value * value / 2.0;
    }

    public final void setArea(double value){
        side = Math.sqrt(value);
        diagonal = Math.sqrt(value * 2.0);
        area = value;
    }

    public void print(){
        System.out.printf("Square:\n\tside: %f\n\tdiagonal: %f\n\tarea: %f\n", side, diagonal, area);
    }

}
