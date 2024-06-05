package GeoConsole.UserInput.Context;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArgumentsHandler {
    static {
        Translator.save(Lang.PL, Identifier.ERR_HANDLER_TOO_MANY_ARGUMENTS,
                "Zadeklarowano nie więcej niż %d argumentów");
        Translator.save(Lang.EN, Identifier.ERR_HANDLER_TOO_MANY_ARGUMENTS,
                "Only %d arguments were declared");

        Translator.save(Lang.PL, Identifier.ERR_HANDLER_ARGUMENT_UNMATCHED_NUMBER,
                "Liczba argumentów różni się od liczby handlerów");
        Translator.save(Lang.EN, Identifier.ERR_HANDLER_ARGUMENT_UNMATCHED_NUMBER,
                "Number of arguments does not match number of handlers");

        Translator.save(Lang.PL, Identifier.ERR_HANDLER_UNSPECIFIED_ARGUMENT_AT,
                "Niedozwolony argument na pozycji ");
        Translator.save(Lang.EN, Identifier.ERR_HANDLER_UNSPECIFIED_ARGUMENT_AT,
                "Unspecified argument at position ");


    }

    private final int numberOfArguments;
    private final List<Consumer<Argument>> handlers;

    public ArgumentsHandler(int numberOfArguments) {
        this.numberOfArguments = numberOfArguments;
        handlers = new ArrayList<>(numberOfArguments);
        for (int i = 0; i < numberOfArguments; i++) {
            int finalI = i+1;
            handlers.add(i, arg -> {throw new IllegalStateException(
                Translator.read(Identifier.ERR_HANDLER_UNSPECIFIED_ARGUMENT_AT) + finalI);});
        }
    }

    public void supply(int argumentPosition, Consumer<Argument> handler) {
        if (argumentPosition > numberOfArguments)
            throw new IllegalArgumentException(
                String.format(Translator.read(Identifier.ERR_HANDLER_TOO_MANY_ARGUMENTS), numberOfArguments));
        handlers.add(argumentPosition - 1, handler);
    }

    public void handleArguments(Argument[] arguments) {
        if (arguments.length != numberOfArguments)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_HANDLER_ARGUMENT_UNMATCHED_NUMBER));
        for (int i = 0; i < arguments.length; i++) {
            var argument = arguments[i];
            var handler = handlers.get(i);
            handler.accept(argument);
        }
    }
}
