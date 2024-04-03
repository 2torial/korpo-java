package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Attribute;
import GeoConsole.Figure.EquilateralTriangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.*;

public class TriangleCommand extends Command {
    @Override
    public String getName() {
        return "triangle";
    }

    @Override
    public String getHelp() {
        return "\tDeclares equilateral triangle by given parameter (side, height or area)";
    }

    @Override
    public int getNumberOfArguments() {
        return -1;
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
        EquilateralTriangle triangle = new EquilateralTriangle();
        switch (actions[0]) {
            case Attribute.SIDE -> triangle.setSide(arguments[0].getNumericValue());
            case Attribute.HEIGHT -> triangle.setHeight(arguments[0].getNumericValue());
            case Attribute.AREA -> triangle.setArea(arguments[0].getNumericValue());
            default -> throw new IllegalStateException("Unspecified argument at position 1");
        }

        triangle.print();
        Context.addFigure(triangle);
    }
}
