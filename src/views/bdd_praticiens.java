package views;

public class bdd_praticiens {
    private String num;
    private String nom;
    private String prenom;
    private String adresse;
    private String cp;
    private String ville;
    private String coeff;
    private String lieu;


    public bdd_praticiens(String _num,String _nom,String _prenom,String _adresse,String _cp,String _ville,String _coeff,String _lieu){
        this.num=_num;
        this.nom=_nom;
        this.prenom=_prenom;
        this.adresse=_adresse;
        this.cp=_cp;
        this.ville=_ville;
        this.coeff=_coeff;
        this.lieu=_lieu;
    }

    public String getNum() {
        return this.num;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public String getCp() {
        return this.cp;
    }

    public String getVille() {
        return this.ville;
    }

    public String getCoeff() {
        return this.coeff;
    }

    public String getLieu() {
        return this.lieu;
    }
}
