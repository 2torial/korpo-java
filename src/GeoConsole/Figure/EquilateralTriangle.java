package GeoConsole.Figure;

//trojkat rownoboczny
public class EquilateralTriangle extends Triangle{
    double side;

    public EquilateralTriangle(double sidevalue, double heightvalue, double areavalue){
        if(sidevalue < 0.0 && heightvalue < 0.0 && areavalue < 0.0)
            throw new IllegalArgumentException("An argument (side/height/area) has to be greater than 0");
        if( sidevalue > 0.0 ) {
            side = sidevalue;
            height = side * Math.sqrt(3.0) / 2.0;
            area = side * side * Math.sqrt(3.0) / 4.0;
        }
        if( heightvalue > 0.0 ) {
            height = heightvalue;
            side = height * 2.0 / Math.sqrt(3.0);
            area = height * height * Math.sqrt(3.0) / 3.0;
        }
        if( areavalue > 0.0 ) {
            area = areavalue;
            side = Math.sqrt(area * 4.0 / Math.sqrt(3.0));
            height = side * Math.sqrt(3.0) / 2.0;
        }
        A = side; B = side; C = side;
        type = "equilateral";
    }

    @Override
    public Circle getCircumcircle(){
        Circle circleDescribedAround = new Circle( A * Math.sqrt(3) / 3.0, -1, -1);
        return circleDescribedAround;
    }
}
