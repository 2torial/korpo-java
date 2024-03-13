package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;

public class ExitCommand extends Command {
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getHelp() {
        return "Exits console";
    }

    @Override
    public void addArgument(Argument argument) throws IllegalArgumentException {
        super.addArgument(argument);
    }

    @Override
    public void execute() {
        if (showHelp)
            System.out.println(getExtendedHelp());
    }
}
