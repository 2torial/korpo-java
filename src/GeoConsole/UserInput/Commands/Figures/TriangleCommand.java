package GeoConsole.UserInput.Commands.Figures;

import GeoConsole.Figure.AnyTriangle;
import GeoConsole.UserInput.Argument;
import GeoConsole.UserInput.Context.Translator.Identifier;
import GeoConsole.UserInput.Context.Translator.Lang;
import GeoConsole.UserInput.Context.Translator.Translator;

public class TriangleCommand extends FigureCommand {
    static {
        Translator.save(Lang.PL, Identifier.COM_TRIANGLE_DESCRIPTION,
                "\tDeklaruje trójkąt z podanymi trzema bokami");
        Translator.save(Lang.EN, Identifier.COM_TRIANGLE_DESCRIPTION,
                "\tDeclares triangle by 3 sides");
    }

    @Override
    public String getName() {
        return "triangle";
    }

    @Override
    public String getHelp() {
        return Translator.read(Identifier.COM_TRIANGLE_DESCRIPTION);
    }

    @Override
    public int getNumberOfArguments() {
        return 3;
    }


    @Override
    protected void handle(Argument[] arguments) {
        AnyTriangle triangle = new AnyTriangle(arguments[0].getNumericValue(), arguments[1].getNumericValue(), arguments[2].getNumericValue());
        updateContext(triangle);
    }
}
