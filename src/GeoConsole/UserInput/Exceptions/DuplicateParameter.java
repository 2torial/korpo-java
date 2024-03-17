package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Arguments.Parameter;

public class DuplicateParameter extends IllegalArgumentException {
    private final Parameter parameter;
    public DuplicateParameter(Parameter parameter) {
        this.parameter = parameter;
    }
    @Override
    public String getMessage() {
        String message = "[%s] (position: %d) cannot appear more than once";
        return String.format(message, parameter, parameter.position);
    }
}
