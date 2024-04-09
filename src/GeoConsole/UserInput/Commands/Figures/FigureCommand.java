package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Figure;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public abstract class FigureCommand extends Command {
    private String figureName;
    protected boolean save = true;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "name" -> argument.enforceRelativePosition(getNumberOfArguments() + 1).setName("id/name")
                .supplyHandler(1, (args, pos) -> figureName = args[0].rawValue);
            case "print" -> argument.enforceRelativePosition(getNumberOfArguments() + 1).setName("save/print")
                .supplyHandler(pos -> save = false);
            case "save" -> argument.enforceRelativePosition(getNumberOfArguments() + 1).setName("save/print")
                .supplyHandler(pos -> save = true);
            default -> super.supplyParameter(argument);
        }
    }

    protected void updateContext(Figure figure) {
        if (!save)
            return;
        if (figureName == null)
            Context.addFigure(figure);
        else Context.addFigure(figureName, figure);
    }
}
