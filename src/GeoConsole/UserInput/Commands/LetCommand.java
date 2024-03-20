package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context;

import java.util.List;

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
    public void supplyParameter(Argument argument) {
        switch (argument.getValue()) {
            case "n", "num" -> argument
                .supplyValue("num")
                .supplyHandler(args -> parseDouble = true);
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(List<Argument> arguments) {
        var variableName = arguments.getFirst().getValue();
        var variableValue = arguments.get(1).getValue();

        if (parseDouble) {
            double value = Double.parseDouble(variableValue);
            System.out.printf("%s : num = ", variableName);
            System.out.println(value);
            Context.declare(variableName, value);
        } else {
            System.out.printf("%s : str = %s\n", variableName, variableValue);
            Context.declare(variableName, variableValue);
        }
    }
}
