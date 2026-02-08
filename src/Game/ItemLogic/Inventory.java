package Game.ItemLogic;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Item> items;
    private int capacity;

    public Inventory(int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        if (isFull()) {
            return false;
        }
        items.add(item);
        return true;
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
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

    public boolean hasItem(String itemName) {
        return getItem(itemName) != null;
    }

    public boolean isFull() {
        return items.size() >= capacity;
    }

    public String getInventoryString() {
        if (items.isEmpty()) {
            return "Batoh zeje prázdnotou.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTÁŘ (" + items.size() + "/" + capacity + ") ===\n");
        for (Item item : items) {
            sb.append("- ").append(item.getName()).append("\n");
        }
        return sb.toString().trim();
    }

    private String normalize(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalized.toLowerCase().trim();
    }
}
