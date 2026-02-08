package Game.WorldLogic;

import Game.ItemLogic.Item;
import Game.CharactersLogic.NPC;
import Game.CharactersLogic.FriendlyNPC;
import Game.CharactersLogic.EnemyNPC;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class World {

    private Map<String, Location> locations;
    private Map<String, Item> itemRegistry;
    private String startLocationId;

    public World() {
        locations = new HashMap<>();
        itemRegistry = new HashMap<>();
    }

    public void loadFromJson(String filePath) {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(filePath)) {
            JsonObject root = gson.fromJson(reader, JsonObject.class);

            if (root.has("startLocationId")) {
                startLocationId = root.get("startLocationId").getAsString();
            }

            // 1. REGISTR PŘEDMĚTŮ - Přidána kontrola existence
            if (root.has("items")) {
                JsonArray itemsArray = root.getAsJsonArray("items");
                for (JsonElement element : itemsArray) {
                    JsonObject obj = element.getAsJsonObject();
                    String id = obj.get("id").getAsString();
                    String name = obj.get("name").getAsString();
                    String desc = obj.get("description").getAsString();
                    String type = obj.get("type").getAsString();

                    itemRegistry.put(id, new Item(id, name, desc, type));
                }
            }

            // 2. REGISTR POSTAV (NPC) - Přidána kontrola existence
            Map<String, NPC> npcRegistry = new HashMap<>();
            if (root.has("characters")) {
                JsonArray charArray = root.getAsJsonArray("characters");
                for (JsonElement element : charArray) {
                    JsonObject obj = element.getAsJsonObject();
                    String id = obj.get("id").getAsString();
                    String name = obj.get("name").getAsString();
                    String type = obj.get("type").getAsString();

                    if ("FRIENDLY".equals(type)) {
                        npcRegistry.put(id, new FriendlyNPC(id, name));
                    } else if ("ENEMY".equals(type)) {
                        npcRegistry.put(id, new EnemyNPC(id, name));
                    }
                }
            }

            // 3. NAČÍTÁNÍ LOKACÍ
            if (root.has("locations")) {
                JsonArray locArray = root.getAsJsonArray("locations");
                for (JsonElement element : locArray) {
                    JsonObject obj = element.getAsJsonObject();
                    String id = obj.get("id").getAsString();
                    String name = obj.get("name").getAsString();
                    String desc = obj.get("description").getAsString();

                    Location location = new Location(id, name, desc);

                    // Sousedé
                    if (obj.has("neighbors")) {
                        JsonArray neighbors = obj.getAsJsonArray("neighbors");
                        for (JsonElement n : neighbors) {
                            location.addNeighbor(n.getAsString());
                        }
                    }

                    // Předměty v lokaci
                    if (obj.has("items")) {
                        JsonArray locItems = obj.getAsJsonArray("items");
                        for (JsonElement itemIdElement : locItems) {
                            String itemId = itemIdElement.getAsString();
                            if (itemRegistry.containsKey(itemId)) {
                                location.addItem(itemRegistry.get(itemId));
                            }
                        }
                    }

                    // Postavy v lokaci
                    if (obj.has("characters")) {
                        JsonArray locChars = obj.getAsJsonArray("characters");
                        for (JsonElement charIdElement : locChars) {
                            String charId = charIdElement.getAsString();
                            if (npcRegistry.containsKey(charId)) {
                                location.addNPC(npcRegistry.get(charId));
                            }
                        }
                    }

                    locations.put(id, location);
                }
            }

        } catch (Exception e) {
            System.err.println("Chyba při načítání světa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Location getLocation(String id) {
        return locations.get(id);
    }

    public String getStartLocationId() {
        return startLocationId;
    }
}