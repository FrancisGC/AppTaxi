package pe.gob.sunat.apptaxi.model.entities;

public class Conductor {
 // Atributos de la clase
    private int idConductor;
    private String nombres;
    private String apellidos;
    private String sexo;
    private int edad;
    private String numeroContacto;
    private Vehiculo vehiculo;

    // Constructor
    public Conductor(int idConductor, String nombres, String apellidos, String sexo, int edad, String numeroContacto, Vehiculo vehiculo) {   
        this.idConductor = idConductor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.edad = edad;
        this.numeroContacto = numeroContacto;
        this.vehiculo = vehiculo;
    }

    // Métodos getters y setters para acceder a los atributos
    public int getId() {
        return idConductor;
    }

    public void setId(int id) {
        this.idConductor = id;
    }

    public String getNombre() {
        return nombres;
    }

    public void setNombre(String nombre) {
        this.nombres = nombre;
    }

    public String getApellido() {
        return apellidos;
    }

    public void setApellido(String apellido) {
        this.apellidos = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNumeroLicencia() {
        return numeroContacto;
    }

    public void setNumeroLicencia(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    // Método toString para representar el objeto como una cadena
    @Override
    public String toString() {
        return "Conductor{" +
                "id=" + idConductor +
                ", nombre='" + nombres + '\'' +
                ", apellido='" + apellidos + '\'' +
                ", edad=" + edad +
                ", numeroContacto='" + numeroContacto + '\'' +
                '}';
    }   
}
