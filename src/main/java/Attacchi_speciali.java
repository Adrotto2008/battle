public class Attacchi_speciali {

    public static void legge_di_ohm(Personaggio attaccante, Personaggio difesa) {
        System.out.println("Micheletti usa il suo attacco speciale : legge di Ohm!");
        difesa.setAtk(difesa.getAtk() - difesa.getHp() / 2);// modifica
    }


    public static void sfortuna_cieca(Personaggio attaccante, Personaggio difesa){
        System.out.println("Palmeri usa il suo attacco speciale : sfortuna_cieca!");
        System.out.println("La precisione del nemico è calata del 25%!");
        difesa.setPrecisione(difesa.getPrecisione() - 25);
        attaccante.setPercentuale_gambler(attaccante.getPercentuale_gambler() + 5);
    }

    public static void attacco_speciale2(Personaggio attaccante, Personaggio difesa){
        System.out.println("Attacco speciale!");
        Personaggio.attack(attaccante, difesa, true, 10);
    }

}
