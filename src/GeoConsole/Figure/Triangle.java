package GeoConsole.Figure;

import GeoConsole.UserInput.Context.Pair;
import GeoConsole.UserInput.Context.Translator.*;

public abstract class Triangle extends Figure {
    static {
        Translator.save(Lang.PL, Identifier.FIG_TRIANGLE_BASE_TYPE,
                "nieokreślony");
        Translator.save(Lang.EN, Identifier.FIG_TRIANGLE_BASE_TYPE,
                "unspecified");

        Translator.save(Lang.PL, Identifier.FIG_TRIANGLE_BASE_DESCRIPTION,
                "[ID:%d] Trójkąt (%s): A: %f, B: %f, C: %f, wysokość: %f, pole: %f, obwód: %f\n");
        Translator.save(Lang.EN, Identifier.FIG_TRIANGLE_BASE_DESCRIPTION,
                "[ID:%d] Triangle of type %s: A: %f, B: %f, C: %f, height: %f, area: %f, perimeter: %f\n");

        Translator.save(Lang.PL, Identifier.ERR_TRIANGLE_ARGUMENT,
                "Argument (bok/wysokość/pole) musi być większy 0");
        Translator.save(Lang.EN, Identifier.ERR_TRIANGLE_ARGUMENT,
                "An argument (side/height/area) has to be greater than 0");
    }

    double A, B, C, height; //height on A
    String type = Translator.read(Identifier.FIG_TRIANGLE_BASE_TYPE);

    @Override
    public Circle getCircumcircle(){
        return new Circle((A*B*C)/Math.sqrt((A+B+C)*(B+C-A)*(C+A-B)*(A+B-C)), -1, -1);
    }

    @Override
    public String getDescription(int roundTo) {
        return stringRounded(Translator.read(Identifier.FIG_TRIANGLE_BASE_DESCRIPTION),
                roundTo, id, type, A, B, C, height, area, perimeter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return roundedComparator.compare(A, triangle.A) == 0 && roundedComparator.compare(height, triangle.height) == 0;
    }

    @Override
    public Pair<Figure, Class<? extends Figure>> simplify() {
        if (roundedComparator.compare(A, B) == 0 && roundedComparator.compare(B, C) == 0)
            return new Pair<>(new EquilateralTriangle(A, -1, -1), EquilateralTriangle.class);
        if (roundedComparator.compare(A*A + B*B, C*C) == 0)
            return new Pair<>(new RightTriangle(A, B, -1, -1), RightTriangle.class);
        if (roundedComparator.compare(A*A + C*C, B*B) == 0)
            return new Pair<>(new RightTriangle(A, C, -1, -1), RightTriangle.class);
        if (roundedComparator.compare(B*B + C*C, A*A) == 0)
            return new Pair<>(new RightTriangle(B, C, -1, -1), RightTriangle.class);
        if (roundedComparator.compare(A, B) == 0)
            return new Pair<>(new IsoscelesTriangle(A, C, -1, -1), IsoscelesTriangle.class);
        if (roundedComparator.compare(A, C) == 0)
            return new Pair<>(new IsoscelesTriangle(A, B, -1, -1), IsoscelesTriangle.class);
        if (roundedComparator.compare(B, C) == 0)
            return new Pair<>(new IsoscelesTriangle(B, A, -1, -1), IsoscelesTriangle.class);
        return null;
    }
}