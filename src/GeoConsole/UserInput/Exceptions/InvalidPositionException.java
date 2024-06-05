package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

public class InvalidPositionException extends Exception {
    static {
        Translator.save(Lang.PL, Identifier.ERR_REQUIRED_FIRST_PART,
                " musi być postawiony przed jakimkolwiek innym argumentem");
        Translator.save(Lang.EN, Identifier.ERR_REQUIRED_FIRST_PART,
                " requires to be positioned before any command's arguments");

        Translator.save(Lang.PL, Identifier.ERR_REQUIRED_AFTER_PART,
                " musi być postawiony po ");
        Translator.save(Lang.EN, Identifier.ERR_REQUIRED_AFTER_PART,
                " requires to be positioned after ");
    }
    Argument argument;

    public InvalidPositionException(Argument argument) {
        this.argument = argument;
    }

    @Override
    public String getMessage() {
        int enforcedPosition = argument.getEnforcedPosition();
        StringBuilder builder = new StringBuilder();
        builder.append(Translator.getLanguage() == Lang.EN ? "Parameter " : "Parameter").append(argument);
        if (enforcedPosition == 1)
            builder.append(Translator.read(Identifier.ERR_REQUIRED_FIRST_PART));
        else
            builder
                .append(Translator.read(Identifier.ERR_REQUIRED_AFTER_PART))
                .append(enforcedPosition - 1)
                .append(Translator.getLanguage() == Lang.EN ? " command's argument" : "argumencie");
        return builder.toString();
    }
}