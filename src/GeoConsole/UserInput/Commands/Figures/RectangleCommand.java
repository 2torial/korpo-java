package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Rectangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Exceptions.*;

public class RectangleCommand extends FigureCommand {
    @Override
    public String getName() {
        return "rectangle";
    }

    @Override
    public String getHelp() {
        return "\tDeclares rectangle by 2 given parameters (side, diagonal or area)\nSide can be passed twice";
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    ArgumentsHandler handler = new ArgumentsHandler(2);
    int parameterPosition = 1;
    Rectangle rectangle = new Rectangle();

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(parameterPosition).allowDuplicates(1)
                .supplyHandler(pos -> handler.supply(pos, arg -> rectangle.setSide(arg.getNumericValue())));
            case "diag", "diagonal" -> argument
                .enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> rectangle.setDiagonal(arg.getNumericValue())));
            case "area" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> rectangle.setArea(arg.getNumericValue())));
            default -> super.supplyParameter(argument);
        }
        parameterPosition++;
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);
        updateContext(rectangle);
        rectangle.print();
    }
}
