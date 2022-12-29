/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Dereck Santander
 */
public class LogInBeneficiarioController implements Initializable {
    
    public static int idBeneficiarioLog;
    public static String nombreBeneficiarioLog;
    public static String apellidoBeneficiarioLog;
    
    @FXML
    private TextField txtIDBeneficiario;
    @FXML
    private PasswordField txtPwBeneficiario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void realizarLogin(ActionEvent event) {
        validarLogin();
    }
    
    public void validarLogin(){
        
        String verificar = "SELECT count(1) FROM CredencialesBeneficiarios WHERE idBeneficiario = '" + txtIDBeneficiario.getText() + "' AND contrasenaBeneficiario = '" + txtPwBeneficiario.getText() + "'";
        
        try{
            Statement statement = App.conexionBaseDatos.createStatement();
            ResultSet queryResult = statement.executeQuery(verificar);
            
            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    System.out.println("aprobado");
                    idBeneficiarioLog = Integer.valueOf(txtIDBeneficiario.getText());
                    buscarInfoBeneficiario();
                    switchToMenuBeneficiario();
                }else{
                    System.out.println("ERRONEO");
                }
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void switchToMenuBeneficiario() throws IOException{
        App.setRoot("menuSolicitudBeneficiario");
    }
    
    private void buscarInfoBeneficiario(){
        String busqueda = "SELECT nombre, apellido FROM Beneficiario WHERE idBeneficiario = "+ idBeneficiarioLog;
        
        try{
            Statement statement = App.conexionBaseDatos.createStatement();
            ResultSet queryResult = statement.executeQuery(busqueda);
            
            while(queryResult.next()){
                nombreBeneficiarioLog = queryResult.getString("nombre");
                apellidoBeneficiarioLog = queryResult.getString("apellido");
            }
            statement.close();   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
