package Commands;

import Game.Player;

public class UseCommand implements Command {

    private Player player;
    private String itemName;

    public UseCommand(Player player, String itemName) {
        this.player = player;
        this.itemName = itemName;
    }

    @Override
    public void execute() {

        if (player.getInventory().hasItem(itemName)) {
            System.out.println("Použil jsi: " + itemName);
        } else {
            System.out.println("Tento předmět nemáš.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
