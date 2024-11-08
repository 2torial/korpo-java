package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

public class InvalidParameterException extends Exception {
    static {
        Translator.save(Lang.PL, Identifier.ERR_INVALID_PARAMETER_AT_PART,
                " jest niedozwolonym parametrem [pozycja: ");
        Translator.save(Lang.EN, Identifier.ERR_INVALID_PARAMETER_AT_PART,
                " is not a valid parameter [position: ");
    }
    Argument parameter;

    public InvalidParameterException(Argument parameter) {
        this.parameter = parameter;
    }

    @Override
    public String getMessage() {
        return "-" + parameter + Translator.read(Identifier.ERR_INVALID_PARAMETER_AT_PART) + parameter.absolutePosition + "]";
    }
}
