package Game;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private String id;
    private String name;
    private String description;
    private List<String> neighborIds;

    public Location(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.neighborIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addNeighbor(String neighborId) {
        neighborIds.add(neighborId);
    }

    public List<String> getNeighborIds() {
        return neighborIds;
    }

    public boolean hasNeighbor(String locationId) {
        return neighborIds.contains(locationId);
    }
}
