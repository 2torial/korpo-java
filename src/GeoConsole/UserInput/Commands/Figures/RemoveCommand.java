package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class RemoveCommand extends FigureCommand {
    @Override
    public String getName() {
        return "square";
    }

    @Override
    public String getHelp() {
        return "\tRemove figure from the list (selected by id/name)";
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    boolean byId = false;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "id" -> argument.enforceRelativePosition(1).setName("id/name")
                .supplyHandler(pos -> byId = true);
            case "name" -> argument.enforceRelativePosition(1).setName("id/name")
                .supplyHandler(pos -> byId = false);
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        if (byId)
            arguments[0].getIntegerValue();
        try {
            Context.removeFigure(arguments[0].rawValue);
            System.out.println("Figure removed successfully");
        } catch (Exception ignored) {
            System.out.println("Invalid input – figure not found");
        }
    }
}
