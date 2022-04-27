package views;

public class bdd_medoc {
    private String code;
    private String nom;
    private String famille;
    private String composotion;
    private String effets;
    private String contre;
    private String prix;

    public bdd_medoc(String _code, String _nom, String _famille, String _composition, String _effets, String _contre, String _prix){
        this.code= _code;
        this.nom= _nom;
        this.famille= _famille;
        this.composotion= _composition;
        this.effets= _effets;
        this.contre=_contre;
        this.prix=_prix;
    }

    public String getCode() {
        return this.code;
    }
    public String getNom() {
        return this.nom;
    }

    public String getFamille() {
        return this.famille;
    }

    public String getComposotion() {
        return this.composotion;
    }

    public String getEffets() {
        return this.effets;
    }

    public String getContre() {
        return this.contre;
    }

    public String getPrix() {
        return this.prix;
    }
}
