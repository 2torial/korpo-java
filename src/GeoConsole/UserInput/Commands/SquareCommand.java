package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.Figure.Square;
import GeoConsole.UserInput.Context;

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
    Square square = new Square();
    boolean werePropertiesSet = false;
    @Override
    public void supplyParameter(Argument argument) {
        Consumer<Argument[]> sideHandler = args -> {
            parse(args, "side");
            square.setSide(readValue);
        };
        Consumer<Argument[]> diagonalHandler = args -> {
            parse(args, "diagonal");
            square.setDiagonal(readValue);
        };
        Consumer<Argument[]> areaHandler = args -> {
            parse(args, "area");
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
        if(werePropertiesSet) {
            square.print();
            Context.addFigure(square);
        }
        else
            throw new IllegalArgumentException("Unsupported command entered");
    }
}
