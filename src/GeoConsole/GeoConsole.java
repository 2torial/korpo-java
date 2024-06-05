package GeoConsole;

import GeoConsole.UserInput.*;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;
import GeoConsole.UserInput.Exceptions.DuplicateParameterException;
import GeoConsole.UserInput.Exceptions.InvalidNumberOfArguments;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;
import GeoConsole.UserInput.Exceptions.InvalidPositionException;

import java.util.Scanner;

public class GeoConsole{
    static {
        Translator.save(Lang.PL, Identifier.ERR_EXITED,
                "Niemożliwy stan: zamknięto konsolę");
        Translator.save(Lang.EN, Identifier.ERR_EXITED,
                "Console is exited");

        Translator.save(Lang.PL, Identifier.ERR_EMPTY_INPUT,
                "Wprowadzone dane nie mogą być puste");
        Translator.save(Lang.EN, Identifier.ERR_EMPTY_INPUT,
                "Input cannot be empty");
    }
    private final Scanner scanner = new Scanner(System.in);

    private boolean isExited = false;
    public final static String versionInfo = "GeoConsole v1.0 (05.06.2024)";

    public void exit() {
        isExited = true;
    }

    public boolean isExited() {
        return isExited;
    }

    public void handleInput() throws InvalidParameterException, InvalidNumberOfArguments, DuplicateParameterException, InvalidPositionException {
        if (isExited)
            throw new IllegalStateException(Translator.read(Identifier.ERR_EXITED));

        String[] tokens = scanner.nextLine().trim().toLowerCase().split("\\s+");
        if (tokens.length == 0)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_EMPTY_INPUT));

        Command command = CommandFactory.parseCommand(tokens);
        if (command.getName().equals("exit")) {
            exit();
            return;
        }

        command.execute();
    }
}