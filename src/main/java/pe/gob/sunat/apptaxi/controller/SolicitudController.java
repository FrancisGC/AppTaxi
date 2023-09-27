package pe.gob.sunat.apptaxi.controller;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.DoubleStringConverter;
import net.synedra.validatorfx.ValidationMessage;
import net.synedra.validatorfx.Validator;
import pe.gob.sunat.apptaxi.controller.enums.EstadoSolicitudEnum;
import pe.gob.sunat.apptaxi.controller.interfaces.SolicitudInterface;
import pe.gob.sunat.apptaxi.model.dao.ISolicitudDao;
import pe.gob.sunat.apptaxi.model.dao.impl.SolicitudDaoImpl;
import pe.gob.sunat.apptaxi.model.entities.Solicitud;
import pe.gob.sunat.apptaxi.model.entities.Usuario;
import pe.gob.sunat.apptaxi.util.Alertas;
import pe.gob.sunat.apptaxi.util.Constants;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SolicitudController implements Initializable, SolicitudInterface {

    @FXML
    private TableView<Solicitud> tableSolicitud;

    @FXML
    private TableColumn<Solicitud, Long> columnId;

    @FXML
    private TableColumn<Solicitud, String> columnOrigen;

    @FXML
    private TableColumn<Solicitud, String> columnDestino;

    @FXML
    private TableColumn<Solicitud, Double> columnPrecio;

    @FXML
    private TableColumn<Solicitud, String> columnEstado;

    @FXML
    private TextField txtOrigen;

    @FXML
    private TextField txtDestino;

    @FXML
    private TextField txtPrecio;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    private final Validator validator = new Validator();

    private ISolicitudDao solicitudDao = new SolicitudDaoImpl();

    private Solicitud solicitudSelected = new Solicitud(0L, "", "", null, 0, Usuario.getInstance().getId(), null);

    private Boolean flagGuardar = Boolean.FALSE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.disableForm(Boolean.TRUE);
        this.validaciones();
        btnGuardar.setDisable(Boolean.TRUE);
        btnCancelar.setDisable(Boolean.TRUE);

        this.cargarData();
        this.enlazarTabla();
        this.seleccionarElemento();
    }

    @FXML
    public void nuevaSolicitud() {
        this.disableForm(Boolean.FALSE);
        btnNuevo.setDisable(Boolean.TRUE);
        btnGuardar.setDisable(Boolean.FALSE);
        btnCancelar.setDisable(Boolean.TRUE);
        txtOrigen.requestFocus();
        this.seleccionarSolicitud(null);
        this.actualizarNombreBoton();
    }

    @FXML
    public void limpiar() {
        this.disableForm(Boolean.TRUE);
        btnGuardar.setDisable(Boolean.TRUE);
        btnCancelar.setDisable(Boolean.TRUE);
        btnNuevo.setDisable(Boolean.FALSE);
        flagGuardar = Boolean.FALSE;
        this.seleccionarSolicitud(null);
        this.actualizarNombreBoton();
    }

    @FXML
    public void guardarSolicitud() {
        if (btnGuardar.getText().equals(Constants.TEXT_GUARDAR)) {
            if (validator.validate()) {
                if (solicitudSelected.getId() == 0L) {
                    Integer resul = solicitudDao.save(
                            new Solicitud(
                                    txtOrigen.getText(),
                                    txtDestino.getText(),
                                    Double.parseDouble(txtPrecio.getText()),
                                    Usuario.getInstance().getId())
                    );

                    if (resul == 1) {
                        Alertas.mostrarAlertas(null, "Registro satisfactorio", Alert.AlertType.INFORMATION);
                        this.limpiar();
                        this.cargarData();
                    } else {
                        Alertas.mostrarAlertas(null, "Ocurrio un error al guardar la solicitud", Alert.AlertType.ERROR);
                    }
                } else {
                    Integer resul = solicitudDao.update(
                            new Solicitud(
                                    solicitudSelected.getId(),
                                    txtOrigen.getText(),
                                    txtDestino.getText(),
                                    Double.parseDouble(txtPrecio.getText()))
                    );

                    if (resul == 1) {
                        Alertas.mostrarAlertas(null, "Actualización satisfactoria", Alert.AlertType.INFORMATION);
                        this.limpiar();
                        this.cargarData();
                    } else {
                        Alertas.mostrarAlertas(null, "Ocurrio un error al actualizar la solicitud", Alert.AlertType.ERROR);
                    }
                }
            } else {
                List<String> ls = validator.getValidationResult().getMessages().stream()
                        .map(ValidationMessage::getText).collect(Collectors.toList());
                Alertas.mostrarAlertas(null, String.join("\n", ls), Alert.AlertType.WARNING);
            }
        } else {
            this.disableForm(Boolean.FALSE);
            this.flagGuardar = Boolean.TRUE;
            this.actualizarNombreBoton();
        }
    }

    @FXML
    private void cancelarSolicitud() {
        System.out.println(solicitudSelected.getId());
        if (solicitudSelected.getId() != 0L) {
            Integer resul = solicitudDao.cancelById(solicitudSelected.getId());
            if (resul == 1) {
                Alertas.mostrarAlertas(null, "Se cancelo la solicitud satisfactoria", Alert.AlertType.INFORMATION);
                this.limpiar();
                this.cargarData();
            } else {
                Alertas.mostrarAlertas(null, "Ocurrio un error al cancelar la solicitud", Alert.AlertType.ERROR);
            }
        }
    }

    private void disableForm(Boolean b) {
        txtOrigen.setDisable(b);
        txtDestino.setDisable(b);
        txtPrecio.setDisable(b);
    }

    private void validaciones() {
        validator.createCheck()
                .withMethod(c -> {
                    String origen = c.get("origen");
                    if (origen.isEmpty()) {
                        c.error("Ingresar el origen");
                    }
                })
                .dependsOn("origen", txtOrigen.textProperty())
                .decorates(txtOrigen);

        validator.createCheck()
                .withMethod(c -> {
                    String destino = c.get("destino");
                    if (destino.isEmpty()) {
                        c.error("Ingresar el destino");
                    }
                })
                .dependsOn("destino", txtDestino.textProperty())
                .decorates(txtDestino);

        validator.createCheck()
                .withMethod(c -> {
                    String precio = c.get("precio");
                    if (precio.isEmpty()) {
                        c.error("Ingresar el precio");
                    }
                })
                .dependsOn("precio", txtPrecio.textProperty())
                .decorates(txtPrecio);
    }

    private void cargarData() {
        if (!SOLICITUD_LIST.isEmpty()) {
            SOLICITUD_LIST.clear();
        }
        SOLICITUD_LIST.addAll(solicitudDao.findAllByUser(Usuario.getInstance().getId()));
    }

    private void enlazarTabla() {
        tableSolicitud.setItems(SOLICITUD_LIST);
        txtOrigen.textProperty().bindBidirectional(solicitudSelected.origenProperty());
        txtDestino.textProperty().bindBidirectional(solicitudSelected.destinoProperty());
        txtPrecio.textProperty().bindBidirectional(solicitudSelected.precioProperty(), new DoubleStringConverter());

        columnId.setCellValueFactory(row -> row.getValue().idProperty());
        columnOrigen.setCellValueFactory(row -> row.getValue().origenProperty());
        columnDestino.setCellValueFactory(row -> row.getValue().destinoProperty());
        columnPrecio.setCellValueFactory(row -> row.getValue().precioProperty());
        columnEstado.setCellValueFactory(row -> new SimpleStringProperty(EstadoSolicitudEnum.getStringValueFromInt(row.getValue().getEstado())));
    }

    private void seleccionarElemento() {
        tableSolicitud.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Solicitud> ov, Solicitud solicitud, Solicitud nuevaSolicitud) -> {
            this.seleccionarSolicitud(nuevaSolicitud);
            this.actualizarNombreBoton();
            this.disableForm(Boolean.TRUE);
        });
    }

    private void seleccionarSolicitud(Solicitud solicitud) {
        if (solicitud != null) {
            solicitudSelected.setId(solicitud.getId());
            solicitudSelected.setOrigen(solicitud.getOrigen());
            solicitudSelected.setDestino(solicitud.getDestino());
            solicitudSelected.setPrecio(solicitud.getPrecio());
            solicitudSelected.setEstado(solicitud.getEstado());
            solicitudSelected.setIdUsuario(solicitud.getIdUsuario());
            solicitudSelected.setFecRegistro(solicitud.getFecRegistro());
        } else {
            solicitudSelected.setId(0L);
            solicitudSelected.setOrigen("");
            solicitudSelected.setDestino("");
            solicitudSelected.setPrecio(null);
            solicitudSelected.setEstado(0);
            solicitudSelected.setIdUsuario(Usuario.getInstance().getId());
            solicitudSelected.setFecRegistro(null);
        }
    }

    private void actualizarNombreBoton() {
        StringBinding btnGuardarText = new StringBinding() {
            @Override
            protected String computeValue() {
                if (solicitudSelected.getId() == 0L || flagGuardar == Boolean.TRUE) {
                    return Constants.TEXT_GUARDAR;
                }
                btnGuardar.setDisable(Boolean.FALSE);
                btnNuevo.setDisable(Boolean.TRUE);
                btnCancelar.setDisable(Boolean.FALSE);
                return Constants.TEXT_ACTUALIZAR;
            }
        };
        btnGuardar.textProperty().bind(btnGuardarText);
    }
}
