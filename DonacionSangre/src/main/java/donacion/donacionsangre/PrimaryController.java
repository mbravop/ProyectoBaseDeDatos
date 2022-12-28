package donacion.donacionsangre;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {
    
    @FXML
    private Button btnEnfermero;

    @FXML
    private Button btnUsuario;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
