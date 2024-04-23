package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.*;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;
import java.util.Collections;

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
            -area -> area of a figure
            -perimeter -> perimeter of a figure""";
    }

    private Attribute sortType = Attribute.NIL;
    private Order sortOrder = Order.ASC;

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "a", "area" -> argument.setName("area")
                    .supplyHandler(pos -> sortType = Attribute.AREA);
            case "p", "peri", "perimeter" -> argument.setName("perimeter")
                    .supplyHandler(pos -> sortType = Attribute.PERIMETER);
            case "asc", "ascending" -> argument.setName("ascending")
                    .supplyHandler(pos -> sortOrder = Order.ASC);
            case "desc", "descending" -> argument.setName("descending")
                    .supplyHandler(pos -> sortOrder = Order.DESC);
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        List<Figure> figureList = Context.getFigureList();
        switch (sortType) {
            case Attribute.AREA -> sortByArea(figureList);
            case Attribute.PERIMETER -> sortByPerimeter(figureList);
            default -> throw new IllegalArgumentException("Unsupported comparator");
        }

        int counter = 1;
        if(sortOrder == Order.DESC){
            Collections.reverse(figureList);
        }
        for( var f : figureList ){
            System.out.printf("%d. ", counter ++);
            f.print();
        }
    }

    private void sortByArea(List<Figure> figureList) {
        figureList.sort((f1, f2) -> {
            if (f1.getArea() == f2.getArea())
                return 0;
            return (f1.getArea() < f2.getArea()) ? -1 : 1;
        });
    }

    private void sortByPerimeter(List<Figure> figureList) {
        figureList.sort((f1, f2) -> {
            if (f1.getPerimeter() == f2.getPerimeter())
                return 0;
            return (f1.getPerimeter() < f2.getPerimeter()) ? -1 : 1;
        });
    }
}
