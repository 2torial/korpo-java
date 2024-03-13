package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;

public class NotNumericArgument extends IllegalArgumentException {
    private final Argument argument;
    public NotNumericArgument(Argument argument) {
        this.argument = argument;
    }
    @Override
    public String getMessage() {
        String message = "[%s%s] (position: %d) is not numeric";
        return String.format(message, (argument.isParameter) ? "-" : "", argument.value, argument.position);
    }
}
