package Commands;

import Game.CharactersLogic.Player;
import Game.ItemLogic.Item;
import Game.WorldLogic.World;

public class UseCommand implements Command {
    private Player player;
    private World world;
    private String itemName;
    private boolean isVictory = false;

    public UseCommand(Player player, World world, String itemName) {
        this.player = player;
        this.world = world;
        this.itemName = itemName;
    }

    @Override
    public String execute() {
        Item item = player.getInventory().getItem(itemName);
        if (item == null) return ">_ ERROR: Předmět [" + itemName + "] nenalezen v inventáři.";

        if (item.getId().equals("item_data_chip")) {
            if (player.getCurrentLocation().getId().contains("loc_node")) {
                runCinematicOutro();
                isVictory = true;
                return "";
            }
            return ">_ ERROR: Chybí kompatibilní rozhraní pro vložení datového čipu.";
        }

        if (item.getId().equals("item_access_card")) {
            return ">_ INFO: Karta je aktivní. Stačí se přiblížit k zamčeným dveřím.";
        }

        return ">_ INFO: Předmět " + item.getName() + " nelze v této situaci použít.";
    }

    private void runCinematicOutro() {
        String outro = world.getOutroContent();
        System.out.println("\n>_ NAVAZUJI SPOJENÍ S TERMINÁLEM...");
        sleep(1000);
        System.out.println(">_ DEŠIFROVÁNÍ DAT:");

        for (String line : outro.split("\n")) {
            System.out.println(line);
            sleep(150);
        }
        sleep(2000);
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    @Override public boolean isExit() { return isVictory; }
}