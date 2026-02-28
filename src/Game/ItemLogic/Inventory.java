package Game.ItemLogic;

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
        if (items.size() >= capacity) {
            return false;
        }
        items.add(item);
        return true;
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public Item getItem(String itemName) {
        String normalizedInput = normalize(itemName);

        for (Item item : items) {
            if (normalize(item.getName()).contains(normalizedInput) ||
                    normalize(item.getId()).equals(normalizedInput)) {
                return item;
            }
        }
        return null;
    }

    public boolean hasItem(String id) {
        for (Item i : items) {
            if (i.getId().equals(id)) return true;
        }
        return false;
    }

    public boolean isFull() {
        return items.size() >= capacity;
    }

    private String normalize(String input) {
        if (input == null) return "";
        return java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase()
                .trim();
    }

    public String getInventoryString() {
        if (items.isEmpty()) return "Inventář zeje prázdnotou.";
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTÁŘ (" + items.size() + "/" + capacity + ") ===\n");
        for (Item item : items) {
            sb.append("- ").append(item.getName()).append("\n");
        }
        return sb.toString().trim();
    }
}