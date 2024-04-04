package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Attribute;
import GeoConsole.Figure.EquilateralTriangle;
import GeoConsole.Figure.IsoscelesTriangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.*;

public class EquilateralTriangleCommand extends Command {
    @Override
    public String getName() {
        return "equilateraltriangle";
    }

    @Override
    public String getHelp() {
        return "\tDeclares equilateral triangle by given parameter (side, height or area)";
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    Attribute[] actions = {Attribute.NIL, Attribute.NIL};

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(1)
                .supplyHandler(pos -> actions[pos-1] = Attribute.SIDE);
            case "height" -> argument.enforceRelativePosition(1)
                .supplyHandler(pos -> actions[pos-1] = Attribute.HEIGHT);
            case "area" -> argument.enforceRelativePosition(1)
                .supplyHandler(pos -> actions[pos-1] = Attribute.AREA);
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        double side = -1, area = -1, height = -1;
        switch (actions[0]) {
            case Attribute.SIDE -> side = arguments[0].getNumericValue();
            case Attribute.HEIGHT -> height = arguments[0].getNumericValue();
            case Attribute.AREA -> area = arguments[0].getNumericValue();
            default -> throw new IllegalStateException("Unspecified argument at position 1");
        }
        EquilateralTriangle triangle = new EquilateralTriangle(side, height, area);

        triangle.print();
        Context.addFigure(triangle);
    }
}
