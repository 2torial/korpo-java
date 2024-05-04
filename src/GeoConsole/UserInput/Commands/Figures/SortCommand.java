package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.*;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.UserInput.Exceptions.InvalidParameterException;

import java.text.SimpleDateFormat;
import java.util.Collections;

import java.util.Comparator;
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
            -perimeter -> perimeter of a figure
            -date -> figure's date of creation""";
    }

    private Order sortOrder = Order.ASC;
    private int roundTo = 2;
    private Comparator<Figure> comparator = Comparator.comparing(Figure::getDateOfCreation);

    @Override
    public void supplyParameter(Argument argument) throws InvalidParameterException {
        switch (argument.rawValue) {
            case "a", "area" -> argument.setName("area/perimeter/date")
                .supplyHandler(pos -> comparator = Comparator.comparingDouble(Figure::getArea));
            case "p", "peri", "perimeter" -> argument.setName("area/perimeter/date")
                .supplyHandler(pos -> comparator = Comparator.comparingDouble(Figure::getPerimeter));
            case "d", "date" -> argument.setName("area/perimeter/date")
                .supplyHandler(pos -> comparator = Comparator.comparing(Figure::getDateOfCreation));
            case "asc", "ascending" -> argument.setName("ascending/descending")
                .supplyHandler(pos -> sortOrder = Order.ASC);
            case "desc", "descending" -> argument.setName("ascending/descending")
                .supplyHandler(pos -> sortOrder = Order.DESC);
            case "r", "round" -> argument.setName("round").supplyHandler(1, (args, pos) -> {
                roundTo = args[0].getIntegerValue();
                if (roundTo < 0)
                    throw new IllegalArgumentException("Rounding argument must be a positive number");
            });
            default -> super.supplyParameter(argument);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        List<Figure> figureList = Context.getFigureList();
        figureList.sort(comparator);

        int counter = 1;
        if(sortOrder == Order.DESC){
            Collections.reverse(figureList);
        }
        if (figureList.isEmpty())
            System.out.println("No figures");
        else for (var f : figureList) {
            System.out.printf("%d. ", counter ++);
            f.print(roundTo);
            System.out.printf("Date of creation: %s\n\n", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(f.getDateOfCreation()));
        }
    }
}
