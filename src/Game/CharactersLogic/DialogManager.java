package Game.CharactersLogic;

import com.google.gson.*;
import Game.Main;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DialogManager {

    private static final Map<String, JsonObject> dialogs = new HashMap<>();
    private static final Random random = new Random();

    public static void load(String fileName) {

        try (InputStream is = Main.class.getClassLoader().getResourceAsStream(fileName)) {

            if (is == null)
                throw new RuntimeException("Soubor nenalezen: " + fileName);

            Gson gson = new Gson();
            JsonObject root = gson.fromJson(
                    new InputStreamReader(is, StandardCharsets.UTF_8),
                    JsonObject.class
            );

            for (String key : root.keySet()) {
                dialogs.put(key, root.getAsJsonObject(key));
            }

        } catch (Exception e) {
            System.err.println("Chyba při načítání dialogů: " + e.getMessage());
        }
    }

    public static String getDialog(String npcId, String context) {

        JsonObject npcDialogs = dialogs.get(npcId);
        if (npcDialogs == null) return "...";

        if (!npcDialogs.has(context))
            context = "default";

        JsonElement element = npcDialogs.get(context);

        if (element.isJsonArray()) {
            JsonArray arr = element.getAsJsonArray();
            return arr.get(random.nextInt(arr.size())).getAsString();
        }

        return element.getAsString();
    }
}