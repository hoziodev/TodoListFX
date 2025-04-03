package appli.accueil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import service.EmailService;

public class MotDePasseOublieController {

    @FXML
    private TextField codeRecu;

    @FXML
    private TextField email;

    @FXML
    private Button envoyerEmail;

    @FXML
    private Button modifMdp;

    @FXML
    private TextField nvMdp;

    @FXML
    void onCodeRempli(InputMethodEvent event) {
        if (codeRecu.getText() != null) {
            modifMdp.setDisable(false);
        }else {
            modifMdp.setDisable(true);
        }
    }

    @FXML
    void onEmailRempli(InputMethodEvent event) {
    }

    @FXML
    void onEnvoyerEmail(ActionEvent event) {
        String mail = email.getText();
        if (mail.isEmpty()) {
            System.out.println("Veuillez entrer une adresse e-mail.");
            return;
        }

        String code = EmailService.genererCode();
        EmailService.envoyerEmail(mail, "Réinitialisation de mot de passe", "Votre code de réinitialisation est : " + code);
        System.out.println("Code envoyé à : " + mail);
    }

    @FXML
    void onModifMdp(ActionEvent event) {

    }

}
