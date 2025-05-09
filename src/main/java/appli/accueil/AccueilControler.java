package appli.accueil;

import appli.StartApplication;
import appli.liste.ListeController;
import appli.user.ModificationUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Liste;
import model.Utilisateur;
import repository.ListeRepository;
import session.SessionUtilisateur;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AccueilControler implements Initializable {

    @FXML
    private Button deco;

    @FXML
    private Button verif;

    @FXML
    private Label sonNom;

    @FXML
    private TableView<Liste> tableauListe;

    @FXML
    private Label erreurCreation;

    @FXML
    private Label okCreation;

    @FXML
    private TextField nomListe;

    private Liste liste;

    ListeRepository listeRepository = new ListeRepository();

    public void initialize(URL location, ResourceBundle resources) {
        Utilisateur user = SessionUtilisateur.getInstance().getUtilisateur();
        if (!user.getRole().equals( "admin")){
            verif.setDisable(true);
        }
        sonNom.setText(user.getNom());
        String [][] colonnes = {
                { "Nom","nom" }
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){
            //Création de la colonne avec le titre
            TableColumn<Liste,String> maCol = new TableColumn<>(colonnes[i][0]);
            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Liste,String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            tableauListe.getColumns().add(maCol);
        }
        ArrayList<Liste> listes = listeRepository.getAllListesUser();
        tableauListe.getItems().addAll(listes);

    }

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

    @FXML
    void onModificationProfil(MouseEvent event) throws IOException {

    }

    @FXML
    void onMouseClickListe(MouseEvent event) throws IOException {
        liste = tableauListe.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 2) {
            if (liste != null) {
                StartApplication.changeScene("liste/Liste");
                ListeController controller = (ListeController)
                        StartApplication.getControllerFromStage();
                controller.initData(liste);
            }
        }
    }

    @FXML
    void onCreerListe(ActionEvent event) {
        int id = SessionUtilisateur.getInstance().getUtilisateur().getId();
        tableauListe.getItems().add(listeRepository.createListe(nomListe.getText(), id));
    }

    @FXML
    void onSupListe(ActionEvent event) {

    }
}
