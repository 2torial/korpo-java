package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;

public class InvalidPositionException extends Exception {
    int position;
    Argument argument;

    public InvalidPositionException(int position, Argument argument) {
        this.position = position;
        this.argument = argument;
    }

    @Override
    public String getMessage() {
        int relativePosition = argument.getRelativePosition();
        StringBuilder builder = new StringBuilder();
        builder.append("Parameter ").append(argument).append(" [position: ").append(position).append("]");
        if (relativePosition == 1)
            return builder.append(" requires to be positioned before any command's arguments").toString();
        return builder.append(" requires to be positioned after ").append(relativePosition).append(" command's argument").toString();
    }
}