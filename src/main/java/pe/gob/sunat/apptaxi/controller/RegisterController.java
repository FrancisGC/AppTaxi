package pe.gob.sunat.apptaxi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.synedra.validatorfx.ValidationMessage;
import net.synedra.validatorfx.Validator;
import org.mindrot.jbcrypt.BCrypt;
import pe.gob.sunat.apptaxi.App;
import pe.gob.sunat.apptaxi.controller.enums.PerfilEnum;
import pe.gob.sunat.apptaxi.model.dao.IUsuarioDao;
import pe.gob.sunat.apptaxi.model.dao.impl.UsuarioDaoImpl;
import pe.gob.sunat.apptaxi.model.entities.Usuario;
import pe.gob.sunat.apptaxi.util.Alertas;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RegisterController implements Initializable {

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtNombres;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private ComboBox<String> cmbPerfil;

    private final Validator validator = new Validator();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.llenarDatos();
        this.validaciones();
    }

    @FXML
    public void registrarUsuario() throws IOException {
        if (validator.validate()) {
            IUsuarioDao usuarioDao = new UsuarioDaoImpl();

            Integer existNumber = usuarioDao.findNumber(txtNumero.getText());

            if (existNumber == 0 ) {
                String hashed = BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt());
                Usuario usuario = new Usuario(txtNumero.getText(),
                        hashed,
                        cmbPerfil.getSelectionModel().getSelectedIndex(),
                        txtApellidos.getText(),
                        txtNombres.getText()
                );
                Integer resultado = usuarioDao.save(usuario);
                if (resultado == 1) {
                    Alertas.mostrarAlertas(null, "Registro satisfactorio", Alert.AlertType.INFORMATION);
                    FXMLLoader loader = App.getFXMLLoader("login/login");
                    Parent dashboard = loader.load();
                    App.scene.setRoot(dashboard);
                } else {
                    Alertas.mostrarAlertas(null, "Ocurrio un error al guardar el usuario", Alert.AlertType.ERROR);
                }
            } else {
                Alertas.mostrarAlertas(null, "Numero ya registrado", Alert.AlertType.ERROR);
            }
        } else {
            List<String> ls = validator.getValidationResult().getMessages().stream()
                    .map(ValidationMessage::getText).collect(Collectors.toList());
            Alertas.mostrarAlertas(null, String.join("\n", ls), Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void cancelarRegistro() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("login/login");
        Parent dashboard = loader.load();
        App.scene.setRoot(dashboard);
    }

    private void llenarDatos() {
        for (PerfilEnum estado : PerfilEnum.values()) {
            if (!estado.getValor().equals(PerfilEnum.ADMIN.getValor())) {
                cmbPerfil.getItems().add(estado.toString());
            }
        }
    }

    private void validaciones() {
        validator.createCheck()
                .withMethod(c -> {
                    String numero = c.get("numero");
                    if (numero.isEmpty()) {
                        c.error("Ingresar su número");
                    }
                })
                .dependsOn("numero", txtNumero.textProperty())
                .decorates(txtNumero);

        validator.createCheck()
                .withMethod(c -> {
                    String numero = c.get("numero");
                    if (!numero.matches("^[0-9]{9,15}$")) {
                        c.error("Número no valido");
                    }
                })
                .dependsOn("numero", txtNumero.textProperty())
                .decorates(txtNumero);

        validator.createCheck()
                .withMethod(c -> {
                    String apellidos = c.get("apellidos");
                    if (apellidos.isEmpty()) {
                        c.error("Ingresar su apellido");
                    }
                })
                .dependsOn("apellidos", txtApellidos.textProperty())
                .decorates(txtApellidos);

        validator.createCheck()
                .withMethod(c -> {
                    String nombres = c.get("nombres");
                    if (nombres.isEmpty()) {
                        c.error("Ingresar su nombre");
                    }
                })
                .dependsOn("nombres", txtNombres.textProperty())
                .decorates(txtNombres);

        validator.createCheck()
                .withMethod(c -> {
                    String password = c.get("password");
                    if (password.isEmpty()) {
                        c.error("Ingresar su contraseña");
                    }
                })
                .dependsOn("password", txtPassword.textProperty())
                .decorates(txtPassword);

        validator.createCheck()
                .withMethod(c -> {
                    String password = c.get("password");
                    String confirmPassword = c.get("confirmPassword");
                    if (!password.equals(confirmPassword)) {
                        c.error("Las contraseñas no coinciden");
                    }
                })
                .dependsOn("password", txtPassword.textProperty())
                .dependsOn("confirmPassword", txtConfirmPassword.textProperty())
                .decorates(txtConfirmPassword)
                .immediate();

        validator.createCheck()
                .withMethod(c -> {
                    Integer perfil = c.get("perfil");
                    if (perfil == -1) {
                        c.error("Seleccione su perfil");
                    }
                })
                .dependsOn("perfil", cmbPerfil.getSelectionModel().selectedIndexProperty())
                .decorates(cmbPerfil);
    }
}
