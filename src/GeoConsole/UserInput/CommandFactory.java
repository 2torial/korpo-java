package GeoConsole.UserInput;

import GeoConsole.UserInput.Commands.*;
import GeoConsole.UserInput.Commands.Figures.*;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;
import GeoConsole.UserInput.Exceptions.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public final class CommandFactory {
    static {
        Translator.save(Lang.PL, Identifier.ERR_UNKNOWN_COMMAND,
                "Nieznane polecenie: ");
        Translator.save(Lang.EN, Identifier.ERR_UNKNOWN_COMMAND,
                "Unknown command: ");

        Translator.save(Lang.PL, Identifier.ERR_EMPTY_ARGUMENT,
                "Argument nie może być pusty");
        Translator.save(Lang.EN, Identifier.ERR_EMPTY_ARGUMENT,
                "Argument cannot be empty");
    }
    private CommandFactory() {} // Static class

    public static Command parseCommand(String[] tokens) throws InvalidParameterException, DuplicateParameterException {
        String name = tokens[0];
        Supplier<Command> supplier = commandSuppliers.get(name);
        if (supplier == null)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_UNKNOWN_COMMAND) + name);
        var command = supplier.get();

        for (int position = 1; position < tokens.length; position++) {
            String token = tokens[position];
            if (token == null || token.isEmpty())
                throw new IllegalArgumentException(Translator.read(Identifier.ERR_EMPTY_ARGUMENT));
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
        commandSuppliers.put("square", SquareCommand::new);
        commandSuppliers.put("equilateraltriangle", EquilateralTriangleCommand::new);
        commandSuppliers.put("rectangle", RectangleCommand::new);
        commandSuppliers.put("rhombus", RhombusCommand::new);
        commandSuppliers.put("isoscelestriangle", IsoscelesTriangleCommand::new);
        commandSuppliers.put("circumcircle", CircumcircleCommand::new);
        commandSuppliers.put("circle", CircleCommand::new);
        commandSuppliers.put("triangle", TriangleCommand::new);
        commandSuppliers.put("righttriangle", RightTriangleCommand::new);
        commandSuppliers.put("double", DoubleFigureCommand::new);
        commandSuppliers.put("isoscelestrapezoid", IsoscelesTrapezoidCommand::new);
        commandSuppliers.put("save", SaveCommand::new);
        commandSuppliers.put("remove", RemoveCommand::new);
        commandSuppliers.put("ellipse", EllipseCommand::new);
        commandSuppliers.put("language", LanguageCommand::new);

        commandSuppliers.forEach((key, supplier) -> commandPool.put(key, supplier.get()));
    }
}