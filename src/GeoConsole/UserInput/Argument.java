package GeoConsole.UserInput;

public class Argument {
    public final String value;
    public final int position;
    public final boolean isParameter;

    protected Argument(String value, int position, boolean isParameter) {
        this.value = value;
        this.position = position;
        this.isParameter = isParameter;
    }

    @Override
    public String toString() {
        return value;
    }
}
