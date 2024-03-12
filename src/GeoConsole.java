import ConsoleInput.Command;
import ConsoleInput.UserInput;

public class GeoConsole{
    private final static String version = "0.0.1";

    public GeoConsole() {}

    public static String getVersion() {
        return String.format("GeoConsole ver %s\nRelease date: 12.03.2024\n", version);
    }

    public static String getHelp() {
        var builder = new StringBuilder();
        for (var command : Command.values()) {
            builder.append(String.format("%s\t%s\n", command.value(), command.help()));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        while (true) {
            try {
                var input = UserInput.interpret();
                if (input.command == Command.EXIT)
                    return;
                switch (input.command) {
                    case Command.VERSION -> System.out.println(getVersion());
                    case Command.HELP -> System.out.println(getHelp());
                }
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}