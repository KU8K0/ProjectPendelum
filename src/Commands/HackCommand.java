package Commands;

import Game.Main;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import Game.CharactersLogic.Player;
import Game.ItemLogic.Item;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class HackCommand implements Command {

    private Player player;
    private JsonObject config;

    public HackCommand(Player player) {
        this.player = player;
        loadConfig();
    }

    private void loadConfig() {
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("hacking.json")) {
            this.config = new Gson().fromJson(new InputStreamReader(inputStream), JsonObject.class);
        } catch (Exception e) {
            System.err.println("Chyba při načítání hacking.json: " + e.getMessage());
        }
    }

    @Override
    public String execute() {

        if (!player.getInventory().hasItem("item_terminal")) {
            return ">_ ERROR: Nemáš přenosný terminál.";
        }

        if (!player.getCurrentLocation().getId().equals("loc_node")) {
            return ">_ ERROR: V této lokaci není síťový uzel.";
        }

        JsonObject ui = config.getAsJsonObject("ui");
        int attempts = config.getAsJsonObject("settings").get("max_attempts").getAsInt();

        Random random = new Random();
        String targetPin = String.format("%03d", random.nextInt(1000));
        Scanner sc = new Scanner(System.in);

        System.out.println("\n" + ui.get("header").getAsString());
        System.out.println("STATUS: POKUSY (" + attempts + ") | LOKACE: " + player.getCurrentLocation().getName());
        System.out.println("----------------------------------------------");

        while (attempts > 0) {

            System.out.print(ui.get("prompt").getAsString());
            String guess = sc.nextLine().trim();

            if (guess.length() != 3 || !guess.matches("\\d+")) {
                System.out.println(ui.get("error").getAsString());
                continue;
            }

            if (guess.equals(targetPin)) {
                return handleVictory(ui.get("win").getAsString());
            }

            attempts--;

            System.out.println(getFeedback(guess, targetPin) + " | ZBÝVÁ POKUSŮ: " + attempts);
        }

        return "\n" + ui.get("lose").getAsString();
    }

    private String getFeedback(String guess, String target) {

        StringBuilder fb = new StringBuilder("ANALÝZA: [ ");

        for (int i = 0; i < 3; i++) {

            if (guess.charAt(i) == target.charAt(i)) {
                fb.append("+ ");
            }
            else if (target.contains(String.valueOf(guess.charAt(i)))) {
                fb.append("- ");
            }
            else {
                fb.append("X ");
            }
        }

        fb.append("]");

        return fb.toString();
    }

    private String handleVictory(String winMessage) {

        Item chip = new Item(
                "item_data_chip",
                "Datový čip",
                "Obsahuje dešifrovaná data projektu Pendelum.",
                "QUEST"
        );

        if (player.getInventory().addItem(chip)) {
            return "\n" + winMessage + "\n>_ ZÍSKÁNA DATA: [item_data_chip]";
        }
        else {
            player.getCurrentLocation().addItem(chip);
            return "\n" + winMessage + "\n>_ Inventář plný. Čip zůstal v lokaci.";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}