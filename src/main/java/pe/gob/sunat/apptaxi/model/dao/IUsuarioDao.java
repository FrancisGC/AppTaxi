package pe.gob.sunat.apptaxi.model.dao;

import pe.gob.sunat.apptaxi.model.entities.Usuario;

import java.util.List;

public interface IUsuarioDao {
    Integer save(Usuario usuario);
    List<Usuario> findAll();
    Usuario findById(Long id);
    Integer update(Usuario usuario);
    Integer deleteById(Long id);
    Integer findUserByUsuarioPassword(String numero, String password);

}
