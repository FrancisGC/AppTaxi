
package pe.gob.sunat.apptaxi.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import pe.gob.sunat.apptaxi.model.dao.IVehiculoDao;
import pe.gob.sunat.apptaxi.model.dao.impl.ClienteDaoImpl;
import pe.gob.sunat.apptaxi.model.dao.impl.VehiculoDaoImpl;
import pe.gob.sunat.apptaxi.model.entities.Vehiculo;

public class VehiculoController {
    //Columnas
    @FXML
    private TableColumn<Vehiculo, String> clmnModelo;
    @FXML
    private TableColumn<Vehiculo, String> clmnColor;
    @FXML
    private TableColumn<Vehiculo, Number> clmnAnio;
    @FXML
    private TableColumn<Vehiculo, String> clmnNumPlaca;
    @FXML
    private TableColumn<Vehiculo, Number> clmnIdUsuario;

    //Componentes GUI
    @FXML
    private TextField txtIdVehiculo;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtColor;
    @FXML
    private TextField txtAnio;
    @FXML
    private TextField txtNumPlaca;
    @FXML
    private TextField txtIdUsuario;
  
    //@FXML private RadioButton rbtFemenino;
    //@FXML private RadioButton rbtMasculino;

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnActualizar;


    @FXML
    private TableView<Vehiculo> tblViewVehiculos;

    //Colecciones
    private ObservableList<Vehiculo> listaVehiculos;
   // private Cliente clienteActual = new Cliente(0L, "", "", "", "");

    public void initialize(URL location, ResourceBundle resources) {

        //Inicializar listas

        listaVehiculos = FXCollections.observableArrayList();

        //Llenar listas
        //Cliente.llenarInformacionClientes(conexion.getConnection(), listaClientes);
        IVehiculoDao clienteDao = new VehiculoDaoImpl();
        listaVehiculos.addAll(clienteDao.findAll());
                /*
                 if (!CLIENTELIST.isEmpty()) {
                    CLIENTELIST.clear();
                }
                CLIENTELIST.addAll(clienteDao.findAll());
*/
        //Enlazar listas con ComboBox y TableView
        tblViewVehiculos.setItems(listaVehiculos);

        //Enlazar columnas con atributos
        clmnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        clmnColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        clmnAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        clmnNumPlaca.setCellValueFactory(new PropertyValueFactory<>("numPlaca"));
        clmnIdUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        
        gestionarEventos();

    }
    
    
    public void gestionarEventos() {
        tblViewVehiculos.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Vehiculo>() {
                    @Override
                    public void changed(ObservableValue<? extends Vehiculo> arg0,
                                        Vehiculo valorAnterior, Vehiculo valorSeleccionado) {
                        if (valorSeleccionado != null) {
                            txtIdVehiculo.setText(String.valueOf(valorSeleccionado.getIdVehiculo()));
                            txtModelo.setText(valorSeleccionado.getModelo());
                            txtColor.setText(valorSeleccionado.getColor());
                            txtAnio.setText(String.valueOf(valorSeleccionado.getAnio()));
                            txtNumPlaca.setText(valorSeleccionado.getNumPlaca());
                            txtIdUsuario.setText(String.valueOf(valorSeleccionado.getIdUusario()));


                            btnGuardar.setDisable(true);
                            btnEliminar.setDisable(false);
                            btnActualizar.setDisable(false);
                        }
                    }

                }
        );
    }

    @FXML
    public void guardarRegistro(ActionEvent event) {
        //Crear una nueva instancia del tipo Cliente
        Vehiculo ve = new Vehiculo(0L,
                txtModelo.getText(),
                txtColor.getText(),
                Integer.parseInt(txtAnio.getText()),
                txtNumPlaca.getText(),
                Long.parseLong(txtIdUsuario.getText()));

        if (ve.getIdVehiculo()== 0L) {
            if (ve.getModelo().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese Modelo", Alert.AlertType.WARNING);
                return;
            }

            if (ve.getColor().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese Color", Alert.AlertType.WARNING);
                return;
            }

            if (ve.getNumPlaca().isEmpty()) {
                mostrarAlertas("Warning", "Ingrese Nro Placa", Alert.AlertType.WARNING);
                return;
            }
            //Llamar al metodo guardarRegistro de la clase Alumno

            IVehiculoDao vehiculoDao = new VehiculoDaoImpl();

            int resultado = vehiculoDao.save(ve);



            if (resultado == 1) {
//                listaClientes.add(cli);
                //JDK 8u>40
                Alert mensaje = new Alert(AlertType.INFORMATION);
                mensaje.setTitle("Registro agregado");
                mensaje.setContentText("El registro ha sido agregado exitosamente");
                mensaje.setHeaderText("Resultado:");
                mensaje.show();
                
                //            listaVehiculos.clear();
            listaVehiculos.addAll(vehiculoDao.findAll());
            }
        }
    }

    @FXML
    public void actualizarRegistro(ActionEvent event) throws Exception {
        Vehiculo a = new Vehiculo(
                Long.parseLong(txtIdVehiculo.getText()),
                txtModelo.getText(),
                txtColor.getText(),
                Integer.parseInt(txtAnio.getText()),
                txtNumPlaca.getText(),
                Long.parseLong(txtIdUsuario.getText()));

        IVehiculoDao vehiculoDao = new VehiculoDaoImpl();

        //conexion.establecerConexion();
        int resultado = vehiculoDao.update(a);
        //conexion.cerrarConexion();

        if (resultado == 1) {
            listaVehiculos.set(tblViewVehiculos.getSelectionModel().getSelectedIndex(), a);
            //JDK 8u>40
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Registro actualizado");
            mensaje.setContentText("El registro ha sido actualizado exitosamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        }
    }

    @FXML
    public void eliminarRegistro(ActionEvent event) {
        //conexion.establecerConexion();
        //int resultado = tblViewClientes.getSelectionModel().getSelectedItem();
        //conexion.cerrarConexion();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Borrar Cliente");
        alert.setContentText("Confirmar SI/ No?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Vehiculo selectedVehiculo = tblViewVehiculos.getSelectionModel().getSelectedItem();
            IClienteDao clienteDao = new ClienteDaoImpl();
            clienteDao.deleteById(selectedVehiculo.getIdVehiculo());
            listaVehiculos.remove(selectedVehiculo);
        }

        tblViewVehiculos.getSelectionModel().clearSelection();

    }

    @FXML
    public void limpiarComponentes() {
        txtIdVehiculo.setText(null);
        txtModelo.setText(null);
        txtAnio.setText(null);
        txtNumPlaca.setText(null);
        clmnIdUsuario.setText(null);


        btnGuardar.setDisable(false);
        btnEliminar.setDisable(true);
        btnActualizar.setDisable(true);
    }


    private void mostrarAlertas(String header, String content, Alert.AlertType type) {
        Alert dialogo = new Alert(type);
        dialogo.setHeaderText(header);
        dialogo.setContentText(content);
        dialogo.show();
    }
    

}
