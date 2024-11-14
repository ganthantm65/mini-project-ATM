module com.example.atm {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires java.rmi;

    opens com.example.atm to javafx.fxml;
    exports com.example.atm;
}