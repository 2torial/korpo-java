package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;

public class DuplicateParameter extends IllegalArgumentException {
    private final Argument argument;
    public DuplicateParameter(Argument argument) {
        this.argument = argument;
    }
    @Override
    public String getMessage() {
        String message = "[%s%s] (position: %d) cannot appear more than once";
        return String.format(message, (argument.isParameter) ? "-" : "", argument.value, argument.position);
    }
}
