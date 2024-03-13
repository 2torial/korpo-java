package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;

public class UnknownArgument extends IllegalArgumentException {
    private final Argument argument;
    public UnknownArgument(Argument argument) {
        this.argument = argument;
    }
    @Override
    public String getMessage() {
        String message = "[%s%s] cannot be recognized as a valid argument";
        return String.format(message, (argument.isParameter) ? "-" : "", argument.value);
    }
}
