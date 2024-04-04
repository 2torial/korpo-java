package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Attribute;
import GeoConsole.Figure.EquilateralTriangle;
import GeoConsole.Figure.IsoscelesTriangle;
import GeoConsole.Figure.Triangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class IsoscelesTriangleCommand extends Command {
    @Override
    public String getName() {
        return "isoscelestriangle";
    }

    @Override
    public String getHelp() {
        return "\tDeclares equilateral triangle by given parameter (side, height or area)";
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    Attribute[] actions = {Attribute.NIL, Attribute.NIL};
    int parameterPosition = 1;
    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "base" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> actions[pos-1] = Attribute.BASE);
            case "side" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> actions[pos-1] = Attribute.SIDE);
            case "height" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> actions[pos-1] = Attribute.HEIGHT);
            case "area" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> actions[pos-1] = Attribute.AREA);
            default -> super.supplyParameter(argument);
        }
        parameterPosition++;
    }

    @Override
    protected void handle(Argument[] arguments) {
        double side = -1, base = -1, area = -1, height = -1;
        for (int i = 0; i < 2; i++) {
            switch (actions[i]) {
                case Attribute.SIDE -> side = arguments[i].getNumericValue();
                case Attribute.BASE -> base = arguments[i].getNumericValue();
                case Attribute.HEIGHT -> height = arguments[i].getNumericValue();
                case Attribute.AREA -> area = arguments[i].getNumericValue();
                default -> throw new IllegalStateException("Unspecified argument at position " + (i+1));
            }
        }
        IsoscelesTriangle triangle = new IsoscelesTriangle(side, base, area, height);
        triangle.print();
        Context.addFigure(triangle);
    }
}
