package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Rectangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Exceptions.*;

public class RectangleCommand extends Command {
    @Override
    public String getName() {
        return "rectangle";
    }

    @Override
    public String getHelp() {
        return "\tDeclares rectangle ...";
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    Rectangle rectangle = new Rectangle();
    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(state.readAndIncrement()).allowDuplicates(1)
                .supplyFinalizer(args -> rectangle.setSide(args[state.readAndIncrement()].getNumericValue()));
            case "diag" -> argument.enforceRelativePosition(state.readAndIncrement())
                .supplyFinalizer(args -> rectangle.setDiagonal(args[state.readAndIncrement()].getNumericValue()));
            case "area" -> argument.enforceRelativePosition(state.readAndIncrement())
                .supplyFinalizer(args -> rectangle.setArea(args[state.readAndIncrement()].getNumericValue()));
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        state.reset();
    }

    @Override
    protected void finalize(Argument[] arguments) {
        rectangle.print();
    }
}
