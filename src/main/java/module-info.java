module appli {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.security.crypto;
    requires javax.mail.api;

    opens appli to javafx.fxml;
    exports appli;

    opens appli.accueil to javafx.fxml;
    exports appli.accueil;

    opens appli.user to javafx.fxml;
    exports appli.user;

    opens model to javafx.fxml;
    exports model;
}