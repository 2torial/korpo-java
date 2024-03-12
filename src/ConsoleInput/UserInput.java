package ConsoleInput;

import java.util.LinkedList;
import java.util.Scanner;

public class UserInput {
    private final static Scanner scanner = new Scanner(System.in);
    public final Command command;
    public final LinkedList<Argument> arguments;

    private UserInput(Command command, LinkedList<Argument> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public static UserInput interpret() throws IllegalArgumentException {
        String[] tokens = scanner.nextLine().split("\\s+");
        if (tokens.length == 0)
            throw new IllegalArgumentException("Input cannot be empty");

        Command command;
        command = Command.find(tokens[0]);

        var arguments = new LinkedList<Argument>();
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.charAt(0) != '-')
                arguments.add(new Argument(token, false));
            else if (token.length() > 2 && token.charAt(1) == '-' && token.charAt(2) != '-')
                arguments.add(new Argument(token, true));
            else
                throw new IllegalArgumentException(String.format("[%s] is not a valid argument", token));
        }

        return new UserInput(command, arguments);
    }
}
