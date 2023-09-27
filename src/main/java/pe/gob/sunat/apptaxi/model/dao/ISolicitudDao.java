package pe.gob.sunat.apptaxi.model.dao;

import pe.gob.sunat.apptaxi.model.entities.Solicitud;

import java.util.List;

public interface ISolicitudDao {
    //CRUD
    Integer save(Solicitud solicitud);
    List<Solicitud> findAllByUser(Long idUser);
    Solicitud findById(Long id);
    Integer update(Solicitud solicitud);
    Integer delete(Long id);
}
