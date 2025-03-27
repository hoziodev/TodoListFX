package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import session.SessionUtilisateur;

import java.io.IOException;

public class AccueilControler {

    @FXML
    private Button deco;

    @FXML
    private Button verif;

    @FXML
    void onDeco(ActionEvent event) throws IOException {
        SessionUtilisateur.getInstance().deconnecter();
        StartApplication.changeScene("accueil/Login");
        System.out.println(SessionUtilisateur.getInstance().getUtilisateur());
    }

    @FXML
    void onVerif(ActionEvent event) throws IOException {
        System.out.println(SessionUtilisateur.getInstance().getUtilisateur());
        StartApplication.changeScene("user/GestionUser");
    }

}
