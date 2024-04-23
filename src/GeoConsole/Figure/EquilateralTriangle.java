package GeoConsole.Figure;

//trojkat rownoboczny
public class EquilateralTriangle extends Triangle{
    double side;

    public EquilateralTriangle(double sideValue, double heightValue, double areaValue){
        super();
        if(sideValue < 0.0 && heightValue < 0.0 && areaValue < 0.0)
            throw new IllegalArgumentException("An argument (side/height/area) has to be greater than 0");
        if( sideValue > 0.0 ) {
            side = sideValue;
            height = side * Math.sqrt(3.0) / 2.0;
            area = side * side * Math.sqrt(3.0) / 4.0;
        }
        if( heightValue > 0.0 ) {
            height = heightValue;
            side = height * 2.0 / Math.sqrt(3.0);
            area = height * height * Math.sqrt(3.0) / 3.0;
        }
        if( areaValue > 0.0 ) {
            area = areaValue;
            side = Math.sqrt(area * 4.0 / Math.sqrt(3.0));
            height = side * Math.sqrt(3.0) / 2.0;
        }
        A = side; B = side; C = side;
        perimeter = 3.0*side;
    }

    @Override
    public Circle getCircumcircle(){
        Circle circleDescribedAround = new Circle( A * Math.sqrt(3) / 3.0, -1, -1);
        return circleDescribedAround;
    }
}
