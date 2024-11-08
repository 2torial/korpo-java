package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.*;
import GeoConsole.UserInput.Context.Translator.*;

public class LanguageCommand extends Command {
    static {
        Translator.save(Lang.PL, Identifier.COM_LANGUAGE_DESCRIPTION,
                "Zmienia język interfejsu (nie dotyczy poleceń terminala)");
        Translator.save(Lang.EN, Identifier.COM_LANGUAGE_DESCRIPTION,
                "Changes language of the UI (does not include commands)");

        Translator.save(Lang.PL, Identifier.ERR_UNSUPPORTED_LANGUAGE,
                "Niewspierany język: ");
        Translator.save(Lang.EN, Identifier.ERR_UNSUPPORTED_LANGUAGE,
                "Unsupported language: ");

        Translator.save(Lang.PL, Identifier.TXT_LANGUAGE_CHANGE,
                "Wybrano język polski interfejsu");
        Translator.save(Lang.EN, Identifier.TXT_LANGUAGE_CHANGE,
                "Switched to English UI");
    }

    @Override
    public String getName() {
        return "language";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_LANGUAGE_DESCRIPTION);
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    @Override
    protected void handle(Argument[] arguments) {
        Translator.changeLanguage(switch (arguments[0].rawValue) {
            case "pl", "PL" -> Lang.PL;
            case "en", "EN" -> Lang.EN;
            default -> throw new IllegalStateException(
                Translator.read(Identifier.ERR_UNSUPPORTED_LANGUAGE) + arguments[0].rawValue);
        });
        Translator.print(Identifier.TXT_LANGUAGE_CHANGE);
    }
}
