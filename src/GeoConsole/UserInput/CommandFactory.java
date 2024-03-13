package GeoConsole.UserInput;

import GeoConsole.UserInput.Commands.AddCommand;
import GeoConsole.UserInput.Commands.ExitCommand;
import GeoConsole.UserInput.Commands.HelpCommand;
import GeoConsole.UserInput.Commands.VersionCommand;
import GeoConsole.UserInput.Exceptions.UnknownCommandName;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public final class CommandFactory {
    private CommandFactory() {} // Static class

    public static Command createCommand(String name) {
        Supplier<Command> supplier = commandSuppliers.get(name);
        if (supplier == null)
            throw new UnknownCommandName(name);
        return supplier.get();
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

        commandSuppliers.forEach((key, supplier) -> commandPool.put(key, supplier.get()));
    }
}

