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

    protected <T extends Figure> void updateContext(T figure, Class<? extends Figure> type) {
        for (var fig : Context.getFigureList()) {
            try {
                if (type.cast(fig).equals(figure))
                    return;
            } catch (Exception ignore) {}
        }
        Context.addFigure(figure);
    }
}
