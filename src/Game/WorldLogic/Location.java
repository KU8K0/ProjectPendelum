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
    private String requiredItemId;

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

    public void addItem(Item item) { items.add(item); }
    public void removeItem(Item item) { items.remove(item); }

    public Item getItem(String itemName) {
        if (itemName == null) return null;
        String normalizedInput = normalize(itemName);
        for (Item item : items) {
            if (normalize(item.getName()).contains(normalizedInput) || normalize(item.getId()).equals(normalizedInput)) {
                return item;
            }
        }
        return null;
    }

    public String getItemsDescription() {
        if (items.isEmpty()) return "";
        StringBuilder sb = new StringBuilder("\n>_ DETEKOVANÉ_OBJEKTY:\n");
        for (Item item : items) sb.append("   [#] ").append(item.getName()).append("\n");
        return sb.toString();
    }

    public void addNPC(NPC npc) { npcs.add(npc); }

    public NPC getNPC(String name) {
        if (name == null) return null;
        String normalizedInput = normalize(name);
        for (NPC npc : npcs) {
            if (normalize(npc.getName()).contains(normalizedInput)) return npc;
        }
        return null;
    }

    public String getNPCDescription() {
        if (npcs.isEmpty()) return "";
        StringBuilder sb = new StringBuilder("\n>_ ŽIVÉ_SUBJEKTY:\n");
        for (NPC npc : npcs) sb.append("   [@] ").append(npc.getName()).append("\n");
        return sb.toString();
    }

    private String normalize(String input) {
        if (input == null) return "";
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase().trim();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<String> getNeighborIds() { return neighborIds; }
    public void addNeighbor(String neighborId) { neighborIds.add(neighborId); }
    public String getRequiredItemId() { return requiredItemId; }
    public void setRequiredItemId(String requiredItemId) { this.requiredItemId = requiredItemId; }
}