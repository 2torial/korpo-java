package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;

public class DuplicateParameterException extends Exception {
    final Argument argument;
    public DuplicateParameterException(Argument argument) {
        this.argument = argument;
    }

    @Override
    public String getMessage() {
        return "Illegal parameter duplication at position " + argument.absolutePosition + ": " + argument;
    }
}
