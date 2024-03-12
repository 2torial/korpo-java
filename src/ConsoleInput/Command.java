package ConsoleInput;

public enum Command {
    VERSION("version", "Displays program version"),
    HELP("help", "Displays available commands information"),
    EXIT("exit", "Exits program");

    private final String value;
    private final String info;

    Command(String value, String info) {
        this.value = value;
        this.info = info;
    }

    public String value() {
        return value;
    }

    public String help() {
        return info;
    }

    public static Command find(String input) {
        for (var command : Command.values())
            if (input.equalsIgnoreCase(command.name()))
                return command;
        throw new IllegalArgumentException(String.format("[%s] is not recognized as a valid command", input));
    }
}