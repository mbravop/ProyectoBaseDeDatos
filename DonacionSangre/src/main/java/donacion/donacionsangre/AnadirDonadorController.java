/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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

    ArrayList<String> errores = new ArrayList<>();
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
        erroresValidacionesCampos();
        if(errores.size()>0){
            String cadenaErrores = "";
            for(String s: errores){
                cadenaErrores+= ( s + "\n");
            }
            
            App.crearAlerta(cadenaErrores);
        }else{
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
    }

    @FXML
    private void switchToMenuDonadores() throws IOException {
        App.setRoot("menuDonadores");
    }
    
    private void erroresValidacionesCampos(){
        errores.clear();
        boolean revisionCedula = txtCedulaDonador.getText().chars().allMatch( Character :: isDigit );
        boolean revisionNombre = txtNombreDonador.getText().chars().allMatch( Character :: isLetter);
        boolean revisionApellido = txtApellidoDonador.getText().chars().allMatch(Character :: isLetter);
        
        if(!revisionCedula || txtCedulaDonador.getText().length()!=10) errores.add("Número de cédula no válido");
        if(!revisionNombre) errores.add("Nombre no válido");
        if(txtNombreDonador.getText().length()>=50) errores.add("Campo de nombre supera 50 caracteres");
        if(!revisionApellido) errores.add("Nombre no válido");
        if(txtApellidoDonador.getText().length()>=50) errores.add("Campo de apellido supera 50 caracteres");
    }
    
}
