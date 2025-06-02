import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utilita.Funzioni.*;

public class GUI {
    private final List<Personaggio> personaggiSelezionati = new ArrayList<>();
    private final List<JPanel> pannelliStats = new ArrayList<>();

    private JPanel panel_container;
    private CardLayout cardLayout;
    private JFrame frame;

    public void home() {
        frame = new JFrame("battle-master");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1900, 1200);

        cardLayout = new CardLayout();
        panel_container = new JPanel(cardLayout);
        panel_container.setBounds(0, 0, 1900, 1200);
        frame.add(panel_container);  // Fico, aggiungi qui al frame

        // PANNELLO HOME
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(1900, 1200);
        panel_container.add(panel, "home");

        JLabel titolo_label = crea_label(50, 50, 400, 50, "Benvenuto in battle-master!!", panel);
        titolo_label.setFont(new Font("arial", Font.BOLD, 26));
        crea_label(100, 150, 200, 20, "Scegli 3 personaggi:", panel);

        // CREA I GIOCATORI
        Personaggio simonotti = new Personaggio("simonotti", 100,20,  30, 20, 0,  100,false,"nasone");
        Personaggio palmeri   = new Personaggio("Palmeri",   100, 40, 10, 20, 100,100,false,"stecca da biliardo");
        Personaggio maruca    = new Personaggio("Maruca",    100, 40, 10, 20, 100,10, false,"macbook pro");
        Personaggio tanta     = new Personaggio("Tanta",     100, 40, 10, 20, 60, 10, false,"attestati corsi sicurezza");
        Personaggio evan      = new Personaggio("Evan",      100, 40, 10, 20, 60, 10, false,"fidanzata 2010");
        Personaggio simo      = new Personaggio("Simo",      100, 40, 10, 40, 60, 10, false,"libro della sapienza");
        Personaggio corvo     = new Personaggio("Corvo",     100, 40, 10, 30, 60, 10, false,"babba napoletani");

        var listaCharacter = List.of(simonotti, palmeri, maruca, tanta, evan, simo, corvo);
        int x = 100, y = 200;
        for (Personaggio personaggio : listaCharacter) {
            crea_label(x, y, 100, 20, personaggio.getNome(), panel);
            JToggleButton btn = crea_switch(x + 120, y, 100, 20, "", panel);

            btn.addActionListener(_ -> {
                if (btn.isSelected()) {
                    if (personaggiSelezionati.size() < 3 && !personaggiSelezionati.contains(personaggio)) {
                        personaggiSelezionati.add(personaggio);
                    } else {
                        btn.setSelected(false);
                    }
                } else {
                    personaggiSelezionati.remove(personaggio);
                }

                // Rimuovi pannelli vecchi
                for (JPanel p : pannelliStats) {
                    panel.remove(p);
                }
                pannelliStats.clear();

                // Ricrea pannelli stats
                int yOffset = 0;
                for (Personaggio p : personaggiSelezionati) {
                    JPanel statPanel = stats(p);
                    statPanel.setBounds(800, yOffset, 800, 230);
                    pannelliStats.add(statPanel);
                    panel.add(statPanel);
                    yOffset += 275;
                }

                panel.revalidate();
                panel.repaint();
            });

            y += 50;
        }

        JButton button_combatti = creabutton(200,600,300,100,"COMBATTI!");
        button_combatti.setFont(new Font("arial",Font.BOLD,30));
        panel.add(button_combatti);

        button_combatti.addActionListener(_ -> {
            if(personaggiSelezionati.size() != 3){
                JOptionPane.showMessageDialog(null, "Devi scegliere esattamente 3 personaggi brutto coglione!");
                return;
            }

            // CREA I NEMICI
            List<Personaggio> listaNemici = new ArrayList<>();
            listaNemici.add(new Personaggio("Cerello",    100, 40, 10, 20, 60, 10, true,"piano cartesiano"));
            listaNemici.add(new Personaggio("Micheletti", 100, 40, 10, 20,120, 10, true,"mega condensatore carico"));
            listaNemici.add(new Personaggio("Tacca",      100, 40, 10, 20, 40, 10, true,"lavatrice a incastro"));
            listaNemici.add(new Personaggio("Mora",       100, 40, 10, 40, 60, 10, true,"penna"));
            listaNemici.add(new Personaggio("Scampini",   100, 40, 10, 20, 60, 10, true,"libro di informatica"));
            listaNemici.add(new Personaggio("Majnetti",   100, 40, 10, 30, 60, 10, true,"laurea"));
            listaNemici.add(new Personaggio("Echelh",     100, 40, 10, 30, 60, 10, true,"ipad"));

            Collections.shuffle(listaNemici);
            List<Personaggio> nemiciScelti = listaNemici.subList(0, 3);

            System.out.println("Nemici che affronterai:");
            for (Personaggio nemico : nemiciScelti) {
                System.out.println(" - " + nemico.getNome());
            }

            // Passo i personaggi selezionati e nemici scelti al ring, per renderli disponibili
            panel_container.add(ring(personaggiSelezionati, nemiciScelti), "panel_ring");
            cardLayout.show(panel_container,"panel_ring");
        });

        // LINEE DI SEPARAZIONE FIGHE
        JPanel riga_nera_verticale = new JPanel();
        riga_nera_verticale.setBackground(Color.BLACK);
        riga_nera_verticale.setBounds(780, 0, 15, 800);
        panel.add(riga_nera_verticale);

        crea_panel_riga(780, 260, 800, 15, panel);
        crea_panel_riga(780, 520, 800, 15, panel);

        frame.setVisible(true);
    }

    // Ora ring prende in input i personaggi scelti e i nemici
    public JPanel ring(List<Personaggio> giocatori, List<Personaggio> nemici) {
        JPanel panel = new JPanel();
        panel.setSize(1900,1200);
        panel.setLayout(null);

        // Aggiungo linee fighe
        crea_panel_riga(1050, 500, 500, 15, panel);
        crea_panel_riga(1050, 500, 15, 350, panel);

        // Metto azioni e stats per il primo personaggio scelto (ad esempio)
        if (!giocatori.isEmpty()) {
            JPanel statsPanel = stats(giocatori.get(0));
            statsPanel.setBounds(20, 550, 400, 300);
            panel.add(statsPanel);

            JPanel azioniPanel = azioni(giocatori.get(0));
            azioniPanel.setBounds(1070, 520, 470, 270);
            panel.add(azioniPanel);
        }

        // Qui puoi aggiungere roba per nemici o altri giocatori
        if (!nemici.isEmpty()) {
            JPanel nemici_panel_stats = stats(nemici.getFirst());
            nemici_panel_stats.setBounds(50, 50, 400, 400);
            panel.add(nemici_panel_stats);
        }

        return panel;
    }

    // stats non cambia quasi niente
    public JPanel stats(Personaggio personaggio) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setSize(400, 230);

        JPanel panel_stats = new JPanel();
        panel_stats.setLayout(new GridLayout(8, 2, 10, 10));

        panel_stats.add(new JLabel("Nome:"));
        panel_stats.add(new JLabel(personaggio.getNome()));

        panel_stats.add(new JLabel("HP:"));
        panel_stats.add(new JLabel(String.valueOf(personaggio.getHp())));

        panel_stats.add(new JLabel("Attacco:"));
        panel_stats.add(new JLabel(String.valueOf(personaggio.getAtk())));

        panel_stats.add(new JLabel("Difesa:"));
        panel_stats.add(new JLabel(String.valueOf(personaggio.getDef())));

        panel_stats.add(new JLabel("Velocità:"));
        panel_stats.add(new JLabel(String.valueOf(personaggio.getSpeed())));

        panel_stats.add(new JLabel("SP:"));
        panel_stats.add(new JLabel(String.valueOf(personaggio.getSp())));

        panel_stats.add(new JLabel("Domain Expansion:"));
        panel_stats.add(new JLabel(String.valueOf(personaggio.getDomain_expansion())));

        panel_stats.add(new JLabel("Arma:"));
        panel_stats.add(new JLabel(personaggio.getArma()));

        panel.add(panel_stats, BorderLayout.NORTH);
        return panel;
    }

    public JPanel azioni(Personaggio personaggio){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.setSize(400, 150);

        JButton attacca = creabutton(0,0,200,70,"ATTACCA");
        JButton difesa = creabutton(0,0,200,70,"DIFESA");
        JButton domain = creabutton(0,0,200,70,"DOMAIN EXPANSION");
        JButton speciale = creabutton(0,0,200,70,"SPECIALE");

        attacca.addActionListener(e -> System.out.println(" Hai scelto di ATTACCARE con " + personaggio.getNome()));
        difesa.addActionListener(e -> System.out.println("️ Ti sei DIFESO con " + personaggio.getNome()));
        domain.addActionListener(e -> System.out.println(" DOMAIN EXPANSION ATTIVATA da " + personaggio.getNome()));
        speciale.addActionListener(e -> System.out.println(" ATTACCO SPECIALE di " + personaggio.getNome()));

        panel.add(attacca);
        panel.add(difesa);
        panel.add(domain);
        panel.add(speciale);

        return panel;
    }

}
