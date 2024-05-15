package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Circle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class CircleCommand extends FigureCommand {
    @Override
    public String getName() {
        return "circle";
    }

    @Override
    public String getHelp() {
        return "\tDeclares circle by given parameter (radius, area, perimeter)";
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    ArgumentsHandler handler = new ArgumentsHandler(1);

    double radius = -1, area = -1, perimeter = -1;
    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "radius" -> argument.enforceRelativePosition(1).setName("radius/area/perimeter")
                    .supplyHandler(pos -> handler.supply(pos, arg -> radius = arg.getNumericValue()));
            case "perimeter" -> argument.enforceRelativePosition(1).setName("radius/area/perimeter")
                    .supplyHandler(pos -> handler.supply(pos, arg -> perimeter = arg.getNumericValue()));
            case "area" -> argument.enforceRelativePosition(1).setName("radius/area/perimeter")
                    .supplyHandler(pos -> handler.supply(pos, arg -> area = arg.getNumericValue()));
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);
        Circle circle = new Circle(radius, area, perimeter);
        updateContext(circle, Circle.class);
        circle.print(roundTo);
    }
}
