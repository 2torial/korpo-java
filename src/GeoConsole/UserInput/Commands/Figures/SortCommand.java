package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.*;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

import java.util.List;

public class SortCommand extends Command {
    @Override
    public String getName() {
        return "sort";
    }

    @Override
    public String getHelp() {
        return "\tSorts all saved figures";
    }

    @Override
    public int getNumberOfArguments() {
        return -1;
    }

    @Override
    public String getExtendedHelp() {
        return """
            Sorts and prints all saved figures with respect to one of following arguments:
            -area -> area of a figure""";
    }

    private Attribute sortType = Attribute.NIL;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "a", "area" -> argument.setName("area")
                .supplyHandler(pos -> sortType = Attribute.AREA);
            default -> super.supplyParameter(argument);
        }
    }

    private void sortByArea() {
        List<Figure> figureList = Context.getFigureList();

        figureList.sort((f1, f2) -> {
            if (f1.getArea() == f2.getArea())
                return 0;
            return (f1.getArea() < f2.getArea()) ? -1 : 1;
        });

        int counter = 1;
        for( var f : figureList ){
            System.out.printf("%d. ", counter ++);
            f.print();
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        switch (sortType) {
            case Attribute.AREA -> sortByArea();
            default -> throw new IllegalArgumentException("Unsupported comparator");
        }
    }
}
