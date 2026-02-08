package Game.CharactersLogic;

import java.text.Normalizer;

public abstract class NPC {

    protected String id;
    protected String name;

    public NPC(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    protected String normalize(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalized.toLowerCase().trim();
    }

    public boolean matches(String input) {
        return normalize(name).contains(normalize(input));
    }

    public abstract String talk(String locationId);
}
