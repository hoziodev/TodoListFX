module appli {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.security.crypto;

    opens appli to javafx.fxml;
    exports appli;

    opens appli.accueil to javafx.fxml;
    exports appli.accueil;

    opens appli.views to javafx.fxml;
    exports appli.views;

    opens model to javafx.fxml;
    exports model;
}