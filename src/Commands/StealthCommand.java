package Commands;

import Game.CharactersLogic.Player;
import Game.ItemLogic.Item;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Random;

public class StealthCommand implements Command {
    private Player player;
    private int playerPos = 0;
    private final int finishPos = 12;
    private int alarmLevel = 0;

    public StealthCommand(Player player) {
        this.player = player;
    }

    @Override
    public String execute() {
        if (!player.getCurrentLocation().getId().equals("loc_residential")) {
            return ">_ INFO: V této oblasti nehrozí žádné nebezpečí. Plížení by bylo ztrátou času.";
        }

        boolean hasCloak = player.getInventory().getItem("item_cloak") != null;

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║  S.E.N.S.O.R.  OVERLAY // STATUS: HOSTILE    ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        if (hasCloak) {
            System.out.println(">_ SYSTÉM: Detekován 'Ochranný plášť'. Tepelná stopa tlumena.");
        } else {
            System.out.println(">_ VAROVÁNÍ: Nemáš žádné maskování! Senzory jsou extrémně citlivé.");
        }

        while (playerPos < finishPos && alarmLevel < 100) {
            renderScanner();

            System.out.println("\n[ MOŽNOSTI ]:");
            System.out.println("(1) Plížit se   [+1 pole]");
            System.out.println("(2) Běžet       [+2 pole, obrovské riziko]");
            System.out.println("(3) Skrýt se    [+0 pole, sníží ALARM]");
            System.out.print(">_ AKCE: ");
            String input = sc.nextLine().trim();

            int chanceToDetect = 0;
            boolean isHiding = false;

            switch (input) {
                case "1":
                    playerPos += 1;
                    chanceToDetect = hasCloak ? 25 : 60; // Bez pláště je i plížení sebevražda
                    break;
                case "2":
                    playerPos += 2;
                    chanceToDetect = hasCloak ? 50 : 90; // Běh bez pláště = téměř jistá smrt
                    break;
                case "3":
                    isHiding = true;
                    chanceToDetect = hasCloak ? 5 : 20;
                    break;
                default:
                    System.out.println(">> Neplatný příkaz. Ztrácíš čas.");
                    chanceToDetect = 30;
                    break;
            }

            System.out.println("\n>> Skener hlídky provádí sweep sektoru...");
            try { Thread.sleep(600); } catch (Exception ignored) {}

            if (rand.nextInt(100) < chanceToDetect) {
                int penalty = hasCloak ? (15 + rand.nextInt(20)) : (30 + rand.nextInt(30)); // Bez pláště roste alarm rychleji
                alarmLevel += penalty;
                System.out.println(" [!] VAROVÁNÍ: Detekován podezřelý pohyb! ALARM vzrostl o " + penalty + "%.");
            } else {
                if (isHiding) {
                    int reduction = hasCloak ? 20 : 10;
                    alarmLevel = Math.max(0, alarmLevel - reduction);
                    System.out.println(" [OK] Jsi dobře schovaný. ALARM klesl na " + alarmLevel + "%.");
                } else {
                    System.out.println(" [OK] Tvá poloha zůstala utajena.");
                }
            }
        }

        if (alarmLevel >= 100) {
            return "\n[!!!] ALARM DOSÁHL " + alarmLevel + "%! Hlídka tě objevila. Záchranný protokol tě donutil k ústupu do bezpečí (Běž do úkrytu).";
        } else {
            Item keycard = new Item("item_access_card", "Přístupová karta", "Modře svítící karta ukradená strážnému.", "KEY");
            if (player.getInventory().addItem(keycard)) {
                return "\n[ÚSPĚCH] Podařilo se ti proklouznout přes hlídku a nepozorovaně jí ukrást předmět: [Přístupová karta]!";
            } else {
                player.getCurrentLocation().addItem(keycard);
                return "\n[ÚSPĚCH] Proklouzl jsi. Strážný upustil [Přístupová karta], ale máš plný inventář, tak zůstala ležet na zemi.";
            }
        }
    }

    private void renderScanner() {
        System.out.print("\nTRASA: [");
        for (int i = 0; i <= finishPos; i++) {
            if (i == playerPos) System.out.print("◈");
            else if (i == finishPos) System.out.print("▣");
            else System.out.print("·");
        }
        System.out.println("] " + playerPos + "/" + finishPos);
        int alarmBars = Math.min(10, alarmLevel / 10);
        System.out.println("ALARM: [" + "█".repeat(alarmBars) + " ".repeat(10 - alarmBars) + "] " + alarmLevel + "%");
    }

    @Override public boolean isExit() { return false; }
}