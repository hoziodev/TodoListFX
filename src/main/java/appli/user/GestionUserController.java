package appli.user;

import appli.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import model.Utilisateur;
import repository.UtilisateurRepository;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GestionUserController implements Initializable {
    @FXML
    private TableView<Utilisateur> tableau;
    @FXML
    private Button btnDeco;

    @FXML
    private Button btnSupprimer;

    private Utilisateur selection;

    private UtilisateurRepository userRepository  = new UtilisateurRepository();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String [][] colonnes = {
                { "Id Utilisateur","id" },
                { "Nom","nom" },
                { "Prénom","prenom" },
                { "Email","email" },
                { "Rôle","role" },
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){
            //Création de la colonne avec le titre
            TableColumn<Utilisateur,String> maCol = new TableColumn<>(colonnes[i][0]);
            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Utilisateur,String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            tableau.getColumns().add(maCol);
        }
        ArrayList<Utilisateur> utilisateurs = userRepository.getAllUtilisateurs();
        tableau.getItems().addAll(utilisateurs);

    }

    @FXML
    void onMouseClicked(MouseEvent event) throws IOException {
        selection = tableau.getSelectionModel().getSelectedItem();
        if (selection != null) {
            btnSupprimer.setDisable(false);
        }else{
            btnSupprimer.setDisable(true);
        }
        if (event.getClickCount() == 2) {
            if (selection != null) {
                StartApplication.changeScene("user/ModificationUser");
                ModificationUserController controller = (ModificationUserController)
                        StartApplication. getControllerFromStage();
                controller.initData(selection);
            }
        }

    }

    @FXML
    void onDeco(ActionEvent event) {

    }

    @FXML
    void onSup(ActionEvent event) {
        System.out.println(selection.getEmail());
        if(userRepository.supprimerUtilisateurParEmail(selection.getEmail())){
            tableau.getItems().remove(selection);
        }
    }

    @FXML
    void onAccueil(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/AccueilView");
    }
}
