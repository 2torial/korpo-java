package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Figure;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public abstract class FigureCommand extends Command {
    protected int roundTo = 2;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "r", "round" -> argument.setName("round")
                .enforceRelativePosition(getNumberOfArguments() + 1)
                .supplyHandler(1, (args, pos) -> {
                    roundTo = args[0].getIntegerValue();
                    if (roundTo < 0)
                        throw new IllegalArgumentException("Rounding argument must be a positive number");
                });
            case "e", "exact" -> argument.setName("round/exact")
                .enforceRelativePosition(getNumberOfArguments() + 1)
                .supplyHandler(pos -> roundTo = -1);
            default -> super.supplyParameter(argument);
        }
    }

    protected void updateContext(Figure figure) {
        var figureClassPair = figure.simplify();
        var simplifiedFigure = figureClassPair == null ? figure : figureClassPair.first();
        var type = figureClassPair == null ? figure.getClass() : figureClassPair.second();
        boolean isDuplicate = false;
        for (var fig : Context.getFigureList()) {
            try {
                if (type.cast(fig).equals(simplifiedFigure)) {
                    isDuplicate = true;
                    break;
                }
            } catch (Exception ignore) {}
        }

        if (!isDuplicate)
            Context.addFigure(simplifiedFigure);
        else
            System.out.println("Duplicate:");
        simplifiedFigure.print(roundTo);
    }
}
