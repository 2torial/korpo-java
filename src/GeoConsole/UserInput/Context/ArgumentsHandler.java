package GeoConsole.UserInput.Context;

import GeoConsole.UserInput.Argument;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArgumentsHandler {
    private final int numberOfArguments;
    private final List<Consumer<Argument>> handlers;

    public ArgumentsHandler(int numberOfArguments) {
        this.numberOfArguments = numberOfArguments;
        handlers = new ArrayList<>(numberOfArguments);
        for (int i = 0; i < numberOfArguments; i++) {
            int finalI = i+1;
            handlers.add(i, arg -> {throw new IllegalStateException("Unspecified argument at position " + finalI);});
        }
    }

    public void supply(int argumentPosition, Consumer<Argument> handler) {
        if (argumentPosition > numberOfArguments)
            throw new IllegalArgumentException("Only " + numberOfArguments + " arguments were declared");
        handlers.add(argumentPosition - 1, handler);
    }

    public void handleArguments(Argument[] arguments) {
        if (arguments.length != numberOfArguments)
            throw new IllegalArgumentException("Number of arguments does not match number of handlers");
        for (int i = 0; i < arguments.length; i++) {
            var argument = arguments[i];
            var handler = handlers.get(i);
            handler.accept(argument);
        }
    }
}
