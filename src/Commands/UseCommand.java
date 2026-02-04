package Commands;

import Game.CharactersLogic.Player;

public class UseCommand implements Command {

    private Player player;
    private String itemName;

    public UseCommand(Player player, String itemName) {
        this.player = player;
        this.itemName = itemName;
    }

    @Override
    public String execute() {
        if (player.getInventory().hasItem(itemName)) {
            return "Použil jsi: " + itemName;
        } else {
            return "Tento předmět nemáš.";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}