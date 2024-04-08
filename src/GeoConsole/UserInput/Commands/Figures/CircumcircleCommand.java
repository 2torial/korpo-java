package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.*;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class CircumcircleCommand extends Command {
    @Override
    public String getName() {
        return "circumcircle";
    }

    @Override
    public String getHelp() {
        return "\tDeclares circle described on figure specified by ID";
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    int providedId;
    ArgumentsHandler handler = new ArgumentsHandler(1);

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "id" -> argument.enforceRelativePosition(1)
                    .supplyHandler(pos -> handler.supply(pos, arg -> providedId = arg.getIntegerValue()));
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);

        Figure fig = Context.findFigureWithId(providedId);
        Circle circle = fig.getCircumcircle();
        Context.addFigure(circle);
        circle.print();
    }
}
