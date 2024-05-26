package GeoConsole.UserInput.Commands;

import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Command;
import GeoConsole.UserInput.Context.Context;
import GeoConsole.Figure.Figure;
import GeoConsole.UserInput.Exceptions.*;
import com.google.gson.Gson;

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

    private void fileSaving(Path filePath, List<String> lines) {
        try {
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void handle(Argument[] arguments) {
        String filename = arguments[0].rawValue;

        Gson gson = new Gson();

        List<String> figureDescriptions = new LinkedList<>();
        for( Figure f : Context.getFigureList() ) {
            figureDescriptions.add(gson.toJson(f));
        }

        Path filePath = Paths.get(filename);

        Runnable saveTask = () -> fileSaving(filePath, figureDescriptions);

        Thread thread = new Thread(saveTask);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("file saved!");
    }
}
