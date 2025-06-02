import lombok.Getter;

import java.util.ArrayList;

public class Inventario {
    @Getter
    private ArrayList<String> armi;
    private final int MAX_ARMI = 4;

    public Inventario() {
        armi = new ArrayList<>();
    }

    public boolean aggiungiArma(String arma) {
        if (armi.size() < MAX_ARMI) {
            armi.add(arma);
            return true;
        } else {
            System.out.println("❌ L'inventario è pieno!");
            return false;
        }
    }

    public boolean rimuoviArma(String arma) {
        return armi.remove(arma);
    }

    public boolean contiene(String arma) {
        return armi.contains(arma);
    }

    public void mostraInventario() {
        System.out.println("Armi nell'inventario:");
        for (int i = 0; i < armi.size(); i++) {
            System.out.println((i + 1) + ". " + armi.get(i));
        }
    }

}
