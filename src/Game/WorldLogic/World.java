package Game.WorldLogic;

import Game.ItemLogic.Item;
import Game.CharactersLogic.*;
import Game.Main;
import com.google.gson.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class World {
    private Map<String, Location> locations = new HashMap<>();
    private Map<String, Item> itemRegistry = new HashMap<>();
    private Map<String, Map<String, String>> dialogRegistry = new HashMap<>();
    private String startLocationId;
    private String winLore = "";

    public void loadFromJson(String filePath) {
        Gson gson = new Gson();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filePath)) {
            JsonObject root = gson.fromJson(new InputStreamReader(inputStream, StandardCharsets.UTF_8), JsonObject.class);
            if (root.has("startLocationId")) startLocationId = root.get("startLocationId").getAsString();

            if (root.has("items")) {
                for (JsonElement e : root.getAsJsonArray("items")) {
                    JsonObject o = e.getAsJsonObject();
                    itemRegistry.put(o.get("id").getAsString(), new Item(o.get("id").getAsString(), o.get("name").getAsString(), o.get("description").getAsString(), o.get("type").getAsString()));
                }
            }

            Map<String, NPC> npcRegistry = new HashMap<>();
            if (root.has("characters")) {
                for (JsonElement e : root.getAsJsonArray("characters")) {
                    JsonObject o = e.getAsJsonObject();
                    String id = o.get("id").getAsString();
                    npcRegistry.put(id, o.get("type").getAsString().equals("FRIENDLY") ? new FriendlyNPC(id, o.get("name").getAsString()) : new EnemyNPC(id, o.get("name").getAsString()));
                }
            }

            if (root.has("locations")) {
                for (JsonElement e : root.getAsJsonArray("locations")) {
                    JsonObject o = e.getAsJsonObject();
                    Location loc = new Location(o.get("id").getAsString(), o.get("name").getAsString(), o.get("description").getAsString());
                    if (o.has("neighbors")) for (JsonElement n : o.getAsJsonArray("neighbors")) loc.addNeighbor(n.getAsString());
                    if (o.has("items")) for (JsonElement i : o.getAsJsonArray("items")) if (itemRegistry.containsKey(i.getAsString())) loc.addItem(itemRegistry.get(i.getAsString()));
                    if (o.has("characters")) for (JsonElement c : o.getAsJsonArray("characters")) if (npcRegistry.containsKey(c.getAsString())) loc.addNPC(npcRegistry.get(c.getAsString()));
                    if (o.has("requiredItem")) loc.setRequiredItemId(o.get("requiredItem").getAsString());
                    if (o.has("isDangerous")) loc.setDangerous(o.get("isDangerous").getAsBoolean());
                    locations.put(loc.getId(), loc);
                }
            }

            this.winLore = "\n[ VNITŘNÍ MONOLOG: SILVER NEXUS ]\n" +
                    ">_ Analyzuji získaný datový čip...\n" +
                    ">_ Moje identita je řada nul a jedniček v lidském obalu.\n" +
                    ">_ Agnes je stejná. Eidolon měl pravdu.\n" +
                    ">_ Silrae není domov, je to jen testovací subjekt.\n" +
                    ">_ SYSTÉM RESTARTUJE POCHYBY. KONEC RELACE.";

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void loadDialogs(String filePath) {
        Gson gson = new Gson();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filePath)) {
            JsonObject root = gson.fromJson(new InputStreamReader(inputStream, StandardCharsets.UTF_8), JsonObject.class);
            for (String npcId : root.keySet()) {
                JsonObject d = root.getAsJsonObject(npcId);
                Map<String, String> contexts = new HashMap<>();
                for (String key : d.keySet()) contexts.put(key, d.get(key).getAsString());
                dialogRegistry.put(npcId, contexts);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public String getNPCDialog(String npcId, String locationId) {
        if (!dialogRegistry.containsKey(npcId)) return "Postava neodpovídá.";
        Map<String, String> dialogs = dialogRegistry.get(npcId);
        return dialogs.getOrDefault(locationId, dialogs.getOrDefault("default", "Nemám co říct."));
    }

    public String getOutroContent() {
        return winLore;
    }

    public Location getLocation(String id) { return locations.get(id); }
    public String getStartLocationId() { return startLocationId; }
    public String getWinLore() { return winLore; }
    public Item createSpecialItem(String id) { return itemRegistry.get(id); }
}