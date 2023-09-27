package pe.gob.sunat.apptaxi.model.entities;

import java.util.Date;

public class Solicitud {
    private Long id;
    private String origen;
    private String destino;
    private Double precio;
    private Integer estado;
    private Long idUsuario;
    private Date fecRegistro;

    public Solicitud(Long id, String origen, String destino, Double precio, Integer estado, Long idUsuario, Date fecRegistro) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.fecRegistro = fecRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }
}
