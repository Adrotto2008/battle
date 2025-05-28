//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        int scelta;
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        // GIOCATORI
        Personaggio player    = new Personaggio();
        Personaggio player2    = new Personaggio();
        Personaggio player3    = new Personaggio();

        player = scelta_personaggio(scanner, 1);
        player2 = scelta_personaggio(scanner, 2, player);
        player3 = scelta_personaggio(scanner, 3, player, player2);

        System.out.print("\n\n");
        System.out.println("La tua squadra è composta da : ");
        System.out.println(player.getNome());
        System.out.println(player2.getNome());
        System.out.println(player3.getNome());

        Thread.sleep(1000);
        // NEMICI
        Personaggio nemico      = new Personaggio();
        Personaggio nemico2     = new Personaggio();
        Personaggio nemico3     = new Personaggio();

        nemico = scelta_prof(rand, 1);
        nemico2 = scelta_prof(rand, 2, nemico);
        nemico3 = scelta_prof(rand, 3, nemico, nemico2);

        System.out.println("La squadra avversaria è composta da : ");
        System.out.println("prof. " + nemico.getNome());
        System.out.println("prof. " + nemico2.getNome());
        System.out.println("prof. " + nemico3.getNome());

        Thread.sleep(1000);


        int turno = 1;
        int esito, punti_squadra1 = 0, punti_squadra2 = 0;


        /// RIFAI TUTTO PIU OTTIMIZZATO E METTI CHE QUANDO UN PLAYER SOPRAVVIVE RIGIOCA

        System.out.println("\n1 turno : ");
        esito = Personaggio.battle(player, nemico, null, null);
        Thread.sleep(1000);

        while( nemico.getHp() > 0 && player.getHp() > 0 ){
            System.out.println("\n" + turno + " turno finito, " + (turno + 1) + " turno : ");
            esito = Personaggio.battle(player, nemico, null, null);
            turno++;
        }


        System.out.println("\nBattaglia finita!");
        if(esito == 1){
            System.out.println("\n" + nemico.getNome() + " è morto!");
            System.out.println("\nEntra in campo " + nemico2.getNome() + "!");
            punti_squadra1++;
        } else if(esito == 2){
            System.out.println("\n" + player.getNome() + " è morto!");
            System.out.println("\nEntra in campo " + player2.getNome() + "!");
            punti_squadra2++;
        }

        System.out.println("\n1 turno : ");
        esito = Personaggio.battle(player2, nemico2, null, null);
        Thread.sleep(1000);

        while( nemico2.getHp() > 0 && player2.getHp() > 0 ){
            System.out.println("\n" + turno + " turno finito, " + (turno + 1) + " turno : ");
            esito = Personaggio.battle(player2, nemico2, null, null);
            turno++;
        }
        System.out.println("\nBattaglia finita!");
        if(esito == 1){
            System.out.println("\n" + nemico2.getNome() + " è morto!");
            System.out.println("\nEntra in campo " + nemico3.getNome() + "!");
            punti_squadra1++;
        } else if(esito == 2){
            System.out.println("\n" + player2.getNome() + " è morto!");
            System.out.println("\nEntra in campo " + player3.getNome() + "!");
            punti_squadra2++;
        }

        System.out.println("\n1 turno : ");
        esito = Personaggio.battle(player3, nemico3, null, null);
        Thread.sleep(1000);

        while( nemico3.getHp() > 0 && player3.getHp() > 0 ){
            System.out.println("\n" + turno + " turno finito, " + (turno + 1) + " turno : ");
            esito = Personaggio.battle(player3, nemico3, null, null);
            turno++;
        }

        System.out.println("\nBattaglia finita!");
        if(esito == 1){
            System.out.println("\n" + nemico3.getNome() + " è morto!");
            punti_squadra1++;
        } else if(esito == 2){
            System.out.println("\n" + player3.getNome() + " è morto!");
            punti_squadra2++;
        }

        /// RIFAI TUTTO PIU OTTIMIZZATO E METTI CHE QUANDO UN PLAYER SOPRAVVIVE RIGIOCA



        if(punti_squadra1 > punti_squadra2){
            System.out.println("\nHai vinto!");
        } else {
            System.out.println("\nHai perso!");
        }


    }


    public static Personaggio scelta_personaggio(Scanner scanner, int i, Personaggio eccezione1, Personaggio eccezione2){
        int scelta;

        // GIOCATORI
        Personaggio player    = new Personaggio();
        Personaggio palmeri   = new Personaggio("Palmeri",   100, 40, 10, 20, 100, 100, false);
        Personaggio maruca    = new Personaggio("Maruca",    100, 40, 10, 20, 100,10, false);
        Personaggio tanta     = new Personaggio("Tanta",     100, 40, 10, 20, 60, 10, false);
        Personaggio evan      = new Personaggio("Evan",      100, 40, 10, 20, 60, 10, false);
        Personaggio simo      = new Personaggio("Simo",      100, 40, 10, 40, 60, 10, false);
        Personaggio corvo     = new Personaggio("Corvo",     100, 40, 10, 30, 60, 10, false);

        while(true) {
            System.out.println("Scegli il tuo personaggio :");
            System.out.println("1. Palmeri :");
            System.out.println("2. Maruca :");
            System.out.println("3. Tanta :");
            System.out.println("4. Evan :");
            System.out.println("5. Simo :");
            System.out.println("6. Corvo :");
            scelta = scanner.nextInt();
            if (scelta > 6 || scelta < 1) {
                System.out.println("Scelta non valida!");
                continue;
            }
            switch (scelta) {
                case 1 -> {
                    System.out.println("Hai scelto Palmeri!");
                    player = palmeri;
                }
                case 2 -> {
                    System.out.println("Hai scelto Maruca!");
                    player = maruca;
                } case 3 -> {
                    System.out.println("Hai scelto Tanta!");
                    player = tanta;
                } case 4 -> {
                    System.out.println("Hai scelto Evan!");
                    player = evan;
                } case 5 -> {
                    System.out.println("Hai scelto Simo!");
                    player = simo;
                } case 6 -> {
                    System.out.println("Hai scelto Corvo!");
                    player = corvo;
                }

            }
            if ((eccezione1 != null && player.getNome().equals(eccezione1.getNome())) || (eccezione2 != null && player.getNome().equals(eccezione2.getNome()))) {
                System.out.println("Hai già questo membro nella squadra!");
                System.out.println("Scegli un altro personaggio:");
                continue;
            } else {
                break;
            }


        }
        return player;

    }
    public static Personaggio scelta_personaggio(Scanner scanner, int i){
        return scelta_personaggio(scanner, i, null, null);
    }
    public static Personaggio scelta_personaggio(Scanner scanner, int i, Personaggio eccezione1){
        return scelta_personaggio(scanner, i, eccezione1, null);
    }

    public static Personaggio scelta_prof(Random rand, int i, Personaggio eccezione1, Personaggio eccezione2){
        int scelta;
        Personaggio prof = new Personaggio();

        Personaggio cerello     = new Personaggio("Cerello",    100, 40, 10, 20, 60, 10, true);
        Personaggio micheletti  = new Personaggio("Micheletti", 100, 40, 10, 20,120, 10, true);
        Personaggio tacca       = new Personaggio("Tacca",      100, 40, 10, 20, 40, 10, true);
        Personaggio mora        = new Personaggio("Mora",       100, 40, 10, 40, 60, 10, true);
        Personaggio scampini    = new Personaggio("Scampini",   100, 40, 10, 20, 60, 10, true);
        Personaggio majnetti    = new Personaggio("Majnetti",   100, 40, 10, 30, 60, 10, true);

        while(true) {
            scelta = rand.nextInt(7);

            prof = switch (scelta) {

                case 1 -> {
                    yield cerello;
                }
                case 2 -> {
                    yield micheletti;
                }
                case 3 -> {
                    yield tacca;
                }
                case 4 -> {
                    yield mora;
                }
                case 5 -> {
                    yield scampini;
                }
                default -> {
                    yield majnetti;
                }

            };
            if ((eccezione1 != null && prof.getNome().equals(eccezione1.getNome())) || (eccezione2 != null && prof.getNome().equals(eccezione2.getNome()))) {
                System.out.println("Hai già questo membro nella squadra!");
                System.out.println("Scegli un altro personaggio:");
                continue;
            } else {
                break;
            }

        }

        return prof;

    }
    public static Personaggio scelta_prof(Random rand, int i){
        return scelta_prof(rand, i, null, null);
    }
    public static Personaggio scelta_prof(Random rand, int i, Personaggio eccezione1){
        return scelta_prof(rand, i, eccezione1, null);
    }

}