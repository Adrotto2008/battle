import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Scanner;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personaggio {

    //variabili personali
    private String nome;
    private float hp;
    private float atk;
    private float def;
    private float def_aggiunta;
    private float speed;
    private float sp;
    private boolean prof;

    //variabili globali
    private static boolean prima_mossa = true;
    private static int turno_di;

    //inizializzazione variabili
    public Personaggio (String nome,  float hp, float atk, float def, float speed, float sp, boolean prof) {
        this.nome = nome;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.def_aggiunta = 0;
        this.speed = speed;
        this.sp = sp;
        this.prof = prof;
    }

    public void stats() {
        System.out.println("nome : " + this.nome);
        System.out.println("hp : " + this.hp);
        System.out.println("attack : " + this.atk);
        System.out.print("defense : " + this.def);
        if(this.def_aggiunta > 0){ System.out.println(" + " +  this.def_aggiunta); } else { System.out.print("\n"); }
        System.out.println("speed : " + this.speed);
        System.out.println("special points : " + this.sp);
        System.out.println("abilità passiva : ");
        System.out.println("attacco speciale : ");
        System.out.println("domain expansion : ");
        System.out.println("descrizione : ");
    }

    public static void attack(Personaggio attaccante, Personaggio difesa, boolean show) {
        float danno = attaccante.atk - (difesa.def + difesa.def_aggiunta);
        difesa.def_aggiunta = 0;
        if (danno < 0) danno = 0;
        difesa.hp = Math.max(0, difesa.hp - danno);
        if (show) {
            System.out.println(attaccante.nome + " ha effettuato " + (danno) + " di danno a " + difesa.nome);
        }
    }

    public static void attack(Personaggio attaccante, Personaggio difesa) {
        attack(attaccante, difesa, false);
    }

    public static void defend(Personaggio difesa, boolean show) {
        difesa.def_aggiunta += 10;

        if (show) {
            System.out.println(difesa.nome + " ha guadagnato 10 punti di difesa");
        }
    }

    public static void defend(Personaggio difesa) {
        defend(difesa, false);
    }
    
    public static void menu(Personaggio player, Personaggio player2) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int scelta;

        System.out.println("-------------------55-------------------");
        if(!player.prof){
            do{
                System.out.println("Turno di " + player.nome + "!!\n");
                System.out.println("1. Attacco");
                System.out.println("2. Difesa");
                System.out.println("3. Attacco speciale");
                System.out.println("4. Informazioni");
                System.out.println("5. Analisi");
                System.out.println("Seleziona un'azione: ");

                scelta = scanner.nextInt();

                if (scelta == 1) {
                    attack(player, player2, true);
                } else if (scelta == 2) {
                    defend(player, true);
                } else if (scelta == 3) {
                    // Implementare attacco speciale
                } else if (scelta == 4) {
                    player.stats();
                    System.out.println("\nPremi un tasto per continuare...");
                    scanner.nextLine();
                    scanner.nextLine();
                } else if (scelta == 5){
                    player2.stats();
                    System.out.println("\nPremi un tasto per continuare...");
                    scanner.nextLine();
                    scanner.nextLine();
                }
                Thread.sleep(1000);///
            }while(scelta != 1 && scelta != 2 && scelta != 3);

        } else {

                scelta = rand.nextInt(2) + 1;

                if (scelta == 1) {
                    System.out.println(player2.nome + " ha deciso di attaccare!");
                    attack(player, player2, true);
                } else if (scelta == 2) {
                    System.out.println(player2.nome + " ha deciso di difendersi!");
                    defend(player, true);
                } else if (scelta == 3) {
                // Implementare attacco speciale
                } else if (scelta == 4) {

                }
        }

        System.out.println("--------------------------------------");
    }
    
    
    public static int battle(Personaggio player1, Personaggio player2, Personaggio player3, Personaggio player4) throws InterruptedException {
        if ( player3 == null && player4 == null ){
            if(prima_mossa) {
                if (player1.speed >= player2.speed) {
                    menu(player1, player2);
                    turno_di = 2;
                } else if (player1.speed < player2.speed) {
                    menu(player2, player1);
                    turno_di = 1;
                }
                prima_mossa = false;
            } else {
                if(turno_di == 1){
                    menu(player1, player2);
                    turno_di = 2;
                } else if (turno_di == 2){
                    menu(player2, player1);
                    turno_di = 1;
                }
            }
            
        } else {
            return 3;
        }
        if (player2.hp <= 0){
            return 1;
        } else if (player1.hp <= 0){
            return 2;
        }
        return 3;
    }

    public static int battle(Personaggio player1, Personaggio player2) throws InterruptedException {
        return battle(player1, player2, null, null);
    }

}
