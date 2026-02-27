package Commands;

import Game.Main;

import java.io.*;

public class HelpCommand implements Command {

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("help.txt");) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
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