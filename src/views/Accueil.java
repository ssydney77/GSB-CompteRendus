package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil {
    private JPanel Main;
    private JFrame Accueil;
    private JCheckBox compteRendusCheckBox;
    private JCheckBox quitterCheckBox;
    private JCheckBox médicamentCheckBox;
    private JCheckBox praticiensCheckBox;
    private JCheckBox VisiteursCheckBox;

    public Accueil(){
        Accueil = new JFrame("Accueil");
        Accueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Accueil.setPreferredSize(new Dimension(550,500));
        Accueil.setResizable(false);
        Accueil.add(Main);
        Accueil.pack();
        Accueil.setLocationRelativeTo(null);
        Accueil.setVisible(true);


        praticiensCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Accueil.dispose();
                Praticiens page_praticiens = new Praticiens();
            }
        });

        médicamentCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Accueil.dispose();
                Medicaments page_medicaments = new Medicaments();
            }
        });

        quitterCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
