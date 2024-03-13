package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;

public class NotIntegerArgument extends IllegalArgumentException {
    private final Argument argument;
    public NotIntegerArgument(Argument argument) {
        this.argument = argument;
    }
    @Override
    public String getMessage() {
        String message = "[%s%s] (position: %d) is not an integer";
        return String.format(message, (argument.isParameter) ? "-" : "", argument.value, argument.position);
    }
}
