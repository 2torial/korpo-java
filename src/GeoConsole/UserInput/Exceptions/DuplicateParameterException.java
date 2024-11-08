package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

public class DuplicateParameterException extends Exception {
    static {
        Translator.save(Lang.PL, Identifier.ERR_PARAMETER_DUPLICATE_AT_PART,
                "Niedozwolony duplikat parametru na pozycji ");
        Translator.save(Lang.EN, Identifier.ERR_PARAMETER_DUPLICATE_AT_PART,
                "Illegal parameter duplication at ");
    }
    final Argument argument;
    public DuplicateParameterException(Argument argument) {
        this.argument = argument;
    }

    @Override
    public String getMessage() {
        return Translator.read(Identifier.ERR_PARAMETER_DUPLICATE_AT_PART) + argument.absolutePosition + ": " + argument;
    }
}
