package GeoConsole.UserInput.Exceptions;

public class UnknownCommandName extends IllegalArgumentException {
    private final String commandName;
    public UnknownCommandName(String commandName) {
        this.commandName = commandName;
    }
    @Override
    public String getMessage() {
        return String.format("[%s] cannot be recognized as a valid command", commandName);
    }
}
