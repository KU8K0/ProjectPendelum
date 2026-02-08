package Game.WorldLogic;

import Game.ItemLogic.Item;
import Game.CharactersLogic.NPC;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Location {

    private String id;
    private String name;
    private String description;

    private List<String> neighborIds;
    private List<Item> items;
    private List<NPC> npcs;

    public Location(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.neighborIds = new ArrayList<>();
        this.items = new ArrayList<>();
        this.npcs = new ArrayList<>();
    }

    // ===== ITEMS =====

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
        if (items.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\nVIDÍŠ ZDE PŘEDMĚTY:\n");
        for (Item item : items) {
            sb.append(" * ").append(item.getName()).append("\n");
        }
        return sb.toString();
    }

    // ===== NPC =====

    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    public NPC getNPC(String name) {
        if (name == null) return null;

        String normalizedInput = normalize(name);

        for (NPC npc : npcs) {
            String normalizedName = normalize(npc.getName());
            if (normalizedName.contains(normalizedInput)) {
                return npc;
            }
        }

        return null;
    }

    public String getNPCDescription() {
        if (npcs.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        sb.append("\nVIDÍŠ ZDE POSTAVY:\n");
        for (NPC npc : npcs) {
            sb.append(" - ").append(npc.getName()).append("\n");
        }
        return sb.toString();
    }

    // ===== NORMALIZE =====

    private String normalize(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalized.toLowerCase().trim();
    }

    // ===== GETTERS =====

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<String> getNeighborIds() { return neighborIds; }

    public void addNeighbor(String neighborId) {
        neighborIds.add(neighborId);
    }
}
