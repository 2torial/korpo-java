package GeoConsole.UserInput;

import java.util.LinkedList;

public abstract class Command {
    protected boolean showHelp = false;
    protected LinkedList<Argument> arguments = new LinkedList<>();

    public abstract String getName();
    public abstract String getHelp();
    public String getExtendedHelp() {
        return getHelp();
    }
    public void addArgument(Argument argument) throws IllegalArgumentException {
        if (!showHelp && argument.isParameter)
            showHelp = argument.value.equals("help");
        arguments.add(argument);
    }
    public abstract void execute();

}

