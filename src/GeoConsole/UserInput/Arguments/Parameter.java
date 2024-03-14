package GeoConsole.UserInput.Arguments;

import GeoConsole.UserInput.Argument;

public class Parameter extends Argument {
    public final int expectedArguments;
    private int givenArguments;

    public Parameter(String value, int position, int expectedArguments) {
        super(value, position, true);
        this.expectedArguments = expectedArguments;
    }

    public boolean isExpectingArgument() {
        return givenArguments < expectedArguments;
    }
    public int getGivenArguments() {
        return givenArguments;
    }
    public void fulfillExpectation() {
        givenArguments++;
    }

    @Override
    public String toString() {
        return "-"+value;
    }
}
