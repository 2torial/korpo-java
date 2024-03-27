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

    private void parse(Argument[] args,String name)
    {
        try {
            readValue = Double.parseDouble(args[0].getValue());
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse the ".concat(name));
        }
        if(readValue <= 0.0)
            throw new IllegalArgumentException("The ".concat(name).concat(" has to be greater than 0"));
        werePropertiesSet = true;
    }

    double readValue;
    boolean werePropertiesSet = false;
    Triangle triangle = new Triangle();
    @Override
    public void supplyParameter(Argument argument) {
        Consumer<Argument[]> sideHandler = args -> {
            parse(args, "side");
            triangle.setSide(readValue);
        };
        Consumer<Argument[]> heightHandler = args -> {
            parse(args, "height");
            triangle.setDiagonal(readValue);
        };
        Consumer<Argument[]> areaHandler = args -> {
            parse(args, "area");
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
        if(werePropertiesSet)
            triangle.print();
        else
            throw new IllegalArgumentException("Unsupported command entered");
    }
}
