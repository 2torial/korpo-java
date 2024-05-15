package GeoConsole.Figure;

public class Ellipse extends Figure {
    public final double axle1, axle2;

    public Ellipse(double axle1Value, double axle2Value, double areaValue) {
        checkForPositives(2, axle1Value, axle2Value, areaValue);
        if (axle1Value > 0 && axle2Value > 0) {
            axle1 = axle1Value;
            axle2 = axle2Value;
        } else if (axle1Value > 0 && areaValue > 0) {
            axle1 = axle1Value;
            area = areaValue;
            axle2 = area / Math.PI / axle1;
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
    public void print(int roundTo) {
        System.out.println(getDescription(roundTo));
    }

    @Override
    public Circle getCircumcircle(){
        return new Circle(Math.max(axle1, axle2), -1, -1);
    }

    @Override
    public Ellipse doubleSelf() {
        return new Ellipse(axle1 * Math.sqrt(2), axle2*Math.sqrt(2), -1);
    }
}
