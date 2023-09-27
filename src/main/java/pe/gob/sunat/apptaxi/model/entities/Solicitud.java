package pe.gob.sunat.apptaxi.model.entities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Solicitud {
    private ObjectProperty<Long> id;
    private StringProperty origen;
    private StringProperty destino;
    private ObjectProperty<Double> precio;
    private ObjectProperty<Integer> estado;
    private ObjectProperty<Long> idUsuario;
    private ObjectProperty<Date> fecRegistro;

    public Solicitud(Long id, String origen, String destino, Double precio, Integer estado, Long idUsuario, Date fecRegistro) {
        this.id = new SimpleObjectProperty<>(id);
        this.origen = new SimpleStringProperty(origen);
        this.destino = new SimpleStringProperty(destino);
        this.precio = new SimpleObjectProperty<>(precio);
        this.estado = new SimpleObjectProperty<>(estado);
        this.idUsuario = new SimpleObjectProperty<>(idUsuario);
        this.fecRegistro = new SimpleObjectProperty<>(fecRegistro);
    }

    public Solicitud(String origen, String destino, Double precio, Long idUsuario) {
        this.origen = new SimpleStringProperty(origen);
        this.destino = new SimpleStringProperty(destino);
        this.precio = new SimpleObjectProperty<>(precio);
        this.idUsuario = new SimpleObjectProperty<>(idUsuario);
    }

    public Solicitud(Long id, String origen, String destino, Double precio) {
        this.id = new SimpleObjectProperty<>(id);
        this.origen = new SimpleStringProperty(origen);
        this.destino = new SimpleStringProperty(destino);
        this.precio = new SimpleObjectProperty<>(precio);
    }

    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public String getOrigen() {
        return origen.get();
    }

    public void setOrigen(String origen) {
        this.origen.set(origen);
    }

    public String getDestino() {
        return destino.get();
    }

    public void setDestino(String destino) {
        this.destino.set(destino);
    }

    public Double getPrecio() {
        return precio.get();
    }

    public void setPrecio(Double precio) {
        this.precio.set(precio);
    }

    public Integer getEstado() {
        return estado.get();
    }

    public void setEstado(Integer estado) {
        this.estado.set(estado);
    }

    public Long getIdUsuario() {
        return idUsuario.get();
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario.set(idUsuario);
    }

    public Date getFecRegistro() {
        return fecRegistro.get();
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro.set(fecRegistro);
    }

    public ObjectProperty<Long> idProperty() {
        return id;
    }

    public StringProperty origenProperty() {
        return origen;
    }

    public StringProperty destinoProperty() {
        return destino;
    }

    public ObjectProperty<Double> precioProperty() {
        return precio;
    }

    public ObjectProperty<Integer> estadoProperty() {
        return estado;
    }
}
