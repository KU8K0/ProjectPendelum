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
            System.out.println("Tvůj inventář je plný.");
            return false;
        }
        items.add(item);
        System.out.println("Přidal jsi do inventáře: " + item.getName());
        return true;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public boolean hasItem(String itemName) {
        return items.stream()
                .anyMatch(i -> i.getName().equalsIgnoreCase(itemName));
    }

    public void printInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventář je prázdný.");
            return;
        }

        System.out.println("=== INVENTÁŘ ===");
        for (Item item : items) {
            System.out.println("- " + item.getName());
        }
    }
}
