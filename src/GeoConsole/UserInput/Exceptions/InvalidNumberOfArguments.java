package GeoConsole.UserInput.Exceptions;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

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
            .append(Translator.getLanguage() == Lang.EN ? "Parameter " : "Parametr ")
            .append(parameter)
            .append(Translator.getLanguage() == Lang.EN ? " expects " : " oczekuje ")
            .append(parameter.getNumberOfSubArguments());
        else builder
            .append(Translator.getLanguage() == Lang.EN ? "Command " : "Polecenie ")
            .append(command.getName())
            .append(Translator.getLanguage() == Lang.EN ? " expects " : " oczekuje ")
            .append(command.getNumberOfArguments());
        builder
            .append(Translator.getLanguage() == Lang.EN ? " arguments, recieved " : " argumentów, podano ")
            .append(passedArguments);
        return builder.toString();
    }
}
