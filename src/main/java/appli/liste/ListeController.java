package appli.liste;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Liste;
import model.Tache;
import repository.ListeRepository;
import repository.TacheRepository;
import session.SessionUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListeController {

    @FXML
    private Label nomListe;

    @FXML
    private TextField nomTache;

    @FXML
    private TableView<Tache> tableauTache;

    private Tache tache;


    private TacheRepository tacheRepository = new TacheRepository();

    public void initialize(URL location, ResourceBundle resources) {
        String [][] colonnes = {
                { "Nom","nom" },
                {"Etat","etat"},
        };

        for ( int i = 0 ; i < colonnes.length ; i ++ ){
            //Création de la colonne avec le titre
            TableColumn<Tache,String> maCol = new TableColumn<>(colonnes[i][0]);
            //Ligne permettant la liaison automatique de la cellule avec la propriété
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Tache,String>(colonnes[i][1]));
            //Ajout de la colonne dans notre tableau
            tableauTache.getColumns().add(maCol);
        }

        ArrayList<Tache> taches = tacheRepository.getAllTachesListe();
        tableauTache.getItems().addAll(taches);

    }

    @FXML
    void ajoutTache(ActionEvent event) {

    }

    @FXML
    void onMouseClickTache(MouseEvent event) throws IOException {

    }

    @FXML
    void supTache(ActionEvent event) {
        System.out.println(tache.getId());
        if(tacheRepository.supTache(tache.getId()));{
            tableauTache.getItems().remove(tache);
        }
    }

    public void initData(Liste liste) {
    }

    @FXML
    void allerType(ActionEvent event) throws IOException {
        StartApplication.changeScene("liste/Type");
    }
}