package GeoConsole.UserInput.Arguments;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Exceptions.NotIntegerArgument;
import GeoConsole.UserInput.Exceptions.NotNumericArgument;

public class ValueArgument extends Argument {
    public ValueArgument(String value, int position) {
        super(value, position, false);
    }

    public int parseValueInt() throws NotIntegerArgument {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NotIntegerArgument(this);
        }
    }

    public double parseValueDouble() throws NotNumericArgument {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new NotNumericArgument(this);
        }
    }

}
