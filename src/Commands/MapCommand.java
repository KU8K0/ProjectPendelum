package Commands;

import Game.CharactersLogic.Player;
import Game.Main;

import java.io.*;

public class MapCommand implements Command {

    private Player player;

    public MapCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("map.txt");) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
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