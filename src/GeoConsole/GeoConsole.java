package GeoConsole;

import GeoConsole.UserInput.*;

import java.util.Scanner;

public class GeoConsole{
    private final Scanner scanner = new Scanner(System.in);

    private boolean isExited = false;
    private final static String version = "0.0.2";

    public static String getVersion() {
        return String.format("GeoConsole ver %s\nRelease date: 13.03.2024\n", version);
    }

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

        Command command = CommandFactory.createCommand(tokens[0]);
        if (command.getName().equals("exit"))
            exit();

        for (int i = 1; i < tokens.length; i++)
            command.addArgument(new Argument(tokens[i], i));

        command.execute();
    }
}