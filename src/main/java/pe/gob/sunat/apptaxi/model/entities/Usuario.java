package pe.gob.sunat.apptaxi.model.entities;

import java.util.Date;

public class Usuario {
    private Long id;
    private String numero;
    private String password;
    private Integer perfil;
    private String apellidos;
    private String nombres;
    private Integer estado;
    private Date fechaRegistro;

    public Usuario() {
    }

    public Usuario(Long id, String numero, String password, Integer perfil, String apellidos, String nombres, Integer estado, Date fechaRegistro) {
        this.id = id;
        this.numero = numero;
        this.password = password;
        this.perfil = perfil;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPerfil() {
        return perfil;
    }

    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
