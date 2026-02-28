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

    protected String formatDialog(String speaker, String text) {

        StringBuilder sb = new StringBuilder();

        sb.append("\n╔════════════════════════════════════╗\n");
        sb.append("║  ").append(speaker.toUpperCase());

        int spaces = 34 - speaker.length();
        sb.append(" ".repeat(Math.max(0, spaces))).append("║\n");

        sb.append("╠════════════════════════════════════╣\n");

        for (String line : text.split("\n")) {
            sb.append("  ").append(line).append("\n");
            try { Thread.sleep(30); } catch (Exception ignored) {}
        }

        sb.append("╚════════════════════════════════════╝\n");

        return sb.toString();
    }

    public abstract String talk(String locationId);
}