package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;

public class InvalidPositionException extends Exception {
    Argument argument;

    public InvalidPositionException(Argument argument) {
        this.argument = argument;
    }

    @Override
    public String getMessage() {
        int enforcedPosition = argument.getEnforcedPosition();
        StringBuilder builder = new StringBuilder();
        builder.append("Parameter ").append(argument);
        if (enforcedPosition == 1)
            builder.append(" requires to be positioned before any command's arguments");
        else
            builder.append(" requires to be positioned after ").append(enforcedPosition - 1).append(" command's argument");
        return builder.toString();
    }
}