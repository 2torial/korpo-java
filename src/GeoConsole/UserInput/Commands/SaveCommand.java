package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.Figure.Figure;
import GeoConsole.UserInput.Exceptions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class SaveCommand extends Command {
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getHelp() {
        return "\tSaves all figures to a file with specified name";
    }

    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    @Override
    public String getExtendedHelp() {
        return """
            Saves all figures to specified file
            Pattern: let name
            Arguments:
              name\tName of the file""";
    }


    @Override
    protected void handle(Argument[] arguments) {
        String filename = arguments[0].rawValue;

        List<String> figureDescriptions = new LinkedList<>();
        for( Figure f : Context.getFigureList() ) {
            figureDescriptions.add(f.getDescription(2));
        }

        Path filePath = Paths.get(filename);
        try {
            Files.write(filePath, figureDescriptions, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
