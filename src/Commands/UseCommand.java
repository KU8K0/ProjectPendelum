package Commands;

import Game.CharactersLogic.Player;
import Game.ItemLogic.Item;

public class UseCommand implements Command {

    private Player player;
    private String itemName;

    public UseCommand(Player player, String itemName) {
        this.player = player;
        this.itemName = itemName;
    }

    @Override
    public String execute() {
        Item item = player.getInventory().getItem(itemName);

        if (item == null) {
            return "Tento předmět (" + itemName + ") nemáš v inventáři.";
        }

        switch (item.getId()) {
            case "item_data_chip":
                if (player.getCurrentLocation().getId().contains("loc_node")) {
                    return "Vložil jsi čip do terminálu...\n" +
                            "Načítání dat...\n" +
                            "IDENTITA POTVRZENA: SILVER NEXUS - ROBOTICKÁ JEDNOTKA MK-IV.\n" +
                            "...\n" +
                            "Pravda byla odhalena. (Napiš 'konec' pro ukončení hry).";
                } else {
                    return "Tady nemáš kam čip vložit. Potřebuješ terminál v Datovém uzlu.";
                }

            case "item_cloak":
                return "Ochranný plášť funguje automaticky. Pokud ho máš v batohu, kamery tě hůře vidí.";

            case "item_access_card":
                return "Přístupová karta slouží k otevření hlavních dveří. Použij příkaz 'jdi' směrem k zamčeným dveřím.";

            case "item_terminal":
                return "Zapnul jsi terminál. 'PING: 4ms... Connection Stable'.\n" +
                        "Zatím tu nejsou žádné nové zprávy.";

            default:
                return "Nevíš, jak tento předmět použít.";
        }
    }

    @Override
    public boolean isExit() { return false; }
}