package Game.WorldLogic;

import Game.ItemLogic.Item;
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
            startLocationId = root.get("startLocationId").getAsString();

            // ITEM REGISTRY
            JsonArray itemsArray = root.getAsJsonArray("items");
            for (JsonElement element : itemsArray) {
                JsonObject obj = element.getAsJsonObject();
                String id = obj.get("id").getAsString();
                String name = obj.get("name").getAsString();
                String desc = obj.get("description").getAsString();
                String type = obj.get("type").getAsString();

                Item item = new Item(id, name, desc, type);
                itemRegistry.put(id, item);
            }

            // LOCATIONS
            JsonArray locArray = root.getAsJsonArray("locations");
            for (JsonElement element : locArray) {
                JsonObject obj = element.getAsJsonObject();
                String id = obj.get("id").getAsString();
                String name = obj.get("name").getAsString();
                String desc = obj.get("description").getAsString();

                Location location = new Location(id, name, desc);

                JsonArray neighbors = obj.getAsJsonArray("neighbors");
                for (JsonElement n : neighbors) {
                    location.addNeighbor(n.getAsString());
                }

                if (obj.has("items")) {
                    JsonArray locItems = obj.getAsJsonArray("items");
                    for (JsonElement itemIdElement : locItems) {
                        String itemId = itemIdElement.getAsString();
                        if (itemRegistry.containsKey(itemId)) {
                            location.addItem(itemRegistry.get(itemId));
                        }
                    }
                }

                locations.put(id, location);
            }

        } catch (Exception e) {
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
