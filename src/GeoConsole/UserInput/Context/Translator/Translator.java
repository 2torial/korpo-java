package GeoConsole.UserInput.Context.Translator;

import java.util.HashMap;
import java.util.Map;

public class Translator {
    private static Lang language = Lang.EN;
    private static final Map<Identifier, Map<Lang, String>> contents = new HashMap<>();

    public static void save(Lang lang, Identifier id, String text) {
        if (!contents.containsKey(id))
            contents.put(id, new HashMap<>());
        var identifiedContents = contents.get(id);
        identifiedContents.put(lang, text);
    }

    public static String read(Identifier id) {
        var content = contents.get(id);
        if (content == null)
            throw new NullPointerException("Unidentified content (" + id.toString() + ")");
        var text = content.get(language);
        if (text == null)
            throw new NullPointerException("Unsupported language (" + language.toString() + ")");
        return text;
    }

    public static void print(Identifier id) {
        System.out.println(read(id));
    }

    public static void changeLanguage(Lang lang) {
        if (lang == null)
            throw new NullPointerException("Selected language cannot be null");
        language = lang;
    }
}


