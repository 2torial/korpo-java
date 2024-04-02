package GeoConsole.Figure;

//trojkat rownoramienny
public class IsoscelesTriangle extends Triangle {
    double side; //A - base

    public IsoscelesTriangle(){
        type = "isosceles";
    }
    @Override
    public double getArea() {
        return area;
    }
    public IsoscelesTriangle(double sidevalue, double basevalue, double areavalue, double heightvalue) { //when < 0 , value not provided
        if( sidevalue > 0.0 && basevalue > 0.0){
            side = sidevalue;
            A = basevalue;
            height = Math.sqrt( side * side - ((A * A) / 4.0) );
            area = 0.5 * A * height;
        }
        if( sidevalue > 0.0 && areavalue > 0.0){
            side = sidevalue;
            area = areavalue;
            //TODO
        }
        if( sidevalue > 0.0 && heightvalue > 0.0) {
            side = sidevalue;
            height = heightvalue;
            A = 2.0 * Math.sqrt( side * side - height * height);
            area = 0.5 * A * height;
        }
        if( basevalue > 0.0 && areavalue > 0.0){
            A = basevalue;
            area = areavalue;
            height = 2.0 * (area / A);
            side = Math.sqrt( ((A * A) / 4.0) + height * height);
        }
        if( basevalue > 0.0 && heightvalue > 0.0){
            A = basevalue;
            height = heightvalue;
            area = 0.5 * height * A;
            side = Math.sqrt( ((A * A) / 4.0) + height * height);
        }
        if( areavalue > 0.0 && heightvalue > 0.0){
            area = areavalue;
            height = heightvalue;
            A = 2.0 * (area / height);
            side = Math.sqrt( ((A * A) / 4.0) + height * height);
        }
        B = side;
        C = side;
    }
}
