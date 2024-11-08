package GeoConsole.Figure;

import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

public class RightTriangle extends Triangle {
    static {
        Translator.save(Lang.PL, Identifier.FIG_TRIANGLE_RIGHT_TYPE,
                "protokątny");
        Translator.save(Lang.EN, Identifier.FIG_TRIANGLE_RIGHT_TYPE,
                "right-angled");
    }

    {
        type = Translator.read(Identifier.FIG_TRIANGLE_RIGHT_TYPE);
    }

    public RightTriangle(double adjoiningAValue, double adjoiningBValue, double hypotenuseValue, double areaValue) {
        if (adjoiningAValue < 0 && adjoiningBValue < 0 && hypotenuseValue < 0 && areaValue < 0)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_TRIANGLE_ARGUMENT));
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
