package Commands;

import Game.CharactersLogic.Player;

public class TalkCommand implements Command {

    private Player player;
    private String target;

    public TalkCommand(Player player, String target) {
        this.player = player;
        this.target = target;
    }

    @Override
    public String execute() {
        return "Mluvíš s: " + target + "\nPostava zatím nereaguje...";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}