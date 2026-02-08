package Commands;

import Game.CharactersLogic.Player;
import Game.ItemLogic.Item;

public class InspectCommand implements Command {

    private Player player;
    private String targetName;

    public InspectCommand(Player player, String targetName) {
        this.player = player;
        this.targetName = targetName;
    }

    @Override
    public String execute() {
        Item item = player.getInventory().getItem(targetName);

        if (item == null) {
            item = player.getCurrentLocation().getItem(targetName);
        }

        if (item != null) {
            return "--- " + item.getName().toUpperCase() + " ---\n" + item.getDescription();
        } else {
            return "Nic takového tu nevidíš.";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}