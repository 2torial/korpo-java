package GeoConsole.Figure;

public class AnyTriangle extends Triangle {
    public AnyTriangle(double a, double b, double c) {
        if(a <= 0.0 || b <= 0.0 || c <= 0.0)
            throw new IllegalArgumentException("Side lengths must be greater than 0");
        A = a; B = b; C = c;
        perimeter = A+B+C;
        double s = perimeter/2.0;
        area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
        height = 2.0*area/A;
        throwIfZero(area, perimeter, A, B, C, height);
        throwIfNaN(area, perimeter, A, B, C, height);
    }

    @Override
    public Figure doubleSelf() {
        return new AnyTriangle(A*2, B*2, C*2);
    }
}