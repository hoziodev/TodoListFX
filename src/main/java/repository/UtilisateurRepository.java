package repository;

import java.sql.*;
import java.util.ArrayList;

import database.Database;
import model.Utilisateur;

public class UtilisateurRepository {

    private Connection connexion;

    public UtilisateurRepository() {
        this.connexion = Database.getConnexion();
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, role) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getMot_de_passe());
            stmt.setString(5, utilisateur.getRole());
            stmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    public Utilisateur getUtilisateurParEmail(String email) {
        String sql = "SELECT * FROM utilisateur WHERE email = ?";
        Utilisateur utilisateur = null;
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                utilisateur = new Utilisateur(rs.getInt("id_utilisateur"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("mot_de_passe"), rs.getNString("role"));
            }
            System.out.println("Recupération user par email réussi");
        } catch (SQLException e) {
            System.out.println("Erreur recupération user");
        }

        return utilisateur;
    }

    public ArrayList<Utilisateur> getAllUtilisateurs() {
        String sql = "SELECT * FROM utilisateur";
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(rs.getInt("id_utilisateur"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("mot_de_passe"), rs.getNString("role"));
                utilisateurs.add(utilisateur);
            }
            System.out.println("Récupération réussi all");
        } catch (SQLException e) {
            System.out.println("Erreur recuperation all user");
        }
        return utilisateurs;
    }

    public boolean supprimerUtilisateurParEmail(String email) {
        String sql = "DELETE FROM utilisateur WHERE email = ?";

        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeUpdate();
            System.out.println("Suppresion réussi");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur supression user");
            return false;
        }
    }

    public boolean mettreAJourUtilisateur(Utilisateur utilisateur) {
        String sql = "UPDATE utilisateur SET nom = ?, prenom = ?, email = ?, role = ? WHERE id_utilisateur = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getRole());
            stmt.setInt(5, utilisateur.getId());
            stmt.executeUpdate();
            System.out.println("Modification réussi");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur modification");
            return false;
        }
    }

    public boolean mettreAJourMdp(Utilisateur utilisateur) {
        String sql = "UPDATE utilisateur SET mot_de_passe = ? WHERE email = ?";
        try {
            PreparedStatement stmt = connexion.prepareStatement(sql);
            stmt.setString(1, utilisateur.getMot_de_passe());
            stmt.setString(2, utilisateur.getEmail());
            stmt.executeUpdate();
            System.out.println("Modification réussi");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur modification");
            return false;
        }
    }

}
