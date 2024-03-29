package GeoConsole.UserInput;

import GeoConsole.UserInput.Context.State;
import GeoConsole.UserInput.Exceptions.*;

import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public abstract class Command {
    private boolean showHelp = false;

    protected final List<Argument> arguments = new LinkedList<>();
    private final Map<String, Integer> usedParameters = new HashMap<>();

    public abstract String getName();
    public abstract String getHelp();
    public int getNumberOfArguments() {
        return 0;
    }
    public String getExtendedHelp() {
        return getHelp();
    }

    protected State state = new State(1);

    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "help", "h" -> argument.setName("help");
            default ->
                throw new InvalidParameterException(argument);
        }
    }

    public final void addArgument(Argument argument) throws IllegalArgumentException, DuplicateParameterException {
        if (argument.isParameter) {
            usedParameters.putIfAbsent(argument.toString(), 0);
            int numberOfUses = usedParameters.get(argument.toString());

            if (numberOfUses > argument.getNumberOfAllowedDuplicates())
                throw new DuplicateParameterException(argument);
            else usedParameters.put(argument.toString(), numberOfUses + 1);

            if (argument.rawValue.equals("help"))
                showHelp = true;
        }
        arguments.add(argument);
    }

    public final void execute() throws IllegalArgumentException, InvalidNumberOfArguments, InvalidPositionException {
        if (showHelp) {
            System.out.println(getExtendedHelp());
            return;
        }

        var commandArguments = new LinkedList<Argument>();
        List<Consumer<Argument[]>> finalizers = new LinkedList<>();
        int relativePosition = 1;
        while (!arguments.isEmpty()) {
            var argument = arguments.removeFirst();
            argument.setRelativePosition(relativePosition);

            if (!argument.isParameter) {
                relativePosition++;
                commandArguments.add(argument);
                continue;
            }

            var subArguments = new Argument[argument.getNumberOfSubArguments()];
            Argument subArgument;
            int subArgumentRelativePosition = 0;
            for (int n = 0; n < argument.getNumberOfSubArguments(); n++) {
                subArgumentRelativePosition++;
                try {
                    subArgument = arguments.removeFirst();
                } catch (NoSuchElementException e) {
                    throw new InvalidNumberOfArguments(n, argument);
                }
                if (subArgument.isParameter)
                    throw new IllegalArgumentException("Expected argument, not parameter");
                subArgument.setRelativePosition(subArgumentRelativePosition);
                subArguments[n] = subArgument;
            }
            argument.getHandler().accept(subArguments);
            finalizers.add(argument.getFinalizer());
        }

        if (getNumberOfArguments() >= 0 && commandArguments.size() != getNumberOfArguments())
            throw new InvalidNumberOfArguments(commandArguments.size(), this);
        var arguments = commandArguments.toArray(new Argument[0]);
        handle(arguments);

        for (var finalizer : finalizers)
            finalizer.accept(arguments);
        finalize(arguments);
    }

    protected void handle(Argument[] arguments) {}

    protected void finalize(Argument[] arguments) {}
}

