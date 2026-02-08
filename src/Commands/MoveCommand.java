package Commands;

import Game.CharactersLogic.Player;
import Game.WorldLogic.World;
import Game.WorldLogic.Location;
import java.text.Normalizer;

public class MoveCommand implements Command {
    private Player player;
    private World world;
    private String targetInput;

    public MoveCommand(Player player, World world, String targetInput) {
        this.player = player;
        this.world = world;
        this.targetInput = normalize(targetInput);
    }

    @Override
    public String execute() {
        Location current = player.getCurrentLocation();
        Location target = null;

        for (String neighborId : current.getNeighborIds()) {
            Location neighbor = world.getLocation(neighborId);
            if (neighbor != null && (normalize(neighbor.getName()).contains(targetInput) || normalize(neighborId).equals(targetInput))) {
                target = neighbor;
                break;
            }
        }

        if (target != null) {
            if (target.getRequiredItemId() != null && !player.getInventory().hasItem(target.getRequiredItemId())) {
                return "\n[!] PŘÍSTUP_ODEPŘEN: Vyžadována autorizace (" + target.getRequiredItemId() + ")\n";
            }
            player.setCurrentLocation(target);
            return "\n>> PŘESUN_ÚSPĚŠNÝ...\n" + "LOKACE: " + target.getName() + "\n" + target.getDescription() + "\n" + target.getNPCDescription() + target.getItemsDescription();
        }
        return ">_ CHYBA: Cíl nenalezen nebo není v dosahu.";
    }

    private String normalize(String input) {
        if (input == null) return "";
        return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase().trim();
    }

    @Override public boolean isExit() { return false; }
}