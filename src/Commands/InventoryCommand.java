package Commands;

import Game.CharactersLogic.Player;

public class InventoryCommand implements Command {

    private Player player;

    public InventoryCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        return player.getInventory().getInventoryString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}