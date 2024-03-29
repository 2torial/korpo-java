package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;

public class InvalidParameterException extends Exception {
    Argument parameter;

    public InvalidParameterException(Argument parameter) {
        this.parameter = parameter;
    }

    @Override
    public String getMessage() {
        return "-" + parameter + " is not a valid parameter [position: " + parameter.absolutePosition + "]";
    }
}
