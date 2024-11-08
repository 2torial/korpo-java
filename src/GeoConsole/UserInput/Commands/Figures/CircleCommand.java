package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Circle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class CircleCommand extends FigureCommand {
    static {
        Translator.save(Lang.PL, Identifier.COM_CIRCLE_DESCRIPTION,
                "\tDeklaruje koło z podaną cechą (promień, pole, obwód)");
        Translator.save(Lang.EN, Identifier.COM_CIRCLE_DESCRIPTION,
                "\tDeclares circle by given parameter (radius, area, perimeter)");
    }

    @Override
    public String getName() {
        return "circle";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_CIRCLE_DESCRIPTION);
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
        updateContext(circle);
    }
}
