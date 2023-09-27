package pe.gob.sunat.apptaxi.controller.interfaces;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pe.gob.sunat.apptaxi.model.entities.Solicitud;

public interface SolicitudInterface {
    public ObservableList<Solicitud> SOLICITUD_LIST = FXCollections.observableArrayList();
}
