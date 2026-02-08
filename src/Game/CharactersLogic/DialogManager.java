package Game.CharactersLogic;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class DialogManager {

    private static Map<String, JsonObject> dialogs = new HashMap<>();

    static {
        try {
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(
                    new FileReader("Resources/dialogs.json"),
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

        // Pokud máme specifický dialog pro danou lokaci/kontext
        if (npcDialogs.has(context))
            return npcDialogs.get(context).getAsString();

        // Jinak použijeme defaultní dialog
        if (npcDialogs.has("default"))
            return npcDialogs.get("default").getAsString();

        return "...";
    }
}