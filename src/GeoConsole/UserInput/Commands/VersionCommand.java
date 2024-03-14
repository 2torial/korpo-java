package GeoConsole.UserInput.Commands;

import GeoConsole.GeoConsole;
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
    protected void finalizeExecution() {
        System.out.println(GeoConsole.versionInfo);
    }

}
