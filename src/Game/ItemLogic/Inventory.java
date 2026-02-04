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

    public String addItem(Item item) {
        if (items.size() >= capacity) {
            return "Tvůj inventář je plný.";
        }
        items.add(item);
        return "Přidal jsi do inventáře: " + item.getName();
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public boolean hasItem(String itemName) {
        return items.stream()
                .anyMatch(i -> i.getName().equalsIgnoreCase(itemName));
    }

    public String getInventoryString() {
        if (items.isEmpty()) {
            return "Inventář je prázdný.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== INVENTÁŘ ===\n");
        for (Item item : items) {
            sb.append("- ").append(item.getName()).append("\n");
        }
        return sb.toString().trim();
    }
}