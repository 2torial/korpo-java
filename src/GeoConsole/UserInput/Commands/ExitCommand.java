package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

public class ExitCommand extends Command {
    static {
        Translator.save(Lang.PL, Identifier.COM_EXIT_DESCRIPTION,
                "\tWychodzi z konsoli");
        Translator.save(Lang.EN, Identifier.COM_EXIT_DESCRIPTION,
                "\tExits console");
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_EXIT_DESCRIPTION);
    }

    @Override
    public int getNumberOfArguments() {
        return 0;
    }

    @Override
    protected void handle(Argument[] arguments) {

    }
}
