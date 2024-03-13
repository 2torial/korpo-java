package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;

public class DuplicateParameter extends IllegalArgumentException {
    private final Argument argument;
    public DuplicateParameter(Argument argument) {
        this.argument = argument;
    }
    @Override
    public String getMessage() {
        return String.format("[-%s] (position %d) cannot appear more than once", argument.value, argument.position);
    }
}
