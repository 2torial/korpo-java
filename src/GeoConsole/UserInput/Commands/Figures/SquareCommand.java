package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.Figure.Square;
import GeoConsole.UserInput.Exceptions.*;

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
        return 1;
    }

    Square square = new Square();
    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(1)
                .supplyFinalizer(args -> square.setSide(args[0].getNumericValue()));
            case "diagonal" -> argument.enforceRelativePosition(1)
                .supplyFinalizer(args -> square.setDiagonal(args[0].getNumericValue()));
            case "area" -> argument.enforceRelativePosition(1)
                .supplyFinalizer(args -> square.setArea(args[0].getNumericValue()));
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void finalize(Argument[] arguments) {
        square.print();
    }
}
