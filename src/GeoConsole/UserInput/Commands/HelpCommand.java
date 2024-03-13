package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.CommandFactory;

public class HelpCommand extends Command {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Displays available commands";
    }

    @Override
    public void addArgument(Argument argument) throws IllegalArgumentException {
        super.addArgument(argument);
    }

    @Override
    public void execute() {
        if (showHelp) {
            System.out.println(getExtendedHelp());
            return;
        }

        var builder = new StringBuilder();
        CommandFactory.forEachInstance((key, command) ->
            builder.append(String.format("%s\t%s\n", key, command.getHelp())));
        System.out.println(builder);
    }
}
