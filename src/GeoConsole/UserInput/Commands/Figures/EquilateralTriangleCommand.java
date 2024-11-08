package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.EquilateralTriangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;
import GeoConsole.UserInput.Exceptions.*;

public class EquilateralTriangleCommand extends FigureCommand {
    static {
        Translator.save(Lang.PL, Identifier.COM_EQUILATERALTRIANGLE_DESCRIPTION,
                "\tDeklaruje trójkąt równoboczny z podaną cechą (bok, wysokość, pole)");
        Translator.save(Lang.EN, Identifier.COM_EQUILATERALTRIANGLE_DESCRIPTION,
                "\tDeclares equilateral triangle by given parameter (side, height or area)");
    }

    @Override
    public String getName() {
        return "equilateraltriangle";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_EQUILATERALTRIANGLE_DESCRIPTION);
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    ArgumentsHandler handler = new ArgumentsHandler(1);
    double side = -1, area = -1, height = -1;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(1).setName("side/height/area")
                .supplyHandler(pos -> handler.supply(pos, arg -> side = arg.getNumericValue()));
            case "height" -> argument.enforceRelativePosition(1).setName("side/height/area")
                .supplyHandler(pos -> handler.supply(pos, arg -> height = arg.getNumericValue()));
            case "area" -> argument.enforceRelativePosition(1).setName("side/height/area")
                .supplyHandler(pos -> handler.supply(pos, arg -> area = arg.getNumericValue()));
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);
        EquilateralTriangle triangle = new EquilateralTriangle(side, height, area);
        updateContext(triangle);
    }
}
