package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.*;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class CircumcircleCommand extends FigureCommand {
    static {
        Translator.save(Lang.PL, Identifier.COM_CIRCUMCIRCLE_DESCRIPTION,
                "\tDeklaruje koło opisane na figurze wskazanej w ID");
        Translator.save(Lang.EN, Identifier.COM_CIRCUMCIRCLE_DESCRIPTION,
                "\tDeclares circle described on figure specified by ID");
    }

    @Override
    public String getName() {
        return "circumcircle";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_CIRCUMCIRCLE_DESCRIPTION);
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    int providedId;
    String providedName;
    ArgumentsHandler handler = new ArgumentsHandler(1);
    
    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "id" -> argument.enforceRelativePosition(1).setName("id/name")
                .supplyHandler(pos -> handler.supply(pos, arg -> providedId = arg.getIntegerValue()));
            case "name" -> argument.enforceRelativePosition(1).setName("id/name")
                .supplyHandler(pos -> handler.supply(pos, arg -> providedName = arg.rawValue));
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);

        Figure fig = Context.findFigure(providedId);
        Circle circle = fig.getCircumcircle();
    }
}
