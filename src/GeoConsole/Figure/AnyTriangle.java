package GeoConsole.Figure;

import GeoConsole.UserInput.Context.Translator.*;

public class AnyTriangle extends Triangle {
    static {
        Translator.save(Lang.PL, Identifier.ERR_SIDE_LENGTHS,
                "Długości boków muszą być większe od 0");
        Translator.save(Lang.EN, Identifier.ERR_SIDE_LENGTHS,
                "Side lengths must be greater than 0");
    }

    public AnyTriangle(double a, double b, double c) {
        if(a <= 0.0 || b <= 0.0 || c <= 0.0)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_SIDE_LENGTHS));
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
        return new AnyTriangle(A*Math.sqrt(2), B*2*Math.sqrt(2), C*2*Math.sqrt(2));
    }
}