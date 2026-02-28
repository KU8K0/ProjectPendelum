package Game;

import Game.WorldLogic.World;
import Game.CharactersLogic.DialogManager;

public class Main {
    public static void main(String[] args) {
        // 1. Inicializace dat
        DialogManager.load("dialogs.json");

        World world = new World();
        world.loadFromJson("gamedata.json");

        // 2. Start motoru
        GameEngine engine = new GameEngine(world);
        engine.start();
    }
}