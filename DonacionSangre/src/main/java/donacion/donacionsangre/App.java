package donacion.donacionsangre;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import javafx.scene.control.Alert;

/**
 * JavaFX App
 */
public class App extends Application {
    
    public static final ConexionBDD baseDeDatos = new ConexionBDD();
    public static Connection conexionBaseDatos = baseDeDatos.getConnection();
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 800, 640);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void crearAlerta(String texto){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ha ocurrido un error");
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }

}