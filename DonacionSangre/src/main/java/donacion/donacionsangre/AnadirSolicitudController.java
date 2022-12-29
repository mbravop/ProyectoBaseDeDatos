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

import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Dereck Santander
 */
public class AnadirSolicitudController implements Initializable {


    @FXML
    private TextField txtNombresBeneficiario;
    @FXML
    private TextField txtCantidadSolicitada;
    @FXML
    private TextField txtIdBeneficiario;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtNombresBeneficiario.setText(LogInBeneficiarioController.nombreBeneficiarioLog+" "+LogInBeneficiarioController.apellidoBeneficiarioLog);
        txtIdBeneficiario.setText(LogInBeneficiarioController.idBeneficiarioLog+"");
    }    
    
    @FXML
    private void switchToMenuSolicitudes() throws IOException {
        App.setRoot("menuSolicitudBeneficiario");
    }

    @FXML
    private void agregarSolicitud() throws IOException {
        try{
            String consulta = "INSERT INTO Solicitud(idBeneficiario,cantidadSolicitada,cantidadRecibida,fechaSolicitud,realizada) VALUES ("+ LogInBeneficiarioController.idBeneficiarioLog +", "+txtCantidadSolicitada.getText()+",0,CURDATE(),false)";
            PreparedStatement ps = App.conexionBaseDatos.prepareStatement(consulta);
            ps.executeUpdate();
            switchToMenuSolicitudes();
        }  catch(SQLException e){
            e.printStackTrace();
        }
    }

}
