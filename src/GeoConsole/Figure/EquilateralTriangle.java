package GeoConsole.Figure;

import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

public class EquilateralTriangle extends Triangle {
    static {
        Translator.save(Lang.PL, Identifier.FIG_TRIANGLE_EQUILATERAL_TYPE,
                "równoboczny");
        Translator.save(Lang.EN, Identifier.FIG_TRIANGLE_EQUILATERAL_TYPE,
                "equilateral");
    }

    double side;

    {
        type = Translator.read(Identifier.FIG_TRIANGLE_EQUILATERAL_TYPE);
    }

    public EquilateralTriangle(double sideValue, double heightValue, double areaValue){
        if(sideValue < 0.0 && heightValue < 0.0 && areaValue < 0.0)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_TRIANGLE_ARGUMENT));
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
    public Figure doubleSelf() {
        return new EquilateralTriangle(A*Math.sqrt(2), height*Math.sqrt(2), -1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquilateralTriangle that = (EquilateralTriangle) o;
        return roundedComparator.compare(side, that.side) == 0;
    }
}
