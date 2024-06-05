package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Ellipse;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class EllipseCommand extends FigureCommand {
    static {
        Translator.save(Lang.PL, Identifier.COM_ELLIPSE_DESCRIPTION,
                "\tTworzy elipsę z dwóch podanych cech (pierwszej osi, drugiej osi lub pola)");
        Translator.save(Lang.EN, Identifier.COM_ELLIPSE_DESCRIPTION,
                "\tDeclares ellipse by 2 given parameters (axle shaft [max 2] or area)");
    }

    @Override
    public String getName() {
        return "ellipse";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_ELLIPSE_DESCRIPTION);
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    ArgumentsHandler handler = new ArgumentsHandler(2);
    int parameterPosition = 1;
    double axle1 = -1, axle2 = -1, area = -1;
    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "axle" -> argument.allowDuplicates(1).enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> {
                    if (axle1 == -1)
                        axle1 = arg.getNumericValue();
                    else
                        axle2 = arg.getNumericValue();
                }));
            case "area" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> area = arg.getNumericValue()));
            default -> super.supplyParameter(argument);
        }
        parameterPosition++;
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);
        Ellipse ellipse = new Ellipse(axle1, axle2, area);
        updateContext(ellipse);
    }
}
