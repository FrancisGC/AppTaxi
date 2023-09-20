package pe.gob.sunat.apptaxi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void autentificacionUsuario(ActionEvent event) {
        System.out.println(txtUsuario.getText());
        System.out.println(txtPassword.getText());
    }
}
