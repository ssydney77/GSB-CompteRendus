package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Praticiens {
    private JPanel Main;
    private JTextField txtNumero;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtAdresse;
    private JTextField txtCP;
    private JTextField txtCoeff;
    private JTextField txtLieu;
    private JTextField txtVille;
    private JButton suivantButton;
    private JButton précédentButton;
    private JButton fermerButton;
    private JButton validerButton;
    private JComboBox comboBoxChercher;
    private JFrame Praticiens;

    int pos=0;

    PreparedStatement pst;
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
    private void updateCombo(){
        String sql = "SELECT PRA_NOM,PRA_PRENOM FROM praticien ORDER BY `PRA_NOM`";
        try {
            Connection con = getConnection();
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                comboBoxChercher.addItem(rs.getString("PRA_NOM"));
            }
        } catch (Exception e) {

        }
    }

    public static java.util.List<bdd_praticiens> BindList(){
        try{
            Connection con = getConnection();
            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM praticien ");
            ResultSet rs = st.executeQuery("SELECT praticien.PRA_NUM as numero, praticien.PRA_NOM as nom, praticien.PRA_PRENOM as prenom, praticien.PRA_ADRESSE as adresse, praticien.PRA_CP as cp, praticien.PRA_VILLE as ville,praticien.PRA_COEFNOTORIETE as coeff, type_praticien.TYP_CODE as code, type_praticien.TYP_LIBELLE as libelle FROM praticien INNER JOIN type_praticien ON type_praticien.TYP_CODE = praticien.TYP_CODE ORDER BY `praticien`.`PRA_NUM` ASC ");
            List<bdd_praticiens> list = new ArrayList<bdd_praticiens>();
            while(rs.next()){
                bdd_praticiens praticien = new bdd_praticiens(rs.getString("numero"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("cp"),
                        rs.getString("ville"),
                        rs.getString("coeff"),
                        rs.getString("libelle")
                );
                list.add(praticien);
            }
            return list;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void ShowPosInfo(int index){
        txtNumero.setText(BindList().get(index).getNum());
        txtNom.setText(BindList().get(index).getNom());
        txtPrenom.setText(BindList().get(index).getPrenom());
        txtAdresse.setText(BindList().get(index).getAdresse());
        txtCP.setText(BindList().get(index).getCp());
        txtVille.setText(BindList().get(index).getVille());
        txtCoeff.setText(BindList().get(index).getCoeff());
        txtLieu.setText(BindList().get(index).getLieu());
    }
    public Praticiens(){

        ShowPosInfo(pos);

        Praticiens = new JFrame("Praticiens");
        Praticiens.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Praticiens.setPreferredSize(new Dimension(550,330));
        Praticiens.setResizable(false);
        Praticiens.add(Main);
        Praticiens.pack();
        Praticiens.setLocationRelativeTo(null);
        Praticiens.setVisible(true);

        updateCombo();

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = getConnection();
                    String info_combobox = (String) comboBoxChercher.getSelectedItem();
                    pst = con.prepareStatement("SELECT * FROM praticien WHERE PRA_NOM = ?");
                    pst.setString(1, info_combobox);
                    ResultSet rs = pst.executeQuery();
                    if(rs.next()==true)
                    {
                        String num = rs.getString(1);
                        String nom = rs.getString(2);
                        String prenom = rs.getString(3);
                        String cp = rs.getString(4);
                        String adresse = rs.getString(5);
                        String ville = rs.getString(6);
                        String coeff = rs.getString(7);
                        String lieu = rs.getString(8);
                        txtNumero.setText(num);
                        txtNom.setText(nom);
                        txtPrenom.setText(prenom);
                        txtCP.setText(adresse);
                        txtAdresse.setText(cp);
                        txtVille.setText(ville);
                        txtCoeff.setText(coeff);
                        txtLieu.setText(lieu);

                        pos=Integer.parseInt(rs.getString("PRA_NUM"))-1;

                    }
                    else
                    {
                        txtNom.setText("");
                        txtPrenom.setText("");
                        txtAdresse.setText("");
                        txtVille.setText("");
                        txtCoeff.setText("");
                        txtLieu.setText("");
                        JOptionPane.showMessageDialog(null,"Praticien non existant !");
                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

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
                Praticiens.dispose();
                Accueil page_accueil = new Accueil();
            }
        });

    }
}
