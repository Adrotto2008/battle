import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personaggio {

    //variabili personali
    private String nome;
    private float hp;
    private float hp_backup;
    private float atk;
    private float precisione;
    private float def;
    private float def_aggiunta;
    private float speed;
    private float sp;
    private boolean prof;
    private float domain_expansion;

    private float percentuale_gambler;

    //variabili globali
    private static boolean prima_mossa = true;
    private static int turno_di;

    //inizializzazione variabili
    public Personaggio (String nome,  float hp, float atk, float def, float speed, float sp, float domain_expansion, boolean prof) {
        this.nome = nome;
        this.hp = hp;
        this.hp_backup = hp;
        this.atk = atk;
        this.precisione = 100;
        this.def = def;
        this.def_aggiunta = 0;
        this.speed = speed;
        this.sp = sp;
        this.domain_expansion = domain_expansion;
        this.prof = prof;

        this.percentuale_gambler = 40;
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

    public static void attack(Personaggio attaccante, Personaggio difesa, boolean show, float attacco_aggiuntivo) {
        Random rand = new Random();
        if(rand.nextInt(99) + 1 >= attaccante.precisione) {
            System.out.println("Attacco fallito!");
        } else {
            float danno = attaccante.atk - (difesa.def + difesa.def_aggiunta) + attacco_aggiuntivo;
            difesa.def_aggiunta = 0;
            attaccante.sp += 20;
            if (danno < 0) danno = 0;
            difesa.hp = Math.max(0, difesa.hp - danno);
            if (show) {
                System.out.println(attaccante.nome + " ha effettuato " + (danno) + " di danno a " + difesa.nome);
                System.out.println(attaccante.nome + " ha guadagnato 20 punti speciali!");
            }
        }
    }

    public static void attack(Personaggio attaccante, Personaggio difesa) {
        attack(attaccante, difesa, false);
    }

    public static void attack(Personaggio attaccante, Personaggio difesa, boolean show) {
        attack(attaccante, difesa, show, 0);
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

        System.out.println("--------------------------------------");
        if(!player.prof){
            while(true){
                System.out.println("Turno di " + player.nome + "!!\n");
                System.out.println("1. Attacco");
                System.out.println("2. Difesa");
                System.out.println("3. Attacco speciale");
                System.out.println("4. Informazioni");
                System.out.println("5. Analisi");
                if(player.domain_expansion >= 100){ System.out.println("6. DOMAIN EXPANSION"); }
                System.out.println("Seleziona un'azione: ");

                scelta = scanner.nextInt();

                if (scelta == 1) {
                    attack(player, player2, true);
                    break;
                } else if (scelta == 2) {
                    defend(player, true);
                    break;
                } else if (scelta == 3) {
                    if(player.sp < 100) {
                        System.out.println("Punti speciali insufficienti!");
                        Thread.sleep(1000);
                        continue;
                    } else {
                        elenco_attacchi_speciali(player, player2);
                    }
                    break;
                } else if (scelta == 4) {
                    player.stats();
                    System.out.println("\nPremi un tasto per continuare...");
                    scanner.nextLine();
                    scanner.nextLine();
                    continue;
                } else if (scelta == 5){
                    player2.stats();
                    System.out.println("\nPremi un tasto per continuare...");
                    scanner.nextLine();
                    scanner.nextLine();
                    continue;
                } else if (scelta == 6){
                    if(player.domain_expansion < 100){
                        System.out.println("BLOCCATO!!");
                        Thread.sleep(1000);
                        continue;
                    }  else {
                        elenco_domain_expansion(player, player2);
                    }
                }
            }

        } else {

            while(true){ // scusa scampini
                scelta = rand.nextInt(7) + 1;

                if (scelta == 1) {
                    System.out.println(player.nome + " attacca!");
                    attack(player, player2, true);
                    break;
                } else if (scelta == 2) {
                    System.out.println(player.nome + " si difende!");
                    defend(player, true);
                    break;
                } else if (scelta == 3) {
                    if(player.sp < 100) {
                        //System.out.println("Punti speciali insufficienti!");
                        //Thread.sleep(1000);
                        continue;
                    } else {
                        elenco_attacchi_speciali(player, player2);
                    }
                    break;
                } else if (scelta == 4) {
                    continue;
                } else if (scelta == 5){
                    continue;
                } else if (scelta == 6){
                    if(player.domain_expansion < 100){
                        //System.out.println("BLOCCATO!!");
                        //Thread.sleep(1000);
                        continue;
                    }  else {
                        elenco_domain_expansion(player, player2);
                    }
                }
            }

        }
        player.domain_expansion += 10;
        System.out.println(player.nome + " ha guadagnato 10 punti per la domain expansion!");
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

    public static void elenco_attacchi_speciali(Personaggio player, Personaggio difesa){

        player.sp = 0;
        switch (player.nome) {
            // GIOCATORI
            case "Palmeri" -> Attacchi_speciali.sfortuna_cieca(player, difesa);
            case "Maruca" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Tanta" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Evan" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Simo" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Corvo" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            // PROF
            case "Cerello" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Micheletti" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Tacca" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Mora" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Scampini" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Majnetti" -> Attacchi_speciali.attacco_speciale2(player, difesa);
        }

    }

    public static void elenco_domain_expansion(Personaggio player, Personaggio difesa){

        player.domain_expansion = 0;
        switch (player.nome) {
            // GIOCATORI
            case "Palmeri" -> Attacchi_speciali.sfortuna_cieca(player, difesa);
            case "Maruca" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Tanta" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Evan" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Simo" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Corvo" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            // PROF
            case "Cerello" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Micheletti" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Tacca" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Mora" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Scampini" -> Attacchi_speciali.attacco_speciale2(player, difesa);
            case "Majnetti" -> Attacchi_speciali.attacco_speciale2(player, difesa);
        }


    }


}
