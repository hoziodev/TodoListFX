package repository;

import model.Tache;
import model.Type;

import java.sql.*;
import java.util.ArrayList;

public class TacheRepository {

    private Connection connexion;

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

    public Type ajoutTache(String nom, String couleur) {
        String sql = "INSERT INTO Type(nom,code_couleur) VALUES(?,?)";
        int autoIncKeyFromApi = -1;
        ResultSet rs = null;
        Type type = null;

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,nom);
            stmt.setString(2,couleur);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            }
            type = new Type(autoIncKeyFromApi,nom,couleur);
            System.out.println("Type ajouté avec succès !");
            return type;
        }catch (SQLException e) {
            System.out.println("Erreur sup Type" + e.getMessage());
            return type;
        }
    }
}
