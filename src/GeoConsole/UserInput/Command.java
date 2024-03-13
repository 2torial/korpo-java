package GeoConsole.UserInput;

import GeoConsole.UserInput.Exceptions.DuplicateParameter;

import java.util.*;

public abstract class Command {
    protected boolean showHelp = false;
    protected List<Argument> arguments = new LinkedList<>();

    // If true, and addArguments is not override, causes object to throw an error if given parameter was used before
    protected boolean restrictDuplicateParameters = true;
    // Every parameter added is recorded with number of times it was used
    protected Map<String, Integer> usedParameters = new HashMap<>();

    public abstract String getName();
    public abstract String getHelp();
    public String getExtendedHelp() {
        return getHelp();
    }
    public void addArgument(Argument argument) throws IllegalArgumentException {
        if (argument.isParameter) {
            if (usedParameters.containsKey(argument.value)) {
                if (restrictDuplicateParameters)
                    throw new DuplicateParameter(argument);
                usedParameters.put(argument.value, usedParameters.get(argument.value)+1);
            } else {
                usedParameters.put(argument.value, 1);
            }

            if (argument.value.equals("help"))
                showHelp = true;
        }
        arguments.add(argument);
    }
    public abstract void execute();
}

