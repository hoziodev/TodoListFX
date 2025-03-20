package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UtilisateurRepository;
import session.SessionUtilisateur;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button Inscription;

    @FXML
    private Button connexion;

    @FXML
    private TextField emailField;

    @FXML
    private Label erreurConnexion;

    @FXML
    private Button mdplost;

    @FXML
    private Label validation;

    @FXML
    private PasswordField passwordField;

    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @FXML
    void onConnexion(ActionEvent event) throws IOException {
        System.out.println(emailField.getText());
        System.out.println(passwordField.getText());
        Utilisateur user = utilisateurRepository.getUtilisateurParEmail(emailField.getText());
        if (user == null) {
            validation.setText("Pas de compte existant");
            System.out.println(validation.getText());
        } else if (!encoder.matches(passwordField.getText(), user.getMot_de_passe())) {
            validation.setText("Mdp faux");
            System.out.println(validation.getText());
        } else {
            System.out.println("CONNECT");
            SessionUtilisateur.getInstance().sauvegardeSession(user);
            StartApplication.changeScene("accueil/Accueil");
        }
    }

    @FXML
    void onInscription(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Inscription");
    }

    @FXML
    void onMdpLost(ActionEvent event) {

    }

}
