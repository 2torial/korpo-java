package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.Figure.Square;

import java.util.List;
import java.util.function.Consumer;

public class SquareCommand extends Command {
    @Override
    public String getName() {
        return "square";
    }

    @Override
    public String getHelp() {
        return "\tDeclares square by given parameter (side, diagonal or area)";
    }

    @Override
    public int getNumberOfArguments() {
        return -1;
    }

    double readValue;
    Square square = new Square();
    @Override
    public void supplyParameter(Argument argument) {
        Consumer<Argument[]> sideHandler = args -> {
            try {
                readValue = Double.parseDouble(args[0].getValue());
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to parse the side length");
            }
            square.setSide(readValue);
        };
        Consumer<Argument[]> diagonalHandler = args -> {
            try {
                readValue = Double.parseDouble(args[0].getValue());
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to parse the diagonal length");
            }
            square.setDiagonal(readValue);
        };
        Consumer<Argument[]> areaHandler = args -> {
            try {
                readValue = Double.parseDouble(args[0].getValue());
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to parse the area length");
            }
            square.setArea(readValue);
        };
        switch (argument.getValue()) {
            case "side" -> argument.supplyValue("side").supplyExpectations(1)
                    .supplyHandler(sideHandler);
            case "diagonal" -> argument.supplyValue("diagonal").supplyExpectations(1)
                    .supplyHandler(diagonalHandler);
            case "area" -> argument.supplyValue("area").supplyExpectations(1)
                    .supplyHandler(areaHandler);
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(List<Argument> arguments) {
        square.print();
    }
}
