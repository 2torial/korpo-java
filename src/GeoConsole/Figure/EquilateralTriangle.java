package GeoConsole.Figure;

import java.util.Objects;

//trojkat rownoboczny
public class EquilateralTriangle extends Triangle{
    double side;

    {
        type = "equilateral";
    }

    public EquilateralTriangle(double sideValue, double heightValue, double areaValue){
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
        throwIfZero(area, perimeter, A, B, C, height, side);
        throwIfNaN(area, perimeter, A, B, C, height, side);
    }

    @Override
    public Circle getCircumcircle(){
        return new Circle( A * Math.sqrt(3) / 3.0, -1, -1);
    }

    @Override
    public EquilateralTriangle doubleSelf() {
        return new EquilateralTriangle(A*Math.sqrt(2), height*Math.sqrt(2), -1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquilateralTriangle that = (EquilateralTriangle) o;
        return Double.compare(side, that.side) == 0;
    }
}
