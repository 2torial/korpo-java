package GeoConsole.UserInput;

import GeoConsole.UserInput.Arguments.ValueArgument;
import GeoConsole.UserInput.Commands.*;
import GeoConsole.UserInput.Exceptions.UnknownCommandName;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public final class CommandFactory {
    private CommandFactory() {} // Static class

    public static Command parseCommand(String[] tokens) {
        String name = tokens[0];
        Supplier<Command> supplier = commandSuppliers.get(name);
        if (supplier == null)
            throw new UnknownCommandName(name);
        var command = supplier.get();

        for (int position = 1; position < tokens.length; position++) {
            String value = tokens[position];
            if (value == null || value.isEmpty())
                throw new IllegalArgumentException("Argument cannot be empty");
            else if (value.charAt(0) != '-') {
                command.addValueArgument(new ValueArgument(value, position));
                continue;
            }

            if (value.length() == 1 || value.charAt(1) == '-')
                throw new IllegalArgumentException(
                    String.format("[%s] (position %d) is not a valid parameter", value, position));
            var parameter = command.parseNewParameter(value.substring(1), position);
            command.addParameter(parameter);
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
        commandSuppliers.put("help", HelpCommand::new);
        commandSuppliers.put("exit", ExitCommand::new);
        commandSuppliers.put("add", AddCommand::new);
        commandSuppliers.put("let", LetCommand::new);

        commandSuppliers.forEach((key, supplier) -> commandPool.put(key, supplier.get()));
    }
}

