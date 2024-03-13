import GeoConsole.GeoConsole;

public class Main {
    public static void main(String[] args) {
        var console = new GeoConsole();
        while (!console.isExited()) {
            try {
                console.handleInput();
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
