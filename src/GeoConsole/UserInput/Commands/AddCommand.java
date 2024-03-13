package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Exceptions.DuplicateParameter;
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
    public String getExtendedHelp() {
        return """
            Adds numbers
            Pattern: add [-r|round] ...num
            Parameters:
              r|round\tRounds result""";
    }

    @Override
    public void addArgument(Argument argument) throws IllegalArgumentException {
        super.addArgument(argument);
    }

    @Override
    public void execute() {
        if (showHelp) {
            System.out.println(getExtendedHelp());
            return;
        }

        boolean isRounded = false;
        double result = 0;
        for (var argument : arguments) {
            if (argument.isParameter) {
                switch (argument.value) {
                    case "r", "round" -> {
                        if (isRounded)
                            throw new DuplicateParameter(argument);
                        isRounded = true;
                    }
                    default -> throw new UnknownArgument(argument);
                }
            } else {
                try {
                    result += Double.parseDouble(argument.value);
                } catch (Exception e) {
                    throw new NotNumericArgument(argument);
                }
            }
        }
        System.out.println((isRounded) ? (int)result : result);
    }
}
