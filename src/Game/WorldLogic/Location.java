package Game.WorldLogic;

import Game.ItemLogic.Item;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Location {

    private String id;
    private String name;
    private String description;
    private List<String> neighborIds;
    private List<Item> items;

    public Location(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.neighborIds = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item getItem(String itemName) {
        if (itemName == null) return null;

        String normalizedInput = normalize(itemName);

        for (Item item : items) {
            String normalizedName = normalize(item.getName());
            String normalizedId = normalize(item.getId());

            if (normalizedName.contains(normalizedInput) ||
                    normalizedId.equals(normalizedInput)) {
                return item;
            }
        }

        return null;
    }

    public String getItemsDescription() {
        if (items.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\nVIDÍŠ ZDE PŘEDMĚTY:\n");
        for (Item item : items) {
            sb.append(" * ").append(item.getName()).append("\n");
        }
        return sb.toString();
    }

    private String normalize(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalized.toLowerCase().trim();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<String> getNeighborIds() { return neighborIds; }
    public void addNeighbor(String neighborId) { neighborIds.add(neighborId); }
}
