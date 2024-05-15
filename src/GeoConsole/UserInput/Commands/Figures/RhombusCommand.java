package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Rhombus;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Exceptions.*;

public class RhombusCommand extends FigureCommand {
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

    double side = -1, diagonalA = -1, diagonalB = -1, area = -1;
    ArgumentsHandler handler = new ArgumentsHandler(2);
    int parameterPosition = 1;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> side = arg.getNumericValue()));
            case "diag", "diagonal" -> argument.setName("diagonal")
                .enforceRelativePosition(parameterPosition)
                .allowDuplicates(1)
                .supplyHandler(pos -> handler.supply(pos, arg -> {
                    if (diagonalA < 0)
                        diagonalA = arg.getNumericValue();
                    else
                        diagonalB = arg.getNumericValue();
                }));
            case "area" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> handler.supply(pos, arg -> area = arg.getNumericValue()));
            default -> super.supplyParameter(argument);
        }
        parameterPosition++;
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);
        Rhombus rhombus = new Rhombus(side, diagonalA, diagonalB, area);
        updateContext(rhombus, Rhombus.class);
        rhombus.print(roundTo);
    }
}
