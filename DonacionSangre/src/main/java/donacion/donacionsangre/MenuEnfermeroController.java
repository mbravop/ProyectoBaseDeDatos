/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mbravop
 */
public class MenuEnfermeroController{

    @FXML
    private Label lblInfoEnfermero;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        lblInfoEnfermero.setText("Bienvenid@, "+SecondaryController.nombreEnfermeroLog +" "+ SecondaryController.apellidoEnfermeroLog);
    }    

    @FXML
    private void switchToDonadores() throws IOException {
        App.setRoot("MenuDonadores");
    }
    
    @FXML
    private void switchToDonaciones() throws IOException  {
        App.setRoot("menuDonaciones");
    }

    @FXML
    private void switchToRevision() throws IOException {
        App.setRoot("menuRevisiones");
    }

    @FXML
    private void switchToSolicitudes() throws IOException {
        App.setRoot("menuSolicitud");
    }
    
}
