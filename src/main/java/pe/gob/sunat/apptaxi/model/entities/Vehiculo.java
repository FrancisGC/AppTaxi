
package pe.gob.sunat.apptaxi.model.entities;

public class Vehiculo {
     // Atributos de la clase
    private Long idVehiculo;
    private String modelo;
    private String color;
    private int anio;
    private String numPlaca;
    private double precio;
    private Long idUsuario;

    public Vehiculo(long idVehiculo, String modelo, String color, int anio, String numPlaca, long precio) {
        this.idVehiculo = idVehiculo;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.numPlaca = numPlaca;
        this.precio = precio;
        this.idUsuario = idUsuario;
    }

   

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getNumPlaca() {
        return numPlaca;
    }

    public void setNumPlaca(String numPlaca) {
        this.numPlaca = numPlaca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Long getIdUusario() {
        return idUsuario;
    }

    public void setIdUusario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "idVehiculo=" + idVehiculo + ", modelo=" + modelo + ", color=" + color + ", anio=" + anio + ", numPlaca=" + numPlaca + ", precio=" + precio + ", idUusario=" + idUsuario + '}';
    }

   
}
