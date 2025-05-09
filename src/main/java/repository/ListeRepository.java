package repository;

import database.Database;
import model.Liste;
import session.SessionUtilisateur;

import java.sql.*;
import java.util.ArrayList;

public class ListeRepository {

    private Connection connexion;

    public ListeRepository() {
        this.connexion = Database.getConnexion();
    }

    public ArrayList<Liste> getAllListes() {
        String sql = "SELECT * FROM liste";
        ArrayList<Liste> listes = new ArrayList<>();

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Liste liste = new Liste(rs.getInt("id_liste"),rs.getString("nom"));
                listes.add(liste);
            }
            System.out.println("Récupération réussi all");
        } catch (SQLException e) {
            System.out.println("Erreur recuperation all lite");
        }
        return listes;
    }

    public ArrayList<Liste> getAllListesUser() {
        String sql = "SELECT * FROM liste INNER JOIN utilisateur_liste ON liste.id_liste = utilisateur_liste.ref_liste WHERE utilisateur_liste.ref_utilisateur = ?";
        ArrayList<Liste> listes = new ArrayList<>();

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setInt(1, SessionUtilisateur.getInstance().getUtilisateur().getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Liste liste = new Liste(rs.getInt(1),rs.getString(2));
                listes.add(liste);
            }
            System.out.println("Récupération réussi all");
        } catch (SQLException e) {
            System.out.println("Erreur recuperation all liste user"+e.getMessage());
        }
        return listes;
    }

    public Liste createListe(String nom, int id) {
        String sqlListe = "INSERT INTO liste(nom) VALUES (?)";
        Liste liste = new Liste(nom);
        String sqlLien = "INSERT INTO  utilisateur_liste VALUES (?,?)";
        int idListe = 0;
        ResultSet rs = null;
        int autoIncKeyFromApi = -1;


        try{
            PreparedStatement stmt = connexion.prepareStatement(sqlListe,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,liste.getNom());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            }
            System.out.println("Key returned from getGeneratedKeys():"
                    + autoIncKeyFromApi);
        }catch (SQLException e) {
            System.out.println("Erreur ajout liste user");
        }

        try{
            PreparedStatement stmt = connexion.prepareStatement(sqlLien);
            stmt.setInt(1,id);
            stmt.setInt(2,autoIncKeyFromApi);
            stmt.executeUpdate();
            System.out.println("Lien reussi");
            liste.setId(autoIncKeyFromApi);
            return liste;

        }catch (SQLException e) {
            System.out.println("Erreur ajout lien liste user");
            return null;
        }
    }
}
