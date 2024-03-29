import GeoConsole.GeoConsole;
import GeoConsole.UserInput.Exceptions.InvalidNumberOfArguments;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class Main {
    public static void main(String[] args) {
        var console = new GeoConsole();
        while (!console.isExited()) {
            try {
                console.handleInput();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
