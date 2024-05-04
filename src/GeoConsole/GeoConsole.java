package GeoConsole;

import GeoConsole.UserInput.*;
import GeoConsole.UserInput.Exceptions.DuplicateParameterException;
import GeoConsole.UserInput.Exceptions.InvalidNumberOfArguments;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;
import GeoConsole.UserInput.Exceptions.InvalidPositionException;

import java.util.Scanner;

public class GeoConsole{
    private final Scanner scanner = new Scanner(System.in);

    private boolean isExited = false;
    public final static String versionInfo = "GeoConsole ver 0.1.2\nRelease date: 04.05.2024\n";

    public void exit() {
        isExited = true;
    }

    public boolean isExited() {
        return isExited;
    }

    public void handleInput() throws InvalidParameterException, InvalidNumberOfArguments, DuplicateParameterException, InvalidPositionException {
        if (isExited)
            throw new IllegalStateException("Console is exited");

        String[] tokens = scanner.nextLine().trim().toLowerCase().split("\\s+");
        if (tokens.length == 0)
            throw new IllegalArgumentException("Input cannot be empty");

        Command command = CommandFactory.parseCommand(tokens);
        if (command.getName().equals("exit")) {
            exit();
            return;
        }

        command.execute();
    }
}