package Commands;

import Game.Main;

import java.io.*;

public class EndCommand implements Command {

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("outro.txt");) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();

        } catch (IOException e) {
            return "CONNECTION LOST...";
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}