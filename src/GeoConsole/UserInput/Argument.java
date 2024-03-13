package GeoConsole.UserInput;

public class Argument {
    public final String value;
    public final boolean isParameter;
    public final int position;

    public Argument(String value, int position) {
        this.position = position;
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Argument cannot be empty");
        } else if (value.charAt(0) != '-') {
            isParameter = false;
            this.value = value;
        } else if (value.length() == 1 || value.charAt(1) == '-') {
            throw new IllegalArgumentException(String.format("[%s] is not a valid parameter", value));
        } else {
            isParameter = true;
            this.value = value.substring(1);
        }
    }
}
