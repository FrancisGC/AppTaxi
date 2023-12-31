package pe.gob.sunat.apptaxi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import pe.gob.sunat.apptaxi.App;
import pe.gob.sunat.apptaxi.util.Alertas;

import java.io.IOException;

public class MenuController {

    @FXML
    public void salirPrograma(ActionEvent actionEvent) {
        Alertas.mostrarAlertas("Se está cerrando el programa", null, Alert.AlertType.INFORMATION);
        Stage stage = (Stage) App.scene.getWindow();
        stage.close();
    }

    @FXML
    public void mostrarSolicitud(ActionEvent event) throws IOException {
        Parent dashboard = App.loadFXML("dashboard/solicitud");
        App.scene.setRoot(dashboard);
    }
    
     @FXML
    public void mostrarVehiculo(ActionEvent event) throws IOException {
        Parent dashboard = App.loadFXML("dashboard/vehiculo");
        App.scene.setRoot(dashboard);
    }

     @FXML
    public void mostrarDashboard(ActionEvent event) throws IOException {
        Parent dashboard = App.loadFXML("dashboard/dashboard");
        App.scene.setRoot(dashboard);
    }
    
       @FXML
    public void mostrarCliente(ActionEvent event) throws IOException {
        Parent dashboard = App.loadFXML("dashboard/cliente");
        App.scene.setRoot(dashboard);
    }
}
