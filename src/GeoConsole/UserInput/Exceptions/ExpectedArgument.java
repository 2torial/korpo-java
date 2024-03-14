package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Arguments.Parameter;

public class ExpectedArgument extends IllegalArgumentException {
    private final Parameter parameter;
    public ExpectedArgument(Parameter parameter) {
        this.parameter = parameter;
    }
    @Override
    public String getMessage() {
        String message = "Parameter [%s] (position: %d) expected (%d) arguments, (%d) were given";
        return String.format(message, parameter, parameter.position, parameter.expectedArguments, parameter.getGivenArguments());
    }
}
