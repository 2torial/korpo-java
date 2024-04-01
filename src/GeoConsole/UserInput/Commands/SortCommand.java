package GeoConsole.UserInput.Commands;

import GeoConsole.Figure.*;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context;

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
            -area -> area of a figure""";
    }

    private String sorttype = "";

    @Override
    public void supplyParameter(Argument argument) {
        switch (argument.getValue()) {
            case "a", "area" -> argument
                    .supplyValue("area")
                    .supplyHandler(args -> sorttype = "area");
            default -> super.supplyParameter(argument);
        }
    }

    private void sortByArea() {
        List<Figure> figurelist = Context.getFigureList();

        Collections.sort(figurelist, new Comparator<Figure>() {
            public int compare(Figure f1, Figure f2) {
                if (f1.getArea() == f2.getArea())
                    return 0;
                return (f1.getArea() < f2.getArea()) ? -1 : 1;
            }
        });

        for( var f : figurelist ){
            f.print();
        }
    }

    @Override
    protected void handle(List<Argument> arguments) {
        switch (sorttype) {
            case "area" -> sortByArea();
            default -> throw new IllegalArgumentException("Unsupported command entered");
        }
    }
}
