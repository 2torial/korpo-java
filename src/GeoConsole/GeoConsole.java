package GeoConsole;

import GeoConsole.UserInput.*;

import java.util.Scanner;

public class GeoConsole{
    private final Scanner scanner = new Scanner(System.in);

    private boolean isExited = false;
    public final static String versionInfo = "GeoConsole ver 0.0.4\nRelease date: 14.03.2024\n";

    public void exit() {
        isExited = true;
    }

    public boolean isExited() {
        return isExited;
    }

    public void handleInput() {
        if (isExited)
            throw new IllegalStateException("Console is exited");

        String[] tokens = scanner.nextLine().trim().split("\\s+");
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