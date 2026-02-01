package Commands;

public class HelpCommand implements Command {

    @Override
    public void execute() {

        System.out.println("SYS_ASSIST v1.2 // ACCESS GRANTED");
        System.out.println("====================================");
        System.out.println();
        System.out.println("[NAVIGACE]");
        System.out.println("> jdi <lokace>");
        System.out.println("> rozhledni");
        System.out.println("> mapa");
        System.out.println();
        System.out.println("[INTERAKCE]");
        System.out.println("> vezmi <předmět>");
        System.out.println("> pouzij <předmět>");
        System.out.println("> mluv <postava>");
        System.out.println();
        System.out.println("[INVENTÁŘ]");
        System.out.println("> batoh");
        System.out.println("> inventar");
        System.out.println();
        System.out.println("[SYSTÉM]");
        System.out.println("> pomoc");
        System.out.println("> konec");
        System.out.println();
        System.out.println("FOG_PROTOCOL: ACTIVE");
        System.out.println("SURVEILLANCE_STATUS: UNKNOWN");
        System.out.println("REALITY_STABILITY: 82%");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
