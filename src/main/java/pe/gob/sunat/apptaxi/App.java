package pe.gob.sunat.apptaxi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent parent = App.loadFXML("login/login");
        scene = new Scene(parent);
        stage.setTitle("AppTaxi");
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("image/parada-de-taxi.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static FXMLLoader getFXMLLoader(String fxml) throws IOException {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    public static void main(String[] args) {
        launch();
    }
}