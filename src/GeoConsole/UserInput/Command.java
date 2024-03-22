package GeoConsole.UserInput;

import java.util.*;
import java.util.function.Consumer;

public abstract class Command {
    private boolean showHelp = false;
    protected final List<Argument> arguments = new LinkedList<>();

    protected boolean restrictDuplicateParameters = true;
    private final Set<String> usedParameters = new HashSet<>();

    public abstract String getName();
    public abstract String getHelp();
    public abstract int getNumberOfArguments();
    public String getExtendedHelp() {
        return getHelp();
    }

    public final void addArgument(Argument argument) throws IllegalArgumentException {
        if (argument.isParameter) {
            if (usedParameters.contains(argument.getValue().toLowerCase()) && restrictDuplicateParameters)
                throw new IllegalArgumentException(String.format("Duplicate parameter [%s]", argument));
            usedParameters.add(argument.getValue().toLowerCase());

            if (argument.hasValue("help"))
                showHelp = true;
        }
        arguments.add(argument);
    }

    public void supplyParameter(Argument argument) {
        switch (argument.getValue()) {
            case "help", "h" -> argument.supplyValue("help");
            default ->
                throw new IllegalArgumentException(String.format("[-%s] is not a valid parameter", argument.getValue()));
        }
    }

    public final void execute() throws IllegalArgumentException {
        if (showHelp) {
            System.out.println(getExtendedHelp());
            return;
        }

        var commandArguments = new LinkedList<Argument>();
        List<Runnable> finalizers = new LinkedList<>();
        while (!arguments.isEmpty()) {
            var argument = arguments.removeFirst();
            if (argument.isParameter) {
                var parameterArguments = new Argument[argument.getExpectedArguments()];
                Argument parameterArgument;
                for (int n = 0; n < argument.getExpectedArguments(); n++) {
                    try {
                        parameterArgument = arguments.removeFirst();
                    } catch (NoSuchElementException e) {
                        String msg = "Parameter [%s] expects %d arguments, %d were given";
                        throw new IllegalArgumentException(String.format(msg, argument, argument.getExpectedArguments(), n));
                    }
                    if (parameterArgument.isParameter)
                        throw new IllegalArgumentException("Expected argument, not parameter");
                    parameterArguments[n] = parameterArgument;
                }
                argument.getHandler().accept(parameterArguments);
                finalizers.add(argument.getFinalizer());
                continue;
            }
            commandArguments.add(argument);
        }

        if (getNumberOfArguments() < 0 || commandArguments.size() == getNumberOfArguments())
            handle(commandArguments);
        else throw new IllegalArgumentException("Incorrect number of arguments");
        for (var finalizer : finalizers)
            finalizer.run();
    }

    protected abstract void handle(List<Argument> arguments);
}

