package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.IsoscelesTrapezoid;
import GeoConsole.Figure.IsoscelesTriangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class IsoscelesTrapezoidCommand extends FigureCommand{
    static {
        Translator.save(Lang.PL, Identifier.COM_ISOSCELESTRAPEZOID_DESCRIPTION,
                "\tDeklaruje trapez równoboczny z podaną cechą (bok, wysokość, pole)");
        Translator.save(Lang.EN, Identifier.COM_ISOSCELESTRAPEZOID_DESCRIPTION,
                "\tDeclares isosceles trapezoid by given parameter (side, height or area)");
    }

    @Override
    public String getName() {
        return "isoscelestrapezoid";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_ISOSCELESTRAPEZOID_DESCRIPTION);
    }

    @Override
    public int getNumberOfArguments() {
        return 3;
    }

    ArgumentsHandler handler = new ArgumentsHandler(3);
    double a = -1, b = -1, side = -1, area = -1, height = -1;
    int parameterPosition = 1;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "a" -> argument.enforceRelativePosition(parameterPosition)
                    .supplyHandler(pos -> handler.supply(pos, arg -> a = arg.getNumericValue()));
            case "b" -> argument.enforceRelativePosition(parameterPosition)
                    .supplyHandler(pos -> handler.supply(pos, arg -> b = arg.getNumericValue()));
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
        IsoscelesTrapezoid trapezoid = new IsoscelesTrapezoid(a, b, side, height, area);
        updateContext(trapezoid);
    }
}
