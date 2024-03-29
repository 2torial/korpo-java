package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Rhombus;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Exceptions.*;

public class RhombCommand extends Command {
    @Override
    public String getName() {
        return "rhombus";
    }

    @Override
    public String getHelp() {
        return "\tDeclares rhombus ...";
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    Rhombus rhombus = new Rhombus();
    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(state.readAndIncrement())
                .supplyFinalizer(args -> rhombus.setSide(args[state.readAndIncrement()].getNumericValue()));
            case "diag" -> argument.enforceRelativePosition(state.readAndIncrement()).allowDuplicates(1)
                .supplyFinalizer(args -> rhombus.setDiagonal(args[state.readAndIncrement()].getNumericValue()));
            case "area" -> argument.enforceRelativePosition(state.readAndIncrement())
                .supplyFinalizer(args -> rhombus.setArea(args[state.readAndIncrement()].getNumericValue()));
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        state.reset();
    }

    @Override
    protected void finalize(Argument[] arguments) {
        rhombus.print();
    }
}
