package GeoConsole.UserInput;

import GeoConsole.UserInput.Commands.*;
import GeoConsole.UserInput.Commands.Figures.*;
import GeoConsole.UserInput.Exceptions.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public final class CommandFactory {
    private CommandFactory() {} // Static class

    public static Command parseCommand(String[] tokens) throws InvalidParameterException, DuplicateParameterException {
        String name = tokens[0];
        Supplier<Command> supplier = commandSuppliers.get(name);
        if (supplier == null)
            throw new IllegalArgumentException("Unknown command: " + name);
        var command = supplier.get();

        for (int position = 1; position < tokens.length; position++) {
            String token = tokens[position];
            if (token == null || token.isEmpty())
                throw new IllegalArgumentException("Argument cannot be empty");
            if (token.length() == 1 || token.charAt(0) != '-' || token.charAt(1) != '-') {
                command.addArgument(new Argument(token, position, false));
                continue;
            }

            var parameter = new Argument(token.substring(2), position, true);
            if (token.length() == 2)
                throw new InvalidParameterException(parameter);

            command.supplyParameter(parameter);
            command.addArgument(parameter);
        }

        return command;
    }

    public static void forEachInstance(BiConsumer<String, Command> consumer) {
        commandPool.forEach(consumer);
    }

    private final static HashMap<String, Supplier<Command>> commandSuppliers = new HashMap<>();
    private final static LinkedHashMap<String, Command> commandPool = new LinkedHashMap<>();
    static {
        commandSuppliers.put("version", VersionCommand::new);
        commandSuppliers.put("sort", SortCommand::new);
        commandSuppliers.put("help", HelpCommand::new);
        commandSuppliers.put("exit", ExitCommand::new);
        commandSuppliers.put("add", AddCommand::new);
        commandSuppliers.put("let", LetCommand::new);
        commandSuppliers.put("square", SquareCommand::new);
        commandSuppliers.put("triangle", TriangleCommand::new);
        commandSuppliers.put("rectangle", RectangleCommand::new);
        commandSuppliers.put("rhombus", RhombCommand::new);
        commandSuppliers.put("iso-triangle", IsoscelesTriangleCommand::new);

        commandSuppliers.forEach((key, supplier) -> commandPool.put(key, supplier.get()));
    }
}