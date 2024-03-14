package GeoConsole.UserInput;

import GeoConsole.UserInput.Arguments.*;
import GeoConsole.UserInput.Exceptions.DuplicateParameter;
import GeoConsole.UserInput.Exceptions.ExpectedArgument;

import java.util.*;

public abstract class Command {
    private boolean showHelp = false;
    protected final List<Argument> arguments = new LinkedList<>();
    private Parameter lastParameter = new Parameter(null, 0, 0);

    protected boolean restrictDuplicateParameters = true;
    private final Set<String> usedParameters = new HashSet<>();

    public abstract String getName();
    public abstract String getHelp();
    public String getExtendedHelp() {
        return getHelp();
    }

    public final void addValueArgument(Argument argument) throws IllegalArgumentException {
        arguments.add(argument);
    }
    public final void addParameter(Parameter parameter) throws IllegalArgumentException {
        if (usedParameters.contains(parameter.value) && restrictDuplicateParameters)
            throw new DuplicateParameter(parameter);
        usedParameters.add(parameter.value);
        arguments.add(parameter);
        if (parameter.value.equals("help"))
            showHelp = true;
    }
    protected Parameter parseNewParameter(String input, int position) {
        return switch (input) {
            case "help", "h" -> new Parameter("help", position, 0);
            default -> throw new IllegalArgumentException(
                String.format("[%s] (position %d) is not a valid parameter", input, position));
        };
    }

    public final void execute() throws IllegalArgumentException {
        if (showHelp) {
            System.out.println(getExtendedHelp());
            return;
        }

        for (var argument : arguments) {
            if (argument.isParameter) {
                var parameter = (Parameter) argument;
                if (lastParameter.isExpectingArgument())
                    throw new ExpectedArgument(parameter);
                handleParameter(parameter);
                lastParameter = parameter;
            } else {
                var valueArgument = (ValueArgument) argument;
                if (lastParameter.isExpectingArgument()) {
                    handleArgumentExpectedByParameter(valueArgument, lastParameter);
                    lastParameter.fulfillExpectation();
                } else handleRegularArgument(valueArgument);
            }
        }

        if (lastParameter.isExpectingArgument())
            throw new ExpectedArgument(lastParameter);
        finalizeExecution();
    }

    protected void handleParameter(Parameter parameter) {}
    protected void handleArgumentExpectedByParameter(ValueArgument argument, Parameter parameter) {}
    protected void handleRegularArgument(ValueArgument argument) {}
    protected void finalizeExecution() {};

    private static Map<String, String> stringVariables = new HashMap<>();
    private static Map<String, Double> numericVariables = new HashMap<>();

    protected void declareNumericVariable(String name, double value) {
        stringVariables.remove(name);
        numericVariables.put(name, value);
    }

    protected void declareStringVariable(String name, String value) {
        numericVariables.remove(name);
        stringVariables.put(name, value);
    }

    protected String readStringVariable(String name) {
        if (stringVariables.containsKey(name))
            return stringVariables.get(name);
        if (numericVariables.containsKey(name))
            throw new IllegalArgumentException(String.format("Variable [%s] is not of type string", name));
        throw new IllegalArgumentException(String.format("Variable [%s] is not declared", name));
    }

    protected double readNumericVariable(String name) {
        if (numericVariables.containsKey(name))
            return numericVariables.get(name);
        if (stringVariables.containsKey(name))
            throw new IllegalArgumentException(String.format("Variable [%s] is not numeric", name));
        throw new IllegalArgumentException(String.format("Variable [%s] is not declared", name));
    }
}

