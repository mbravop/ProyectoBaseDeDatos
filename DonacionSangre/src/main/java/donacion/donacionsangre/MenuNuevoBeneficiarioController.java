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
public class MenuNuevoBeneficiarioController {


    @FXML
    private Label lblNombreBeneficiario;
    @FXML
    private Label lblIDBeneficiario;
    @FXML
    private Label lblSangreBeneficiario;
    @FXML
    private Label lblCantidadSolicitudesRealizadas;
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        lblNombreBeneficiario.setText(LoginGeneralController.nombreUsuarioLog+ " "+LoginGeneralController.apellidoUsuarioLog);
        lblIDBeneficiario.setText("ID #"+LoginGeneralController.idUsuarioLog);
        
        lblSangreBeneficiario.setText(LoginGeneralController.tipoSangreUsuario + " " + LoginGeneralController.tipificacionUsuario);
        lblCantidadSolicitudesRealizadas.setText(LoginGeneralController.cantidadSolicitudesUsuario + " solicitud(es) realizada(s)");
    }    
    
    
    
    @FXML
    private void switchToSolicitudes() throws IOException{
        App.setRoot("menuNuevoSolicitudesBeneficiario");
    }

    @FXML
    private void switchToInicio() throws IOException {
        App.setRoot("loginGeneral");
    }

}
