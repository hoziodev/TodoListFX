package model;

public class Type {

    private int id;
    private String nom;
    private String codeCouleur;

    public Type(int id, String nom, String codeCouleur) {
        this.id = id;
        this.nom = nom;
        this.codeCouleur = codeCouleur;
    }

    public Type(String nom, String codeCouleur) {
        this.nom = nom;
        this.codeCouleur = codeCouleur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodeCouleur() {
        return codeCouleur;
    }

    public void setCodeCouleur(String codeCouleur) {
        this.codeCouleur = codeCouleur;
    }
}
