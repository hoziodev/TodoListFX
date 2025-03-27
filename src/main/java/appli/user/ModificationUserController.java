package appli.user;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;

import java.io.IOException;

public class ModificationUserController {

    @FXML
    private TextField email;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField role;
    private Utilisateur utilisateurSel;

    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @FXML
    void onAccueil(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/Accueil");
    }

    @FXML
    void onModif(ActionEvent event) throws IOException {
        utilisateurSel.setNom(nom.getText());
        utilisateurSel.setPrenom(prenom.getText());
        utilisateurSel.setEmail(email.getText());
        utilisateurSel.setRole(role.getText());
        if (utilisateurRepository.mettreAJourUtilisateur(utilisateurSel)) {
            StartApplication.changeScene("user/GestionUser");
        }
    }

    public void initData(Utilisateur utilisateur) {
        this.utilisateurSel = utilisateur;
        nom.setText(utilisateur.getNom());
        prenom.setText(utilisateur.getPrenom());
        email.setText(utilisateur.getEmail());
        role.setText( utilisateur.getRole());
    }


}
