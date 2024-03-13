package GeoConsole.UserInput.Commands;

import GeoConsole.GeoConsole;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;

public class VersionCommand extends Command {
    @Override
    public String getName() {
        return "version";
    }

    @Override
    public String getHelp() {
        return "Displays GeoConsole version information";
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

        System.out.println(GeoConsole.getVersion());
    }
}
