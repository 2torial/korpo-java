package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.*;

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
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "R", "ROUND" -> argument.setName("round").supplyHandler(() -> roundTo = 0);
            case "r", "round" -> argument.setName("round").supplyHandler(1, args -> {
                    roundTo = args[0].getIntegerValue();
                    if (roundTo < 0)
                        throw new IllegalArgumentException("Rounding argument must be a positive number");
                });
            case "v", "var" -> argument.setName("var").enforceRelativePosition(getNumberOfArguments() + 1) // must be passed after all arguments
                .supplyHandler(1, args -> variableName = args[0].rawValue)
                .supplyFinalizer(args -> Context.declare(variableName, result));

            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        for (var argument : arguments) {
            result += argument.getNumericValue();
        }
        result = (roundTo < 0) ? result : round(result, roundTo);
        System.out.println(result);
    }

    private double round(double value, int n) {
        double rounder = Math.pow(10, n);
        return Math.round(value * rounder) / rounder;
    }
}
