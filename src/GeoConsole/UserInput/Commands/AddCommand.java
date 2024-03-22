package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context;

import java.util.List;

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
    public int getNumberOfArguments() {
        return -1;
    }

    @Override
    public String getExtendedHelp() {
        return """
            Adds numbers
            Pattern: add ...num [-round num]
            Arguments:
              num\t\tNumber
              -round\tRounds result to n-th digit""";
    }

    String variableName;
    int roundTo = -1;
    double result = 0;

    @Override
    public void supplyParameter(Argument argument) {
        switch (argument.getValue()) {
            case "R", "ROUND" -> argument.supplyValue("round").supplyHandler(args -> roundTo = 0);
            case "r", "round" -> argument.supplyValue("round").supplyExpectations(1)
                .supplyHandler(args -> {
                    try {
                        roundTo = Integer.parseInt(args[0].getValue());
                    } catch (Exception e) {
                        roundTo = Context.readInt(args[0].getValue());
                    }
                    if (roundTo < 0)
                        throw new IllegalArgumentException("Rounding argument must be a positive number");
                });
            case "v", "var" -> argument
                .supplyValue("var")
                .supplyExpectations(1)
                .supplyHandler(args -> variableName = args[0].getValue())
                .supplyFinalizer(() -> Context.declare(variableName, this));
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(List<Argument> arguments) {
        for (var argument : arguments) {
            try {
                result += Double.parseDouble(argument.getValue());
            } catch (Exception e) {
                result += Context.readDouble(argument.getValue());
            }
        }
        System.out.println((roundTo < 0) ? result : round(result, roundTo));
    }

    private double round(double value, int n) {
        double rounder = Math.pow(10, n);
        return Math.round(value * rounder) / rounder;
    }
}
