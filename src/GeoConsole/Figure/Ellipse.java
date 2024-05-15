package GeoConsole.Figure;

import GeoConsole.UserInput.Context.Pair;

public class Ellipse extends Figure {
    public final double axle1, axle2;

    public Ellipse(double axle1Value, double axle2Value, double areaValue) {
        checkForPositives(2, axle1Value, axle2Value, areaValue);
        if (axle1Value > 0 && axle2Value > 0) {
            axle1 = Math.max(axle1Value, axle2Value);
            axle2 = Math.min(axle1Value, axle2Value);
        } else if (axle1Value > 0 && areaValue > 0) {
            area = areaValue;
            double axleTmp = area / Math.PI / axle1Value;
            axle1 = Math.max(axle1Value, axleTmp);
            axle2 = Math.min(axle1Value, axleTmp);
        } else throw new RuntimeException("Unreachable state");
        area = (area > 0) ? area : Math.PI * axle1 * axle2;
        perimeter = Math.PI * (3 * (axle1 + axle2) /2 - Math.sqrt(axle1 * axle2));
        throwIfZero(axle1Value, axle2Value, area, perimeter);
        throwIfNaN(axle1Value, axle2Value, area, perimeter);
    }

    @Override
    public String getDescription(int roundTo) {
        return stringRounded("[ID:%d] Ellipse: axle shafts: %f x %f, area: %f, perimeter: %f\n",
            roundTo, id, axle1, axle2, area, perimeter);
    }

    @Override
    public Circle getCircumcircle(){
        return new Circle(Math.max(axle1, axle2), -1, -1);
    }

    @Override
    public Figure doubleSelf() {
        return new Ellipse(axle1 * Math.sqrt(2), axle2*Math.sqrt(2), -1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ellipse ellipse = (Ellipse) o;
        return roundedComparator.compare(axle1, ellipse.axle1) == 0 && roundedComparator.compare(axle2, ellipse.axle2) == 0;
    }

    @Override
    public Pair<Figure, Class<? extends Figure>> simplify() {
        if (roundedComparator.compare(axle1, axle2) == 0)
            return new Pair<>(new Circle(axle1, -1, -1), Circle.class);
        return null;
    }
}
