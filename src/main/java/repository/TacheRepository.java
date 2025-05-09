package repository;

import database.Database;
import model.Tache;
import model.Type;

import java.sql.*;
import java.util.ArrayList;

public class TacheRepository {

    private Connection connexion;

    public TacheRepository() {
        this.connexion = Database.getConnexion();
    }

    public ArrayList<Tache> getAllTaches() {
        String sql = "SELECT * FROM tache";
        ArrayList<Tache> taches = new ArrayList<>();

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tache liste = new Tache(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5) );
                taches.add(liste);
            }
            System.out.println("Récupération réussi all");
        } catch (SQLException e) {
            System.out.println("Erreur recuperation all lite");
        }
        return taches;
    }

    public ArrayList<Tache> getAllTachesListe() {
        String sql = "SELECT * FROM tache INNER JOIN liste ON ref_liste = id_liste";
        ArrayList<Tache> taches = new ArrayList<>();

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Tache liste = new Tache(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5) );
                taches.add(liste);
            }
            System.out.println("Récupération réussi all");
        } catch (SQLException e) {
            System.out.println("Erreur recuperation all lite");
        }
        return taches;
    }

    public boolean supTache(int id) {
        String sql = "DELETE FROM tache WHERE id_tache = ?";

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("Erreur sup tache");
            return false;
        }
    }

    public Tache ajoutTache(String nom, int etat, int refListe, int refType) {
        String sql = "INSERT INTO tache(nom,etat,ref_liste,ref_type) VALUES(?,?,?,?)";
        int autoIncKeyFromApi = -1;
        ResultSet rs = null;
        Tache tache = null;

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,nom);
            stmt.setInt(2,etat);
            stmt.setInt(3,refListe);
            stmt.setInt(4,refType);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            }
            tache = new Tache(autoIncKeyFromApi,nom,etat,refListe,refType);
            System.out.println("Type ajouté avec succès !");
            return tache;
        }catch (SQLException e) {
            System.out.println("Erreur sup Type" + e.getMessage());
            return tache;
        }
    }
}
