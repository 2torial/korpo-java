package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.UserInput.Argument;
import GeoConsole.Figure.Square;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Exceptions.*;

public class SquareCommand extends FigureCommand {
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
    ArgumentsHandler handler = new ArgumentsHandler(1);

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(1).setName("side/diag/area")
                .supplyHandler(pos -> handler.supply(pos, arg -> square.setSide(arg.getNumericValue())));
            case "diagonal" -> argument.enforceRelativePosition(1).setName("side/diag/area")
                .supplyHandler(pos -> handler.supply(pos, arg -> square.setDiagonal(arg.getNumericValue())));
            case "area" -> argument.enforceRelativePosition(1).setName("side/diag/area")
                .supplyHandler(pos -> handler.supply(pos, arg -> square.setArea(arg.getNumericValue())));
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);
        updateContext(square);
        square.print();
    }
}
