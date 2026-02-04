package Commands;

import Game.CharactersLogic.Player;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapCommand implements Command {

    private Player player;

    public MapCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("Resources/map.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();

        } catch (IOException e) {
            return "MAP_ERROR: Nelze načíst mapu.";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}