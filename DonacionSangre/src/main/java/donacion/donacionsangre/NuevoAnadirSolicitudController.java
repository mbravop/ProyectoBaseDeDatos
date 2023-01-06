/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author mbravop
 */
public class NuevoAnadirSolicitudController{


    @FXML
    private Label lblNombreBeneficiario;
    @FXML
    private Label lblIDBeneficiario;
    @FXML
    private TextField txtCantidadSolicitada;
    /**
     * Initializes the controller class.
     */
    public void initialize() {
       lblNombreBeneficiario.setText(LoginGeneralController.nombreUsuarioLog+ " "+LoginGeneralController.apellidoUsuarioLog);
       lblIDBeneficiario.setText("ID #"+LoginGeneralController.idUsuarioLog);
    }    
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menuNuevoBeneficiario");
    }

    @FXML
    private void switchToSolicitudes() throws IOException{
        App.setRoot("menuNuevoSolicitudesBeneficiario");
    }

    @FXML
    private void switchToInicio() throws IOException {
        App.setRoot("loginGeneral");
    }

    @FXML
    private void confirmarAgregarSolicitud() throws IOException {
        if (txtCantidadSolicitada.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setContentText("Por favor llene todos los campos ");
            alerta.showAndWait();
        } else {
            if (!txtCantidadSolicitada.getText().chars().allMatch(Character::isDigit)) {
                App.crearAlerta("La cantidad solicitada no es válida");
            }else{

                try {
                    String consulta = "INSERT INTO Solicitud(idBeneficiario,cantidadSolicitada,cantidadRecibida,fechaSolicitud,realizada) VALUES (" + LoginGeneralController.idUsuarioLog + ", " + txtCantidadSolicitada.getText() + ",0,CURDATE(),false)";
                    PreparedStatement ps = App.conexionBaseDatos.prepareStatement(consulta);
                    ps.executeUpdate();
                    switchToSolicitudes();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
