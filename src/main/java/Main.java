//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static utilita.Funzioni.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int scelta;
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        new GUI().home();




        /*
        do {
            System.out.println("Scegli il tuo personaggio :");
            System.out.println("1. Palmeri :");
            System.out.println("2. Maruca :");
            System.out.println("3. Tanta :");
            System.out.println("4. Evan :");
            System.out.println("5. Simo :");
            System.out.println("6. Corvo :");
            scelta = scanner.nextInt();
        }while(scelta > 6 || scelta < 1);


        player = switch (scelta) {
            case 1 -> {
                System.out.println("Hai scelto Palmeri!");
                yield palmeri;
            }
            case 2 -> {
                System.out.println("Hai scelto Maruca!");
                yield maruca;
            }
            case 3 -> {
                System.out.println("Hai scelto Tanta!");
                yield tanta;
            }
            case 4 -> {
                System.out.println("Hai scelto Evan!");
                yield evan;
            }
            case 5 -> {
                System.out.println("Hai scelto Simo!");
                yield simo;
            }
            case 6 -> {
                System.out.println("Hai scelto Corvo!");
                yield corvo;
            }
            default -> player;
        };

        scelta = rand.nextInt(7);

        nemico = switch (scelta) {

            case 1 -> {
                System.out.println("Il tuo nemico è il prof. Cerello!");
                yield cerello;
            }
            case 2 -> {
                System.out.println("Il tuo nemico è il prof. Micheletti!");
                yield micheletti;
            }
            case 3 -> {
                System.out.println("Il tuo nemico è il prof. Tacca!");
                yield tacca;
            }
            case 4 -> {
                System.out.println("Il tuo nemico è il prof. Mora!");
                yield mora;
            }
            case 5 -> {
                System.out.println("Il tuo nemico è il prof. Scampini!");
                yield scampini;
            }
            default -> {
                System.out.println("Il tuo nemico è la prof. Majnetti!");
                yield majnetti;
            }

        };

        int turno = 1;
        int esito;


        System.out.println("\n1 turno : ");
        esito = Personaggio.battle(player, nemico, null, null);

        while( nemico.getHp() > 0 && player.getHp() > 0 ){
            System.out.println("\n" + turno + " turno finito, " + (turno + 1) + " turno : ");
            esito = Personaggio.battle(player, nemico, null, null);
            turno++;
        }



        System.out.println("\nBattaglia finita!");
        if(esito == 1){
            System.out.println("\nHa vinto " + player.getNome() + "!");
        } else if(esito == 2){
            System.out.println("\nHa vinto " + nemico.getNome() + "!");
        }
        */


    }
}