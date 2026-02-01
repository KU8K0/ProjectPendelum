package Commands;

import Game.Player;

public class MapCommand implements Command {

    private Player player;

    public MapCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        System.out.println(" ╔══════════════════════════════════════════════════════╗");
        System.out.println(" ║  SYS.MAP_V.1.04 // ERROR: SIGNAL LOSS...             ║");
        System.out.println(" ╚══════════════════════════════════════════════════════╝");
        System.out.println("         ░░▒▒                                 ░░▒▒");
        System.out.println("         ┌──────────────────────────────┐");
        System.out.println("         │ [01] SILVERŮV BYT            │");
        System.out.println("         │ Loc: Res_Block_A7            │");
        System.out.println("         └──────────────┬───────────────┘");
        System.out.println("         ░░▒▒          [↕]          ░░▒▒");
        System.out.println("         ┌──────────────┴───────────────┐");
        System.out.println("         │ [02] OBYTNÁ ZÓNA             │ <──(Hlídky!)");
        System.out.println("         │ Status: MLHA/NEBEZPEČÍ       │      ░░▒▒");
        System.out.println("  ░░▒▒   │ Cam: [ON] / [OFF]??          │        │");
        System.out.println("    │    └──────────────┬───────────────┘        │");
        System.out.println("   [↕]                 [↕]                      [↕] HLAVNÍ VSTUP");
        System.out.println("    │    ┌──────────────┴───────────────┐        │  (Zamčeno/Risk)");
        System.out.println("    │    │ [04] SERVISNÍ PRŮCHOD        │        │");
        System.out.println("    │    │ Loot: [Plášť]                │        ▼");
        System.out.println("    │    │ Vis: 0% (Safe)               │    ┌───────────┐");
        System.out.println("    │    └──────────────┬───────────────┘    │ [03] UZEL │");
        System.out.println("    └───────────[↔] TAJNÝ VSTUP ────────────>│ Cíl: DATA │");
        System.out.println("         ░░▒▒                                └───────────┘");
        System.out.println("");
        System.out.println(">_ PING: 4ms...");
        System.out.println(">_ REALITY_CHECK: FAILED");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
