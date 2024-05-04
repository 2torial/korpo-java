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

    double sideA = -1, sideB = -1, diagonal = -1, area = -1;
    ArgumentsHandler handler = new ArgumentsHandler(2);
    int parameterPosition = 1;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(parameterPosition).allowDuplicates(1)
                .supplyHandler(pos -> handler.supply(pos, arg -> {
                    if (sideA < 0)
                        sideA = arg.getNumericValue();
                    else
                        sideB = arg.getNumericValue();
                }));
            case "diag", "diagonal" -> argument.setName("diagonal")
                .enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> diagonal = arg.getNumericValue()));
            case "area" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> area = arg.getNumericValue()));
            default -> super.supplyParameter(argument);
        }
        parameterPosition++;
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);
        Rectangle rectangle = new Rectangle(sideA, sideB, diagonal, area);
        updateContext(rectangle);
        rectangle.print(roundTo);
    }
}
