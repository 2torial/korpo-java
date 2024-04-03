package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Attribute;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.Figure.Square;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.*;

public class SquareCommand extends Command {
    @Override
    public String getName() {
        return "square";
    }

    @Override
    public String getHelp() {
        return "\tDeclares square by given parameter (side, diagonal or area)";
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    Square square = new Square();
    Attribute action = Attribute.NIL;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(1)
                .supplyHandler(() -> action = Attribute.SIDE);
            case "diagonal" -> argument.enforceRelativePosition(1)
                .supplyHandler(() -> action = Attribute.DIAGONAL);
            case "area" -> argument.enforceRelativePosition(1)
                .supplyHandler(() -> action = Attribute.AREA);
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        switch (action) {
            case Attribute.SIDE -> square.setSide(arguments[0].getNumericValue());
            case Attribute.DIAGONAL -> square.setDiagonal(arguments[0].getNumericValue());
            case Attribute.AREA -> square.setArea(arguments[0].getNumericValue());
            default -> throw new IllegalStateException("Unspecified argument at position 1");
        }
        square.print();
        Context.addFigure(square);
    }
}
