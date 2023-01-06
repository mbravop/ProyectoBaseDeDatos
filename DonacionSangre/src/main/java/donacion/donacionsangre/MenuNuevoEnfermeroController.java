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
public class MenuNuevoEnfermeroController {

    @FXML
    private Label lblNombreEnfermero;
    @FXML
    private Label lblIDEnfermero;
    @FXML
    private Label lblCantidadDonacionesAtendidas;
    @FXML
    private Label lblCedulaEnfermero;
    @FXML
    private Label lblEspecialidadEnfermero;

    /**
     * Initializes the controller class.
     */

    public void initialize() {
        lblNombreEnfermero.setText(LoginGeneralController.nombreUsuarioLog+ " "+LoginGeneralController.apellidoUsuarioLog);
        lblIDEnfermero.setText("ID #"+LoginGeneralController.idUsuarioLog);
        lblCantidadDonacionesAtendidas.setText(LoginGeneralController.cantidadDonacionesUsuario+" donacion(es) atendida(s).");
        lblCedulaEnfermero.setText(LoginGeneralController.cedulaUsuarioLog);
        lblEspecialidadEnfermero.setText(LoginGeneralController.especialidadUsuarioLog);
    }    

    @FXML
    private void switchToDonadores() throws IOException {
        App.setRoot("menuNuevoDonadores");
    }

    @FXML
    private void switchToDonaciones() throws IOException {
        App.setRoot("menuNuevoDonaciones");
    }

    @FXML
    private void switchToRevisiones() throws IOException{
        App.setRoot("menuNuevoRevisiones");
    }

    @FXML
    private void switchToSolicitudes() throws IOException{
        App.setRoot("menuNuevoSolicitudes");
    }

    @FXML
    private void switchToInicio() throws IOException {
        App.setRoot("loginGeneral");
    }
    
}
