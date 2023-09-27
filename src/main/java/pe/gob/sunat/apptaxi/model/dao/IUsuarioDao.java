package pe.gob.sunat.apptaxi.model.dao;

import pe.gob.sunat.apptaxi.model.entities.Usuario;

import java.util.List;

public interface IUsuarioDao {
    Integer save(Usuario usuario);
    List<Usuario> findAll();
    Usuario findBynumber(String number);
    Integer update(Usuario usuario);
    Integer deleteById(Long id);
    String findPasswordByNumber(String numero);
    Integer findNumber(String numero);

}
