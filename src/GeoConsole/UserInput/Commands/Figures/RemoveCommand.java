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

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        super.supplyParameter(argument);
    }

    @Override
    protected void handle(Argument[] arguments) {
        try {
            Context.removeFigure(arguments[0].getIntegerValue());
            System.out.println("Figure removed successfully");
        } catch (Exception ignored) {
            System.out.println("Invalid input – figure not found");
        }
    }
}
