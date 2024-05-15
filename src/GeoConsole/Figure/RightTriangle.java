package GeoConsole.Figure;

public class RightTriangle extends Triangle {
    {
        type = "right-angled";
    }

    public RightTriangle(double adjoiningAValue, double adjoiningBValue, double hypotenuseValue, double areaValue) {
        if (adjoiningAValue < 0 && adjoiningBValue < 0 && hypotenuseValue < 0 && areaValue < 0)
            throw new IllegalArgumentException("An argument (side/height/area) has to be greater than 0");
        if (adjoiningAValue > 0) {
            A = adjoiningAValue;
            if (adjoiningBValue > 0) {
                B = adjoiningBValue;
                C = Math.sqrt(A*A + B*B);
            }
            if (hypotenuseValue > 0) {
                C = hypotenuseValue;
                B = Math.sqrt(C*C - A*A);
            }
            if (areaValue > 0) {
                B = 2 * areaValue / A;
                C = Math.sqrt(A*A + B*B);
                area = areaValue;
            }
        } else if (hypotenuseValue > 0 && areaValue > 0) {
            C = hypotenuseValue;
            area = areaValue;
            B = Math.sqrt((C*C + Math.sqrt(Math.pow(C, 4) - 16*A*A)) / 2);
            A = 2 * area / B;
        }
        height = B;
        area = area > 0 ? area : 0.5 * A * height;
        perimeter = A + B + C;
        throwIfZero(area, perimeter, A, B, C, height);
        throwIfNaN(area, perimeter, A, B, C, height);
    }
    @Override
    public Circle getCircumcircle(){
        return new Circle(-1, -1, C/2);
    }

    @Override
    public Figure doubleSelf() {
        return new RightTriangle(A*Math.sqrt(2), B*Math.sqrt(2), -1, -1);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
