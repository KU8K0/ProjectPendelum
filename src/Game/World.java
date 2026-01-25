package Game;

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
    private String startLocationId;

    public World() {
        locations = new HashMap<>();
    }

    public void loadFromJson(String filePath) {

        Gson gson = new Gson();

        try (Reader reader = new FileReader(filePath)) {

            JsonObject root = gson.fromJson(reader, JsonObject.class);

            startLocationId = root.get("startLocationId").getAsString();

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

