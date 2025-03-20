package appli.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import model.Utilisateur;
import repository.UtilisateurRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {
    @FXML
    private TableView<Utilisateur> tableau;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UtilisateurRepository userRepository  = new UtilisateurRepository();
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
    void onMouseClicked(MouseEvent event) {

    }

    @FXML
    void supprimer(ActionEvent event) {

    }
}
