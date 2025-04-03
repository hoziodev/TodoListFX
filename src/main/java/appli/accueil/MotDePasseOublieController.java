package appli.accueil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import model.Utilisateur;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UtilisateurRepository;
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

    private String code;

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

        code = EmailService.genererCode();
        EmailService.envoyerEmail(mail, "Réinitialisation de mot de passe", "Votre code de réinitialisation est : " + code);
        System.out.println("Code envoyé à : " + mail);
    }

    @FXML
    void onModifMdp(ActionEvent event) {
        if (nvMdp.getText() != null) {
            if (codeRecu.getText().equals(code)) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                Utilisateur user = new Utilisateur(email.getText(), encoder.encode(nvMdp.getText()));
                UtilisateurRepository userRepo = new UtilisateurRepository();
                userRepo.mettreAJourMdp(user);
            }
        }
    }

}
