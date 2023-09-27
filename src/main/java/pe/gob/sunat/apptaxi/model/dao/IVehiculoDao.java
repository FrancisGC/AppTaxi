package pe.gob.sunat.apptaxi.model.dao;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pe.gob.sunat.apptaxi.model.entities.Vehiculo;

public interface IVehiculoDao {
 public ObservableList<Vehiculo> VehiculoLIST = FXCollections.observableArrayList();  
   // Métodos que pueden ser implementados por clases concretas
    Integer save(Vehiculo vehiculo);
    Integer update(Vehiculo vehiculo);
    Integer deleteById(Long id);
    Vehiculo findById(Long id);
    List<Vehiculo> findAll(); 
}
