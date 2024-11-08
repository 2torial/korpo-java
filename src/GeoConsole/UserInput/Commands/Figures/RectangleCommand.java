package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Rectangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;
import GeoConsole.UserInput.Exceptions.*;

public class RectangleCommand extends FigureCommand {
    static {
        Translator.save(Lang.PL, Identifier.COM_RECTANGLE_DESCRIPTION,
                "\tDeklaruje prostokąt z dwoma podanymi cechami (bok, przekątna, pole)\nBok może zostać podany dwa razy");
        Translator.save(Lang.EN, Identifier.COM_RECTANGLE_DESCRIPTION,
                "\tDeclares rectangle by 2 given parameters (side, diagonal or area)\nSide can be passed twice");
    }

    @Override
    public String getName() {
        return "rectangle";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_RECTANGLE_DESCRIPTION);
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
    }
}
