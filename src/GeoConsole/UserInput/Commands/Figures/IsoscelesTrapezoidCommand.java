package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.IsoscelesTrapezoid;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class IsoscelesTrapezoidCommand extends FigureCommand{
    @Override
    public String getName() {
        return "isoscelestrapezoid";
    }

    @Override
    public String getHelp() {
        return "\tDeclares isosceles trapezoid by given parameter (side, height or area)";
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    ArgumentsHandler handler = new ArgumentsHandler(3);
    double a = -1, b = -1, side = -1, area = -1, height = -1;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "a" -> argument.enforceRelativePosition(1).setName("a/b/side/height/area")
                    .supplyHandler(pos -> handler.supply(pos, arg -> a = arg.getNumericValue()));
            case "b" -> argument.enforceRelativePosition(1).setName("a/b/side/height/area")
                    .supplyHandler(pos -> handler.supply(pos, arg -> b = arg.getNumericValue()));
            case "side" -> argument.enforceRelativePosition(1).setName("a/b/side/height/area")
                    .supplyHandler(pos -> handler.supply(pos, arg -> side = arg.getNumericValue()));
            case "height" -> argument.enforceRelativePosition(1).setName("a/b/side/height/area")
                    .supplyHandler(pos -> handler.supply(pos, arg -> height = arg.getNumericValue()));
            case "area" -> argument.enforceRelativePosition(1).setName("a/b/side/height/area")
                    .supplyHandler(pos -> handler.supply(pos, arg -> area = arg.getNumericValue()));
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);
        IsoscelesTrapezoid trapezoid = new IsoscelesTrapezoid(a, b, side, height, area);
        updateContext(trapezoid);
        trapezoid.print(roundTo);
    }
}
