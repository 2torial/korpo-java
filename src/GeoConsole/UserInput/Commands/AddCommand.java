package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Arguments.Parameter;
import GeoConsole.UserInput.Arguments.ValueArgument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Exceptions.NotNumericArgument;

public class AddCommand extends Command {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getHelp() {
        return "\tAdds numbers";
    }

    @Override
    public String getExtendedHelp() {
        return """
            Adds numbers
            Pattern: add ...num [-round|r _n]?
            Arguments:
              num\t\tNumber
              -round\tRounds result to _n-th digit""";
    }

    @Override
    protected Parameter parseNewParameter(String input, int position) {
        return switch (input) {
            case "r", "round" -> new Parameter("round", position, 1);
            default -> super.parseNewParameter(input, position);
        };
    }

    private int roundTo = -1;
    private double result = 0;

    @Override
    protected void handleParameter(Parameter parameter) {
        switch (parameter.value) {
            case "round" -> roundTo = 0;
        }
    }

    @Override
    protected void handleArgumentExpectedByParameter(ValueArgument argument, Parameter parameter) {
        switch (parameter.value) {
            case "round" -> roundTo = argument.parseValueInt();
        }
    }

    @Override
    protected void handleRegularArgument(ValueArgument argument) {
        try {
            result += argument.parseValueDouble();
        } catch (NotNumericArgument e) {
            result += readNumericVariable(argument.value);
        }
    }

    @Override
    protected void finalizeExecution() {
        System.out.println((roundTo < 0) ? result : round(result, roundTo));
    }

    private double round(double value, int n) {
        double rounder = Math.pow(10, n);
        return Math.round(value * rounder) / rounder;
    }
}
