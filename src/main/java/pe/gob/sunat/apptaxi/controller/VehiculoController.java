
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.synedra.validatorfx.Validator;
import pe.gob.sunat.apptaxi.model.dao.IClienteDao;
import pe.gob.sunat.apptaxi.model.dao.IVehiculoDao;
import pe.gob.sunat.apptaxi.model.dao.impl.ClienteDaoImpl;
import pe.gob.sunat.apptaxi.model.dao.impl.VehiculoDaoImpl;
import pe.gob.sunat.apptaxi.model.entities.Usuario;
import pe.gob.sunat.apptaxi.model.entities.Vehiculo;

public class VehiculoController implements Initializable {
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
    private TableColumn<Vehiculo, Number> clmnMarca;

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
    private TextField txtMarca;

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

    private final Validator validator = new Validator();

    //Colecciones
    private ObservableList<Vehiculo> listaVehiculos;
    // private Cliente clienteActual = new Cliente(0L, "", "", "", "");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.validaciones();

        //Inicializar listas

        listaVehiculos = FXCollections.observableArrayList();

        //Llenar listas
        //Cliente.llenarInformacionClientes(conexion.getConnection(), listaClientes);
        IVehiculoDao clienteDao = new VehiculoDaoImpl();
        listaVehiculos.addAll(clienteDao.findAllById(Usuario.getInstance().getId()));
                /*
                 if (!CLIENTELIST.isEmpty()) {
                    CLIENTELIST.clear();
                }
                CLIENTELIST.addAll(clienteDao.findAll());
*/
        //Enlazar listas con ComboBox y TableView
        tblViewVehiculos.setItems(listaVehiculos);

        //Enlazar columnas con atributos
//        clmnMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
//        clmnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
//        clmnColor.setCellValueFactory(new PropertyValueFactory<>("color"));
//        clmnAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
//        clmnNumPlaca.setCellValueFactory(new PropertyValueFactory<>("numPlaca"));

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
        if (validator.validate()){
            Vehiculo ve = new Vehiculo(0L,
                    txtMarca.getText(),
                    txtModelo.getText(),
                    txtColor.getText(),
                    Integer.parseInt(txtAnio.getText()),
                    txtNumPlaca.getText(),
                    Usuario.getInstance().getId());

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
                listaVehiculos.addAll(vehiculoDao.findAllById(Usuario.getInstance().getId()));
            }
        }
    }

    @FXML
    public void actualizarRegistro(ActionEvent event) throws Exception {
        Vehiculo a = new Vehiculo(
                Long.parseLong(txtIdVehiculo.getText()),
                txtMarca.getText(),
                txtModelo.getText(),
                txtColor.getText(),
                Integer.parseInt(txtAnio.getText()),
                txtNumPlaca.getText(),
                Usuario.getInstance().getId());

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

    private void validaciones() {
        validator.createCheck()
                .withMethod(c -> {
                    String marca = c.get("marca");
                    if (marca.isEmpty()) {
                        c.error("Ingresar la marca");
                    }
                })
                .dependsOn("marca", txtMarca.textProperty())
                .decorates(txtMarca);

        validator.createCheck()
                .withMethod(c -> {
                    String modelo = c.get("modelo");
                    if (modelo.isEmpty()) {
                        c.error("Ingresar el modelo");
                    }
                })
                .dependsOn("modelo", txtModelo.textProperty())
                .decorates(txtModelo);

        validator.createCheck()
                .withMethod(c -> {
                    String color = c.get("color");
                    if (color.isEmpty()) {
                        c.error("Ingresar la marca");
                    }
                })
                .dependsOn("color", txtColor.textProperty())
                .decorates(txtColor);

        validator.createCheck()
                .withMethod(c -> {
                    String anio = c.get("anio");
                    if (anio.isEmpty()) {
                        c.error("Ingresar el año");
                    }
                })
                .dependsOn("anio", txtAnio.textProperty())
                .decorates(txtAnio);

        validator.createCheck()
                .withMethod(c -> {
                    String placa = c.get("placa");
                    if (placa.isEmpty()) {
                        c.error("Ingresar el número de placa");
                    }
                })
                .dependsOn("placa", txtNumPlaca.textProperty())
                .decorates(txtNumPlaca);
    }

    @FXML
    public void limpiarComponentes() {


        btnGuardar.setDisable(false);
        btnEliminar.setDisable(true);
        btnActualizar.setDisable(true);
    }


}
