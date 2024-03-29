package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Triangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Exceptions.*;

public class TriangleCommand extends Command {
    @Override
    public String getName() {
        return "triangle";
    }

    @Override
    public String getHelp() {
        return "\tDeclares equilateral triangle by given parameter (side, height or area)";
    }

    Triangle triangle = new Triangle();
    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(1)
                .supplyFinalizer(args -> triangle.setSide(args[0].getNumericValue()));
            case "height" -> argument.enforceRelativePosition(1)
                .supplyFinalizer(args -> triangle.setHeight(args[0].getNumericValue()));
            case "area" -> argument.enforceRelativePosition(1)
                .supplyFinalizer(args -> triangle.setArea(args[0].getNumericValue()));
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void finalize(Argument[] arguments) {
        triangle.print();
    }
}
