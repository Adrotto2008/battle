public class Attacchi_speciali {

    public static void legge_di_ohm(){

    }

    public static void sfortuna_cieca(Personaggio attaccante, Personaggio difesa){
        System.out.println("Palmeri usa il suo attacco speciale : sfortuna_cieca!");
        System.out.println("La precisione del nemico è calata del 25%!");
        difesa.setPrecisione(difesa.getPrecisione() - 25);
    }

    public static void attacco_speciale2(Personaggio attaccante, Personaggio difesa){
        System.out.println("Attacco speciale!");
        Personaggio.attack(attaccante, difesa, true, 10);
    }

}
