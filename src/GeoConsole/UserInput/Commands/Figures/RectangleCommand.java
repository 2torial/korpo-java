package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Attribute;
import GeoConsole.Figure.Rectangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.*;

public class RectangleCommand extends Command {
    @Override
    public String getName() {
        return "rectangle";
    }

    @Override
    public String getHelp() {
        return "\tDeclares rectangle ...";
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    Rectangle rectangle = new Rectangle();
    Attribute[] actions = {Attribute.NIL, Attribute.NIL};
    int parameterPosition = 1;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(parameterPosition).allowDuplicates(1)
                .supplyHandler(pos -> actions[pos-1] = Attribute.SIDE);
            case "diag", "diagonal" -> argument.setName("diagonal")
                .enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> actions[pos-1] = Attribute.DIAGONAL);
            case "area" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> actions[pos-1] = Attribute.AREA);
            default -> super.supplyParameter(argument);
        }
        parameterPosition++;
    }

    @Override
    protected void handle(Argument[] arguments) {
        for (int i = 0; i < 2; i++) {
            var action = actions[i];
            switch (action) {
                case Attribute.SIDE -> rectangle.setSide(arguments[i].getNumericValue());
                case Attribute.DIAGONAL -> rectangle.setDiagonal(arguments[i].getNumericValue());
                case Attribute.AREA -> rectangle.setArea(arguments[i].getNumericValue());
                default -> throw new IllegalStateException("Unspecified argument at position: " + (i+1));
            }
        }
        rectangle.print();
        Context.addFigure(rectangle);
    }
}
