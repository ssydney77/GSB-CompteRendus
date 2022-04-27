package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Medicaments {
    private JPanel Main;
    private JTextArea txtContre;
    private JTextArea txtEffets;
    private JTextArea txtComposition;
    private JTextField txtFamille;
    private JTextField txtCommerce;
    private JTextField txtCode;
    private JTextField txtPrix;
    private JButton précédentButton;
    private JButton suivantButton;
    private JButton fermerButton;
    private JFrame Medicaments;
    int pos=0;

    public static Connection getConnection()
    {
        Connection cn;
        try{
            cn = DriverManager.getConnection("jdbc:mysql://localhost/gsb_java","root","root");
            return cn;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public static java.util.List<bdd_medoc> BindList(){
        try{
            Connection con = getConnection();
            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM medicament");
            ResultSet rs = st.executeQuery("SELECT medicament.MED_DEPOTLEGAL AS depot, medicament.MED_NOMCOMMERCIAL AS nom, medicament.MED_COMPOSITION AS compo, medicament.MED_EFFETS AS effets, medicament.MED_CONTREINDIC AS contre, medicament.MED_PRIXECHANTILLON AS prix, famille.FAM_CODE AS CODE, famille.FAM_LIBELLE AS libelle FROM medicament INNER JOIN famille ON famille.FAM_CODE = medicament.FAM_CODE ");
            List<bdd_medoc> list = new ArrayList<bdd_medoc>();
            while(rs.next()){
                bdd_medoc medoc = new bdd_medoc(rs.getString("depot"),
                        rs.getString("nom"),
                        rs.getString("libelle"),
                        rs.getString("compo"),
                        rs.getString("effets"),
                        rs.getString("contre"),
                        rs.getString("prix")
                        );
                list.add(medoc);
            }
            return list;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void ShowPosInfo(int index){
        txtCode.setText(BindList().get(index).getCode());
        txtCommerce.setText(BindList().get(index).getNom());
        txtFamille.setText(BindList().get(index).getFamille());
        txtComposition.setText(BindList().get(index).getComposotion());
        txtEffets.setText(BindList().get(index).getEffets());
        txtContre.setText(BindList().get(index).getContre());
        txtPrix.setText(BindList().get(index).getPrix());
    }
    public Medicaments(){

        ShowPosInfo(pos);

        Medicaments = new JFrame("Médicaments");
        Medicaments.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Medicaments.setPreferredSize(new Dimension(550,450));
        Medicaments.setResizable(false);
        Medicaments.add(Main);
        Medicaments.pack();
        Medicaments.setLocationRelativeTo(null);
        Medicaments.setVisible(true);

        précédentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pos--;
                if(pos > 0){
                    ShowPosInfo(pos);
                }
                else{
                    pos = 0;
                    ShowPosInfo(pos);
                }
            }
        });

        suivantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pos++;
                if(pos < BindList().size()){
                    ShowPosInfo(pos);
                }
                else{
                    pos = BindList().size() - 1;
                    ShowPosInfo(pos);
                }
            }
        });

        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Medicaments.dispose();
                Accueil page_accueil = new Accueil();
            }
        });

    }
}
