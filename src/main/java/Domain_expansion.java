import java.util.Random;

public class Domain_expansion {

    public static void circuito_aperto(Personaggio attaccante, Personaggio difesa) {
        System.out.println("Micheletti usa la sua DOMAIN EXPANSION : CIRCUITO APERTO!");
        System.out.println("Adesso " + difesa.getNome() + " è talmente confuso da non poter agire per 3 turni!");
        difesa.setTurno_bloccati(2);
    }

    public static void gambler_nato(Personaggio attaccante, Personaggio difesa) throws InterruptedException {
        Random rand = new Random();
        System.out.println("Palmeri usa la sua DOMAIN EXPANSION : GAMBLER NATO!");
        System.out.println("Slot machine :");
        Thread.sleep(1000);System.out.print("7 ");Thread.sleep(1500);System.out.print("7 ");Thread.sleep(2000);

        if ( rand.nextInt(99) + 1 <= attaccante.getPercentuale_gambler() ) {
            System.out.println("7");
            Thread.sleep(1500);
            System.out.println("Palmeri ha vinto il jackpot!");
            attaccante.setHp(attaccante.getHp() * 2);
            attaccante.setAtk(attaccante.getAtk() * 2);
            attaccante.setDef(attaccante.getDef() * 2);
            attaccante.setPrecisione(attaccante.getPrecisione() * 2);
            attaccante.setSpeed(attaccante.getSpeed() * 2);
        } else {
            System.out.println(rand.nextInt(7));
            Thread.sleep(1500);
            System.out.println("Palmeri non ha vinto il jackpot!");
            attaccante.setHp(attaccante.getHp() / 2);
            attaccante.setAtk(attaccante.getAtk() / 2);
            attaccante.setDef(attaccante.getDef() / 2);
            attaccante.setPrecisione(attaccante.getPrecisione() / 2);
            attaccante.setSpeed(attaccante.getSpeed() / 2);
        }
    }

    public static void cerello_pose(){
        System.out.println("Cerello usa la sua DOMAIN EXPANSION : CERELLO POSE!");


    }


}
