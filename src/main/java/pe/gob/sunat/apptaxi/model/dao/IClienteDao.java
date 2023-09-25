
package pe.gob.sunat.apptaxi.model.dao;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pe.gob.sunat.apptaxi.model.entities.Cliente;

public interface IClienteDao {
   // Métodos que pueden ser implementados por clases concretas
    Integer save(Cliente cliente);
    Integer update(Cliente cliente);
    Integer deleteById(Long id);
    Cliente findById(Long id);
    List<Cliente> findAll(); 
    public ObservableList<Cliente> CLIENTELIST = FXCollections.observableArrayList();
    
}
