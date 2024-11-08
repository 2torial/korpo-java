package GeoConsole.UserInput;

import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;
import GeoConsole.UserInput.Exceptions.InvalidPositionException;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Argument {
    static {
        Translator.save(Lang.PL, Identifier.ERR_EMPTY_ARGUMENT_NAME,
                "Nazwa argumentu nie może być pusta");
        Translator.save(Lang.EN, Identifier.ERR_EMPTY_ARGUMENT_NAME,
                "Argument name cannot be empty");

        Translator.save(Lang.PL, Identifier.ERR_ENFORCED_POSITION_POSITIVE,
                "Wymuszanie pozycji przyjmuje wyłącznie liczby dodatnie");
        Translator.save(Lang.EN, Identifier.ERR_ENFORCED_POSITION_POSITIVE,
                "Enforced position must be a positive number");

        Translator.save(Lang.PL, Identifier.ERR_NON_PARAMETER_HANDLER,
                "Handler jest wspierany wyłącznie przez parametry");
        Translator.save(Lang.EN, Identifier.ERR_NON_PARAMETER_HANDLER,
                "Only parameters support handler");

        Translator.save(Lang.PL, Identifier.ERR_NON_PARAMETER_EXPECTED_ARGUMENT,
                "Wyłącznie parametry wspierają funkcję oczekiwanych argumentów");
        Translator.save(Lang.EN, Identifier.ERR_NON_PARAMETER_EXPECTED_ARGUMENT,
                "Only parameters support expected arguments");

        Translator.save(Lang.PL, Identifier.ERR_ROUNDTO_NOT_POSITIVE,
                "Argument roundTo nie może być ujemny");
        Translator.save(Lang.EN, Identifier.ERR_ROUNDTO_NOT_POSITIVE,
                "Argument roundTo must be positive or 0");

        Translator.save(Lang.PL, Identifier.ERR_NULL_HANDLER,
                "Handler nie może być nullem");
        Translator.save(Lang.EN, Identifier.ERR_NULL_HANDLER,
                "Handler cannot be null");

        Translator.save(Lang.PL, Identifier.ERR_EXPECTED_ARGUMENTS_NEGATIVE,
                "Liczba oczekiwanych argumentów nie może być ujemna");
        Translator.save(Lang.EN, Identifier.ERR_EXPECTED_ARGUMENTS_NEGATIVE,
                "Number of expected arguments cannot be negative");

        Translator.save(Lang.PL, Identifier.ERR_DUPLICATE_ARGUMENTS_NEGATIVE,
                "Liczba dozwolonych duplikatów parametrów nie może być ujemna");
        Translator.save(Lang.EN, Identifier.ERR_DUPLICATE_ARGUMENTS_NEGATIVE,
                "Number of allowed duplicate parameters must be positive");

        Translator.save(Lang.PL, Identifier.ERR_NON_PARAMETER_ARGUMENT_DUPLICATION,
                "Wyłącznie parametry wspierają duplikację");
        Translator.save(Lang.EN, Identifier.ERR_NON_PARAMETER_ARGUMENT_DUPLICATION,
                "Only parameters support allowing duplication");

    }

    private String name;
    public final String rawValue;
    public final int absolutePosition;
    private int relativePosition;
    private int enforcedPosition = -1;
    public final boolean isParameter;
    private InvalidPositionException positionException;

    private int allowedDuplicates = 0;
    private int expectedSubArguments = 0;
    private BiConsumer<Argument[], Integer> handler = (args, pos) -> {};
    public Argument(String value, int position, boolean isParameter) {
        this.name = value.toLowerCase();
        this.rawValue = value;
        this.absolutePosition = position;
        this.isParameter = isParameter;
    }

    public Argument setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_EMPTY_ARGUMENT_NAME));
        this.name = name.toLowerCase();
        return this;
    }

    public void setRelativePosition(int position) {
        if (enforcedPosition > 0 && enforcedPosition != position)
            positionException = new InvalidPositionException(this);
        this.relativePosition = position;
    }
    public int getRelativePosition() {
        return this.relativePosition;
    }

    public Argument enforceRelativePosition(int position) {
        if (position < 0)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_ENFORCED_POSITION_POSITIVE));
        enforcedPosition = position;
        return this;
    }
    public int getEnforcedPosition() {
        return this.enforcedPosition;
    }
    public void throwIfInvalid() throws InvalidPositionException {
        if (positionException != null)
            throw positionException;
    }

    public Argument allowDuplicates(int number) {
        if (!isParameter)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_NON_PARAMETER_ARGUMENT_DUPLICATION));
        if (number <= 0)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_DUPLICATE_ARGUMENTS_NEGATIVE));
        allowedDuplicates = number;
        return this;
    }
    public int getNumberOfAllowedDuplicates() {
        return allowedDuplicates;
    }

    public void supplyHandler(int numberOfSubArguments, BiConsumer<Argument[], Integer> handler) {
        if (numberOfSubArguments < 0)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_EXPECTED_ARGUMENTS_NEGATIVE));
        expectedSubArguments = numberOfSubArguments;
        if (handler == null)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_NULL_HANDLER));
        this.handler = handler;
    }
    public void supplyHandler(Consumer<Integer> handler) {
        supplyHandler(0, (args, pos) -> handler.accept(pos));
    }

    public double getNumericValue(int roundTo) {
        if (roundTo < 0)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_ROUNDTO_NOT_POSITIVE));
        double value = Double.parseDouble(rawValue);
        if (roundTo == 0)
            return value;
        double rounder = Math.pow(10, roundTo);
        return Math.round(value * rounder) / rounder;
    }
    public double getNumericValue() {
        return getNumericValue(0);
    }
    public int getIntegerValue() {
        return Integer.parseInt(rawValue);
    }

    public int getNumberOfSubArguments() {
        if (!isParameter)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_NON_PARAMETER_EXPECTED_ARGUMENT));
        return expectedSubArguments;
    }

    public BiConsumer<Argument[], Integer> getHandler() {
        if (!isParameter)
            throw new IllegalArgumentException(Translator.read(Identifier.ERR_NON_PARAMETER_HANDLER));
        return handler;
    }

    @Override
    public String toString() {
        return (isParameter ? "--" : "") + name;
    }
}
