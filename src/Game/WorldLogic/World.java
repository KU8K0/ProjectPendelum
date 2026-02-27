package Game.WorldLogic;

import Game.ItemLogic.Item;
import Game.CharactersLogic.*;
import Game.Main;
import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class World {
    private Map<String, Location> locations = new HashMap<>();
    private Map<String, Item> itemRegistry = new HashMap<>();
    private String startLocationId;
    private String outroContent = "";

    public void loadFromJson(String filePath) {
        Gson gson = new Gson();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filePath)) {
            JsonObject root = gson.fromJson(new InputStreamReader(inputStream), JsonObject.class);
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
                    String name = o.get("name").getAsString();
                    npcRegistry.put(id, o.get("type").getAsString().equals("FRIENDLY") ? new FriendlyNPC(id, name) : new EnemyNPC(id, name));
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
                    locations.put(loc.getId(), loc);
                }
            }

            try {
                outroContent = Files.readString(Paths.get("Resources/outro.txt"));
            } catch (IOException ex) {
                outroContent = "SYSTEM ERROR: OUTRO FILE MISSING.";
            }

        } catch (Exception e) { e.printStackTrace(); }
    }

    public Location getLocation(String id) { return locations.get(id); }
    public String getStartLocationId() { return startLocationId; }
    public String getOutroContent() { return outroContent; }
}