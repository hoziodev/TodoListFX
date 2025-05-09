package repository;

import database.Database;
import model.Type;

import java.sql.*;
import java.util.ArrayList;

public class TypeRepository {

    private Connection connexion;

    public TypeRepository() {
        this.connexion = Database.getConnexion();
    }

    public ArrayList<Type> getAllTypes() {
        String sql = "SELECT * FROM type";
        ArrayList<Type> types = new ArrayList<>();

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Type type = new Type(rs.getInt(1),rs.getString(2), rs.getString(3));
                types.add(type);
            }
            System.out.println("Récupération réussi all");
        } catch (SQLException e) {
            System.out.println("Erreur recuperation all lite");
        }
        return types;
    }

    public ArrayList<Type> getAllTypesTache() {
        String sql = "SELECT * FROM Type INNER JOIN liste ON ref_liste = id_liste";
        ArrayList<Type> types = new ArrayList<>();

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Type type = new Type(rs.getInt(1),rs.getString(2), rs.getString(3));
                types.add(type);
            }
            System.out.println("Récupération réussi all");
        } catch (SQLException e) {
            System.out.println("Erreur recuperation all lite");
        }
        return types;
    }

    public boolean supType(int id) {
        String sql = "DELETE FROM Type WHERE id_Type = ?";

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
            return true;
        }catch (SQLException e) {
            System.out.println("Erreur sup Type");
            return false;
        }
    }

    public Type ajoutType(String nom, String couleur) {
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
