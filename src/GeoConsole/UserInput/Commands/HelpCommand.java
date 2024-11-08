package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.CommandFactory;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

public class HelpCommand extends Command {
    static {
        Translator.save(Lang.PL, Identifier.COM_HELP_DESCRIPTION,
                "\tWyświetla dostępne komendy");
        Translator.save(Lang.EN, Identifier.COM_HELP_DESCRIPTION,
                "\tDisplays available commands");
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_HELP_DESCRIPTION);
    }

    @Override
    public int getNumberOfArguments() {
        return 0;
    }

    @Override
    protected void handle(Argument[] arguments) {
        var builder = new StringBuilder();
        CommandFactory.forEachInstance((key, command) ->
            builder.append(String.format("%s\t%s\n", key, command.getHelp())));
        System.out.println(builder);
    }
}
