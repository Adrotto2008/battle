public class Attacchi_speciali {

    public static void legge_di_ohm(){

    }

    public static void attacco_speciale1(Personaggio attaccante, Personaggio difesa){
        System.out.println("Attacco speciale!");
        Personaggio.attack(attaccante, difesa, true, 30);
    }
    public static void attacco_speciale2(Personaggio attaccante, Personaggio difesa){
        System.out.println("Attacco speciale!");
        Personaggio.attack(attaccante, difesa, true, 10);
    }

}
