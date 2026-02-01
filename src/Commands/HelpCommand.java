package Commands;

public class HelpCommand implements Command {
    @Override
    public void execute() {
        System.out.println("=== NÁPOVĚDA ===");
        System.out.println("Dostupné příkazy:");
        System.out.println("- jdi [kam]      (přesune tě do jiné lokace)");
        System.out.println("- rozhledni      (popíše aktuální místo a východy)");
        System.out.println("- batoh          (ukáže obsah inventáře)");
        System.out.println("- mapa           (zobrazí tvou polohu vůči okolí)");
        System.out.println("- konec          (ukončí hru)");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}