package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.RightTriangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.ArgumentsHandler;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

public class RightTriangleCommand extends FigureCommand {
    @Override
    public String getName() {
        return "righttriangle";
    }

    @Override
    public String getHelp() {
        return "\tDeclares right triangle by given parameters (adjoining side [x2], hypotenuse side or area)";
    }

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    ArgumentsHandler handler = new ArgumentsHandler(2);
    int parameterPosition = 1;
    double adjoiningSideA = -1, adjoiningSideB = -1, hypotenuseSide = -1, area = -1;
    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "adj", "adjoining" -> argument.enforceRelativePosition(parameterPosition).setName("adjoining")
                .allowDuplicates(1)
                .supplyHandler(pos -> handler.supply(pos, arg -> {
                    if (adjoiningSideA < 0)
                        adjoiningSideA = arg.getNumericValue();
                    else
                        adjoiningSideB = arg.getNumericValue();
                }));
            case "hyp", "hypotenuse" -> argument.enforceRelativePosition(parameterPosition).setName("hypotenuse")
                .supplyHandler(pos -> handler.supply(pos, arg -> hypotenuseSide = arg.getNumericValue()));
            case "a", "area" -> argument.enforceRelativePosition(parameterPosition).setName("area")
                .supplyHandler(pos -> handler.supply(pos, arg -> area = arg.getNumericValue()));
            default -> super.supplyParameter(argument);
        }
        parameterPosition++;
    }

    @Override
    protected void handle(Argument[] arguments) {
        handler.handleArguments(arguments);
        RightTriangle triangle = new RightTriangle(adjoiningSideA, adjoiningSideB, hypotenuseSide, area);
        updateContext(triangle);
        triangle.print(roundTo);
    }
}
