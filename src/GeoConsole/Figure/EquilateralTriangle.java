package GeoConsole.Figure;

//trojkat rownoboczny
public class EquilateralTriangle extends Triangle{
    double side;

    public EquilateralTriangle(){
        type = "equilateral";
    }
    @Override
    public double getArea() {
        return area;
    }
    public final void setSide(double value){
        side = value;
        height = value * Math.sqrt(3.0) / 2.0;
        area = value * value * Math.sqrt(3.0) / 4.0;
        A = side;
        B = side;
        C = side;
    }

    public final void setDiagonal(double value){
        side = value * 2.0 / Math.sqrt(3.0);
        height = value;
        area = value * value * Math.sqrt(3.0) / 3.0;
        A = side;
        B = side;
        C = side;
    }

    public final void setArea(double value){
        side = Math.sqrt(value * 4.0 / Math.sqrt(3.0));
        height = side * Math.sqrt(3.0) / 2.0;
        area = value;
        A = side;
        B = side;
        C = side;
    }
}
