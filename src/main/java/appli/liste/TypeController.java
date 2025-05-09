package appli.liste;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Type;
import repository.TypeRepository;

public class TypeController {

    @FXML
    private TextField couleur;

    @FXML
    private TextField nomTache;

    @FXML
    private TableView<Type> tableauTache;

    TypeRepository rep = new TypeRepository();

    @FXML
    void ajoutTache(ActionEvent event) {
        tableauTache.getItems().add(rep.ajoutType(nomTache.getText(), couleur.getText()));

    }

    @FXML
    void retourListe(ActionEvent event) {

    }

    @FXML
    void supTache(ActionEvent event) {

    }

}
