package Commands;

import Game.Player;

public class TalkCommand implements Command {

    private Player player;
    private String target;

    public TalkCommand(Player player, String target) {
        this.player = player;
        this.target = target;
    }

    @Override
    public void execute() {
        System.out.println("Mluvíš s: " + target);
        System.out.println("Postava zatím nereaguje...");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
