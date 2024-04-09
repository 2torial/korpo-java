package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Rhombus;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Exceptions.*;

public class RhombCommand extends FigureCommand {
    @Override
    public String getName() {
        return "rhombus";
    }

    @Override
    public String getHelp() {
        return "\tDeclares rhombus by 2 given parameters (side, diagonal or area)\nDiagonal can be passed twice";
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    Rhombus rhombus = new Rhombus();
    ArgumentsHandler handler = new ArgumentsHandler(2);
    int parameterPosition = 1;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> rhombus.setSide(arg.getNumericValue())));
            case "diag", "diagonal" -> argument.setName("diagonal")
                .enforceRelativePosition(parameterPosition)
                .allowDuplicates(1)
                .supplyHandler(pos -> handler.supply(pos, arg -> rhombus.setDiagonal(arg.getNumericValue())));
            case "area" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> rhombus.setArea(arg.getNumericValue())));
            default -> super.supplyParameter(argument);
        }
        parameterPosition++;
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);
        updateContext(rhombus);
        rhombus.print();
    }
}
