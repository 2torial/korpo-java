package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.IsoscelesTriangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class IsoscelesTriangleCommand extends FigureCommand {
    static {
        Translator.save(Lang.PL, Identifier.COM_ISOSCELESTRIANGLE_DESCRIPTION,
                "\tDeklaruje trójkąt róœnoramienny z podanych dwóch cech (bok, wysokość, pole)");
        Translator.save(Lang.EN, Identifier.COM_ISOSCELESTRIANGLE_DESCRIPTION,
                "\tDeclares equilateral triangle by given 2 parameters (side, height or area)");
    }

    @Override
    public String getName() {
        return "isoscelestriangle";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_ISOSCELESTRIANGLE_DESCRIPTION);
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    ArgumentsHandler handler = new ArgumentsHandler(2);
    int parameterPosition = 1;
    double side = -1, base = -1, area = -1, height = -1;
    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "base" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> base = arg.getNumericValue()));
            case "side" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> side = arg.getNumericValue()));
            case "height" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> height = arg.getNumericValue()));
            case "area" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> area = arg.getNumericValue()));
            default -> super.supplyParameter(argument);
        }
        parameterPosition++;
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);
        IsoscelesTriangle triangle = new IsoscelesTriangle(side, base, area, height);
        updateContext(triangle);
    }
}
