package appli.liste;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Tache;
import model.Type;
import repository.TypeRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TypeController implements Initializable {

    @FXML
    private TextField couleur;

    @FXML
    private TextField nomTache;



    @FXML
    private TableView<Type> tableauTache;

    TypeRepository rep = new TypeRepository();


    public void initialize(URL location, ResourceBundle resources) {
        String [][] colonnes = {
                { "Nom","nom" },
                {"Couleur","codeCouleur"},
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){
            //Création de la colonne avec le titre
            TableColumn<Type,String> maCol = new TableColumn<>(colonnes[i][0]);
            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Type,String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            tableauTache.getColumns().add(maCol);
        }

        ArrayList<Type> types = rep.getAllTypes();
        tableauTache.getItems().addAll(types);

    }

    @FXML
    void ajoutTache(ActionEvent event) {
        tableauTache.getItems().add(rep.ajoutType(nomTache.getText(), couleur.getText()));

    }

    @FXML
    void retourListe(ActionEvent event) throws IOException {
        StartApplication.changeScene("liste/Liste");
    }

    @FXML
    void supTache(ActionEvent event) {

    }

}
