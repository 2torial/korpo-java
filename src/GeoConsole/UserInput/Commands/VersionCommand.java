package GeoConsole.UserInput.Commands;

import GeoConsole.GeoConsole;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

public class VersionCommand extends Command {
    static {
        Translator.save(Lang.PL, Identifier.COM_VERSION_DESCRIPTION,
                "\tWyświetla informację o wersji programu");
        Translator.save(Lang.EN, Identifier.COM_VERSION_DESCRIPTION,
                "\tDisplays GeoConsole version information");
    }

    @Override
    public String getName() {
        return "version";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_VERSION_DESCRIPTION);
    }

    @Override
    public int getNumberOfArguments() {
        return 0;
    }

    @Override
    protected void handle(Argument[] arguments) {
        System.out.println(GeoConsole.versionInfo);
    }
}
