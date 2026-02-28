package Commands;

import Game.Main;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class HelpCommand implements Command {

    @Override
    public String execute() {
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("help.txt")) {

            if (is == null) {
                return "HELP_ERROR: Soubor help.txt nenalezen.";
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8)
            );

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            return sb.toString();

        } catch (IOException e) {
            return "HELP_ERROR: Nelze načíst nápovědu.";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}