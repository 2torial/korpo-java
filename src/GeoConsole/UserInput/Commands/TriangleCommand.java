package GeoConsole.UserInput.Commands;

import GeoConsole.Figure.Triangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;

import java.util.List;
import java.util.function.Consumer;

public class TriangleCommand extends Command {
    @Override
    public String getName() {
        return "triangle";
    }

    @Override
    public String getHelp() {
        return "\tDeclares equilateral triangle by given parameter (side, height or area)";
    }

    @Override
    public int getNumberOfArguments() {
        return -1;
    }

    double readValue;
    Triangle triangle = new Triangle();
    @Override
    public void supplyParameter(Argument argument) {
        Consumer<Argument[]> sideHandler = args -> {
            try {
                readValue = Double.parseDouble(args[0].getValue());
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to parse the side length");
            }
            triangle.setSide(readValue);
        };
        Consumer<Argument[]> heightHandler = args -> {
            try {
                readValue = Double.parseDouble(args[0].getValue());
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to parse the height length");
            }
            triangle.setDiagonal(readValue);
        };
        Consumer<Argument[]> areaHandler = args -> {
            try {
                readValue = Double.parseDouble(args[0].getValue());
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to parse the area length");
            }
            triangle.setArea(readValue);
        };
        switch (argument.getValue()) {
            case "side" -> argument.supplyValue("side").supplyExpectations(1)
                    .supplyHandler(sideHandler);
            case "height" -> argument.supplyValue("height").supplyExpectations(1)
                    .supplyHandler(heightHandler);
            case "area" -> argument.supplyValue("area").supplyExpectations(1)
                    .supplyHandler(areaHandler);
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(List<Argument> arguments) {
        triangle.print();
    }
}
