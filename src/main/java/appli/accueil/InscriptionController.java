package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UtilisateurRepository;
import model.Utilisateur;
import java.io.IOException;

public class InscriptionController {

    @FXML
    private TextField confirmmdp;

    @FXML
    private TextField email;

    @FXML
    private Label erreur;

    @FXML
    private Label validation;

    @FXML
    private TextField mdp;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @FXML
    void onInscription(ActionEvent event) {
        System.out.println(nom.getText());
        System.out.println(prenom.getText());
        System.out.println(email.getText());
        System.out.println(mdp.getText());
        System.out.println(encoder.encode(mdp.getText()));
        System.out.println(confirmmdp.getText());
        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || email.getText().isEmpty() || mdp.getText().isEmpty() || confirmmdp.getText().isEmpty()) {
            erreur.setText("Champs vide");
            System.out.println(erreur.getText());
        }else if ( !mdp.getText().equals(confirmmdp.getText())){
            erreur.setText("mdp != confirmmdp");
            System.out.println(erreur.getText());
        }else if (email.getText().equals("f@f")){
            erreur.setText("le email est déjà utilisé");
            System.out.println(erreur.getText());
        }else {
            Utilisateur user = new Utilisateur(nom.getText(), prenom.getText(), email.getText(),encoder.encode(mdp.getText()), "user");
            utilisateurRepository.ajouterUtilisateur(user);
            validation.setText("Incription réussie");
            System.out.println(validation.getText());
        }
    }

    @FXML
    void onRetour(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Login");
    }

}
