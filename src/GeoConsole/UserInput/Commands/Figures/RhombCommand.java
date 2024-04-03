package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.Attribute;
import GeoConsole.Figure.Rhombus;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.*;

import java.util.ArrayList;
import java.util.List;

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
    Attribute[] actions = {Attribute.NIL, Attribute.NIL};
    int parameterPosition = 1;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "side" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> actions[pos-1] = Attribute.SIDE);
            case "diag", "diagonal" -> argument.setName("diagonal")
                .enforceRelativePosition(parameterPosition)
                .allowDuplicates(1)
                .supplyHandler(pos -> actions[pos-1] = Attribute.DIAGONAL);
            case "area" -> argument.enforceRelativePosition(parameterPosition)
                .supplyHandler(pos -> actions[pos-1] = Attribute.AREA);
            default -> super.supplyParameter(argument);
        }
        parameterPosition++;
    }

    @Override
    protected void handle(Argument[] arguments) {
        for (int i = 0; i < 2; i++) {
            var action = actions[i];
            switch (action) {
                case Attribute.SIDE -> rhombus.setSide(arguments[i].getNumericValue());
                case Attribute.DIAGONAL -> rhombus.setDiagonal(arguments[i].getNumericValue());
                case Attribute.AREA -> rhombus.setArea(arguments[i].getNumericValue());
                default -> throw new IllegalStateException("Unspecified argument at position: " + (i+1));
            }
        }
        rhombus.print();
        Context.addFigure(rhombus);
    }
}
