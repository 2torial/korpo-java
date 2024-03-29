package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.*;

public class LetCommand extends Command {
    @Override
    public String getName() {
        return "let";
    }

    @Override
    public String getHelp() {
        return "\tDeclares variable of given type (str or num)";
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    @Override
    public String getExtendedHelp() {
        return """
            Declares variable of given type (str or num)"
            If not specified, program is going to try converting variable to num
            Pattern: let var value [-num]
            Arguments:
              name\tName of the variable
              value\tValue of the variable
              -str\tEnsures declaring as numeric""";
    }

    boolean parseDouble = false;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "n", "num" -> argument.setName("num").supplyHandler(() -> parseDouble = true);
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        var variableName = arguments[0].rawValue;

        if (parseDouble) {
            double value = arguments[1].getNumericValue();
            System.out.printf("%s : num = ", variableName);
            System.out.println(value);
            Context.declare(variableName, value);
        } else {
            String value = arguments[1].rawValue;
            System.out.printf("%s : str = %s\n", variableName, value);
            Context.declare(variableName, value);
        }
    }
}
