package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;

public class NotIntegerArgument extends IllegalArgumentException {
    private final Argument argument;
    public NotIntegerArgument(Argument argument) {
        this.argument = argument;
    }
    @Override
    public String getMessage() {
        return String.format("[%s] (position: %d) is not an integer", argument.value, argument.position);
    }
}
