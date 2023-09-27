package pe.gob.sunat.apptaxi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import net.synedra.validatorfx.ValidationMessage;
import net.synedra.validatorfx.Validator;
import org.mindrot.jbcrypt.BCrypt;
import pe.gob.sunat.apptaxi.App;
import pe.gob.sunat.apptaxi.model.dao.IUsuarioDao;
import pe.gob.sunat.apptaxi.model.dao.impl.UsuarioDaoImpl;
import pe.gob.sunat.apptaxi.util.Alertas;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LoginController implements Initializable {
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    private final Validator validator = new Validator();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validator.createCheck()
                .withMethod(c -> {
                    String userName = c.get("username");
                    if (userName.isEmpty()) {
                        c.error("Por favor ingrese su usuario");
                    }
                })
                .dependsOn("username", txtUsuario.textProperty())
                .decorates(txtUsuario);

        validator.createCheck()
                .withMethod(c -> {
                    String password = c.get("password");
                    if (password.isEmpty()) {
                        c.error("Por favor ingrese su contraseña");
                    }
                })
                .dependsOn("password", txtPassword.textProperty())
                .decorates(txtPassword);

        txtUsuario.setText("999955555");
        txtPassword.setText("123123");
    }

    @FXML
    public void autentificacionUsuario(ActionEvent event) throws IOException {
        if (validator.validate()) {
            IUsuarioDao usuarioDao = new UsuarioDaoImpl();
            String pswHash = usuarioDao.findPasswordByNumber(txtUsuario.getText());
            if (!pswHash.isEmpty()) {
                if (BCrypt.checkpw(txtPassword.getText(), pswHash)) {
                    FXMLLoader loader = App.getFXMLLoader("dashboard/dashboard");
                    Parent dashboard = loader.load();
                    App.scene.setRoot(dashboard);
                    Window window = App.scene.getWindow();
                    window.setWidth(1024);
                    window.setHeight(600);
                } else {
                    Alertas.mostrarAlertas(null, "Usuario o contraseña incorrecto", Alert.AlertType.WARNING);
                }
            } else {
                Alertas.mostrarAlertas(null, "Usuario o contraseña incorrecto", Alert.AlertType.WARNING);
            }
        } else {
            List<String> ls = validator.getValidationResult().getMessages().stream()
                    .map(ValidationMessage::getText).collect(Collectors.toList());
            Alertas.mostrarAlertas(null, String.join("\n", ls), Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void registrarUsuario() throws IOException {
        FXMLLoader loader =  App.getFXMLLoader("login/register");
        Parent dashboard = loader.load();
        App.scene.setRoot(dashboard);
    }
}
