package pe.gob.sunat.apptaxi.util;

import javafx.scene.control.Alert;

public class Alertas {
    public static void mostrarAlertas(String header, String content, Alert.AlertType type){
        Alert dialogo = new Alert(type);
        dialogo.setHeaderText(header);
        dialogo.setContentText(content);
        dialogo.showAndWait();
    }


}
