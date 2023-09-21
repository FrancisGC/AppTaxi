module pe.gob.sunat.apptaxi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens pe.gob.sunat.apptaxi to javafx.fxml;
    opens pe.gob.sunat.apptaxi.controller to javafx.fxml;
    exports pe.gob.sunat.apptaxi;
}