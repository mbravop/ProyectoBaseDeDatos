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

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author mbravop
 */
public class AnadirDonadorController {


    @FXML
    private ComboBox<String> cmbTipoSangre;
    @FXML
    private ComboBox<String> cmbTipificacionSangre;
    @FXML
    private TextField txtCedulaDonador;
    @FXML
    private TextField txtNombreDonador;
    @FXML
    private TextField txtApellidoDonador;
    @FXML
    private ComboBox<String> cmbSexo;
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        cmbTipoSangre.getItems().addAll("A","B","AB","O");
        cmbTipificacionSangre.getItems().addAll("+","-");
        cmbSexo.getItems().addAll("M","F");
    }    
    
    @FXML
    private void anadirDonadorNuevo() throws IOException{ // HACER VALIDACIONES
        String cedula = txtCedulaDonador.getText();
        String nombre = txtNombreDonador.getText();
        String apellido = txtApellidoDonador.getText();
        String tipoS = cmbTipoSangre.getValue();
        String tipificacion = cmbTipificacionSangre.getValue();
        String sexo = cmbSexo.getValue();
                
        
        try{
            String consulta = "INSERT INTO Donador(cedulaD,nombre,apellido,sexo,tipoDeSangre,tipificacionSangre) VALUES ( '"+ cedula +"', '"+nombre+"', '"+apellido+"','"+sexo+"','"+tipoS+"','"+tipificacion+"')";
            PreparedStatement ps = App.conexionBaseDatos.prepareStatement(consulta);
            ps.executeUpdate();
            switchToMenuDonadores();
        }  catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToMenuDonadores() throws IOException {
        App.setRoot("menuDonadores");
    }

}
