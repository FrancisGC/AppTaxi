
package pe.gob.sunat.apptaxi.model.entities;

public class Cliente {
   // Atributos de la clase
    private Long idCliente;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;

    // Constructor
    public Cliente(long par, String nombre, String apellido, String email, String telefono) {
        //this.idCliente = id;
        this.nombres = nombre;
        this.apellidos = apellido;
        this.email = email;
        this.telefono = telefono;
    }
 
    // Métodos getters y setters para acceder a los atributos
    public Long getId() {
        return idCliente;
    }

    public void setId(Long id) {
        this.idCliente= id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Método toString para representar el objeto como una cadena
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + idCliente+
                ", nombre='" + nombres + '\'' +
                ", apellido='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    } 
}
