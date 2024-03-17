package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Arguments.Parameter;
import GeoConsole.UserInput.Arguments.ValueArgument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Exceptions.NotNumericArgument;

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
    public String getExtendedHelp() {
        return """
            Declares variable of given type (str or num)"
            If not specified, program is going to try converting variable to num
            Pattern: let _name _[-str] _value
            Arguments:
              name\tName of the variable
              -str\tEnsures declaring as string
              value\tValue of the variable""";
    }

    private int handledArguments = 0;
    private boolean isString = false;
    private String variableName;

    @Override
    protected Parameter parseNewParameter(String input, int position) {
        return switch (input) {
            case "str" -> new Parameter(input, position, 0);
            default -> super.parseNewParameter(input, position);
        };
    }

    @Override
    protected void handleParameter(Parameter parameter) {
        if (parameter.value.equals("str")) {
            if (parameter.position != 2)
                throw new IllegalArgumentException(
                    String.format("[%s] cannot be passed on position (%d)", parameter, parameter.position));
            isString = true;
        }
    }

    @Override
    protected void handleRegularArgument(ValueArgument argument) {
        if (handledArguments == 0)
            variableName = argument.value;
        else if (handledArguments == 1) {
            if (isString)
                declareStringVariable(variableName, argument.value);
            else try {
                declareNumericVariable(variableName, argument.parseValueDouble());
            } catch (NotNumericArgument e) {
                declareStringVariable(variableName, argument.value);
            }
        } else throw new IllegalArgumentException("Too many arguments");
        handledArguments++;
    }

    @Override
    protected void finalizeExecution() {
        if (isString)
            System.out.printf("%s : str = %s\n", variableName, readStringVariable(variableName));
        else {
            System.out.printf("%s : num = ", variableName);
            System.out.println(readNumericVariable(variableName));
        }
    }
}
