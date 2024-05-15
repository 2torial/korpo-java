package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.AnyTriangle;
import GeoConsole.UserInput.Argument;

public class TriangleCommand extends FigureCommand {
    @Override
    public String getName() {
        return "triangle";
    }

    @Override
    public String getHelp() {
        return "\tDeclares triangle by 3 sides";
    }

    @Override
    public int getNumberOfArguments() {
        return 3;
    }


    @Override
    protected void handle(Argument[] arguments) {
        AnyTriangle triangle = new AnyTriangle(arguments[0].getNumericValue(), arguments[1].getNumericValue(), arguments[2].getNumericValue());
        updateContext(triangle, AnyTriangle.class);
        triangle.print(roundTo);
    }
}
