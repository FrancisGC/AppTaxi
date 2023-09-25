
package pe.gob.sunat.apptaxi.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pe.gob.sunat.apptaxi.model.dao.IClienteDao;
import pe.gob.sunat.apptaxi.model.dao.impl.ClienteDaoImpl;
import pe.gob.sunat.apptaxi.model.entities.Cliente;

public class ClienteController {
    	//Columnas
	@FXML private TableColumn<Cliente,String> clmnNombre;
	@FXML private TableColumn<Cliente,String> clmnApellido;
	@FXML private TableColumn<Cliente,String> clmnEmail;
	@FXML private TableColumn<Cliente,String> clmnTelefono;
	
	//Componentes GUI
	@FXML private TextField txtIdCliente;
	@FXML private TextField txtNombre;
	@FXML private TextField txtApellido;
	@FXML private TextField txtEmail;
	//@FXML private RadioButton rbtFemenino;
	//@FXML private RadioButton rbtMasculino;
	@FXML private TextField txtTelefono;
        
	@FXML private Button btnGuardar;
	@FXML private Button btnEliminar;
	@FXML private Button btnActualizar;

	
	@FXML private TableView<Cliente> tblViewClientes;

	//Colecciones
	private ObservableList<Cliente> listaClientes;
        private Cliente clienteActual = new Cliente(0L, "", "", "", "");
	public void initialize(URL location, ResourceBundle resources) {
		
		//Inicializar listas
		
		listaClientes = FXCollections.observableArrayList();

		//Llenar listas
		//Cliente.llenarInformacionClientes(conexion.getConnection(), listaClientes);
                 IClienteDao clienteDao = new ClienteDaoImpl();
                listaClientes.addAll(clienteDao.findAll());
                /*
                 if (!CLIENTELIST.isEmpty()) {
                    CLIENTELIST.clear();
                }
                CLIENTELIST.addAll(clienteDao.findAll());
*/
		//Enlazar listas con ComboBox y TableView
		tblViewClientes.setItems(listaClientes);

		//Enlazar columnas con atributos
		clmnNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
		clmnApellido.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellido"));
		clmnEmail.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));
		clmnTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
		
		gestionarEventos();
		
	}

	public void gestionarEventos(){
		tblViewClientes.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Cliente>() {
					@Override
					public void changed(ObservableValue<? extends Cliente> arg0,
							Cliente valorAnterior, Cliente valorSeleccionado) {
							if (valorSeleccionado!=null){
								txtIdCliente.setText(String.valueOf(valorSeleccionado.getId()));
								txtNombre.setText(valorSeleccionado.getNombre());
								txtApellido.setText(valorSeleccionado.getApellido());
								txtEmail.setText(String.valueOf(valorSeleccionado.getEmail()));
                                                                txtTelefono.setText(String.valueOf(valorSeleccionado.getTelefono()));
								

								btnGuardar.setDisable(true);
								btnEliminar.setDisable(false);
								btnActualizar.setDisable(false);
							}
					}

				}
		);
	}

	@FXML
	public void guardarRegistro(ActionEvent event){
		//Crear una nueva instancia del tipo Cliente
		Cliente cli = new Cliente(0L,
					txtNombre.getText(),
					txtApellido.getText(),
					txtEmail.getText(),
					txtTelefono.getText());
                
            if (cli.getId()== 0L) {
            if (cli.getNombre().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese nombres", Alert.AlertType.WARNING);
                return;
            }

            if (cli.getApellido().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese Apellidos", Alert.AlertType.WARNING);    
                return;
            }

            if (cli.getEmail().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese Correo", Alert.AlertType.WARNING);
                return;
            }
            
             if (cli.getTelefono().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese Telefono", Alert.AlertType.WARNING);
                return;
            }
		//Llamar al metodo guardarRegistro de la clase Alumno
                
              IClienteDao clienteDao = new ClienteDaoImpl();
   
		int resultado = clienteDao.save(cli);
                listaClientes.clear();
                listaClientes.addAll(clienteDao.findAll());
	

		if (resultado == 1){
			listaClientes.add(cli);
			//JDK 8u>40
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Registro agregado");
			mensaje.setContentText("El registro ha sido agregado exitosamente");
			mensaje.setHeaderText("Resultado:");
			mensaje.show();
		}
	}
}
	@FXML
	public void actualizarRegistro(ActionEvent event) throws Exception{
		Cliente a = new Cliente(
				Integer.valueOf(txtIdCliente.getText()),
				txtNombre.getText(),
				txtApellido.getText(),
                                txtEmail.getText(),
                                txtTelefono.getText());
				
		IClienteDao clienteDao = new ClienteDaoImpl();		
				
		//conexion.establecerConexion();
		int resultado = clienteDao.update(a);
		//conexion.cerrarConexion();

		if (resultado == 1){
			listaClientes.set(tblViewClientes.getSelectionModel().getSelectedIndex(),a);
			//JDK 8u>40
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Registro actualizado");
			mensaje.setContentText("El registro ha sido actualizado exitosamente");
			mensaje.setHeaderText("Resultado:");
			mensaje.show();
		}
	}

	@FXML
	public void eliminarRegistro(ActionEvent event){
		//conexion.establecerConexion();
		//int resultado = tblViewClientes.getSelectionModel().getSelectedItem();
		//conexion.cerrarConexion();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Borrar Cliente");
        alert.setContentText("Confirmar SI/ No?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Cliente selectedCliente = tblViewClientes.getSelectionModel().getSelectedItem();
            IClienteDao clienteDao = new ClienteDaoImpl();	
            clienteDao.deleteById(selectedCliente.getId());
            listaClientes.remove(selectedCliente);
        }

        tblViewClientes.getSelectionModel().clearSelection();
                
	}

	@FXML
	public void limpiarComponentes(){
		txtIdCliente.setText(null);
		txtNombre.setText(null);
		txtApellido.setText(null);
		txtEmail.setText(null);
                txtTelefono.setText(null);
		

		btnGuardar.setDisable(false);
		btnEliminar.setDisable(true);
		btnActualizar.setDisable(true);
	}
        
        
    private void mostrarAlertas(String header, String content, Alert.AlertType type){
        Alert dialogo = new Alert(type);        
        dialogo.setHeaderText(header);
        dialogo.setContentText(content);
        dialogo.show();
    }
    
}
