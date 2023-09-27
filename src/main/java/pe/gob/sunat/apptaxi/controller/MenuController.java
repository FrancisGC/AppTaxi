package pe.gob.sunat.apptaxi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import pe.gob.sunat.apptaxi.App;
import pe.gob.sunat.apptaxi.util.Alertas;

public class MenuController {

    @FXML
    public void salirPrograma(ActionEvent actionEvent) {
        Alertas.mostrarAlertas("Salir", "Se está cerrando el programa", Alert.AlertType.INFORMATION);
        Stage stage = (Stage) App.scene.getWindow();
        stage.close();
    }
}
