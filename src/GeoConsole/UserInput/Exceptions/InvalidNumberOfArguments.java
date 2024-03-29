package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;

public class InvalidNumberOfArguments extends Exception {
    int passedArguments;
    Command command;
    Argument parameter;

    public InvalidNumberOfArguments(int passedArguments, Argument parameter) {
        this.passedArguments = passedArguments;
        this.parameter = parameter;
    }

    public InvalidNumberOfArguments(int passedArguments, Command command) {
        this.passedArguments = passedArguments;
        this.command = command;
    }

    @Override
    public String getMessage() {
        StringBuilder builder = new StringBuilder();
        if (parameter != null) builder
            .append("Parameter ")
            .append(parameter)
            .append(" expects ")
            .append(parameter.getNumberOfSubArguments());
        else builder
            .append("Command ")
            .append(command.getName())
            .append(" expects ")
            .append(command.getNumberOfArguments());
        builder
            .append(" arguments, ")
            .append(passedArguments)
            .append(" were given");
        return builder.toString();
    }
}
