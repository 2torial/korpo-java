package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class RemoveCommand extends FigureCommand {
    static {
        Translator.save(Lang.PL, Identifier.COM_REMOVE_DESCRIPTION,
                "\tUsuwa figurę z listy (wskazaną przez ID)");
        Translator.save(Lang.EN, Identifier.COM_REMOVE_DESCRIPTION,
                "\tRemove figure from the list (selected by id/name)");
    }

    @Override
    public String getName() {
        return "square";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_REMOVE_DESCRIPTION);
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        super.supplyParameter(argument);
    }

    @Override
    protected void handle(Argument[] arguments) {
        try {
            Context.removeFigure(arguments[0].getIntegerValue());
            System.out.println("Figure removed successfully");
        } catch (Exception ignored) {
            System.out.println("Invalid input – figure not found");
        }
    }
}
