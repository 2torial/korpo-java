package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Exceptions.NotIntegerArgument;
import GeoConsole.UserInput.Exceptions.NotNumericArgument;
import GeoConsole.UserInput.Exceptions.UnknownArgument;

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
    public void addArgument(Argument argument) throws IllegalArgumentException {
        if (argument.isParameter) {
            argument = switch (argument.value) {
                case "help" -> argument;
                case "r", "round" -> new Argument("-round", argument.position);
                default -> throw new UnknownArgument(argument);
            };
        }
        super.addArgument(argument);
    }

    @Override
    public String getExtendedHelp() {
        return """
            Adds numbers
            Pattern: add ...num [-r|round num?]
            Parameters:
              r|round\tRounds result""";
    }

    @Override
    public void execute() {
        if (showHelp) {
            System.out.println(getExtendedHelp());
            return;
        }

        int roundTo = -1;
        boolean roundArgumentNext = false;
        double result = 0;
        for (var argument : arguments) {
            if (argument.isParameter) {
                if (argument.value.equals("round")) {
                    roundTo = 0;
                    roundArgumentNext = true;
                } else if (roundArgumentNext)
                    throw new NotIntegerArgument(argument);
                continue;
            }

            if (roundArgumentNext) {
                try {
                    roundTo = Integer.parseInt(argument.value);
                } catch (Exception e) {
                    throw new NotIntegerArgument(argument);
                }
                roundArgumentNext = false;
                continue;
            }

            try {
                result += Double.parseDouble(argument.value);
            } catch (Exception e) {
                throw new NotNumericArgument(argument);
            }
        }

        System.out.println((roundTo < 0) ? result : round(result, roundTo));
    }

    private double round(double value, int n) {
        double rounder = Math.pow(10, n);
        return Math.round(value * rounder) / rounder;
    }
}
