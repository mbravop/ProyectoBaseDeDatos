/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author mbravop
 */
public class LoginGeneralController {
    
    
    //INFORMACION PERSONA LOGGEADA
    public static int idUsuarioLog;
    
    public static String nombreUsuarioLog;
    public static String apellidoUsuarioLog;
    public static String cedulaUsuarioLog;
    public static String especialidadUsuarioLog;
    
    public static int cantidadDonacionesUsuario;
    public static int cantidadSolicitudesUsuario;
    
    public static String tipoSangreUsuario;
    public static String tipificacionUsuario;
    
    @FXML
    private TextField txtIDUsuario;
    @FXML
    private PasswordField txtPwUsuario;
    @FXML
    private ComboBox<String> cmbTipoUsuario;
    /**
     * Initializes the controller class.
     */

    public void initialize() {
        cmbTipoUsuario.getItems().addAll("Enfermero","Beneficiario");
    } 

    @FXML
    private void realizarLogin() throws Exception{
        String tipoUsuario = cmbTipoUsuario.getValue();
        boolean incompletoID = txtIDUsuario.getText().length()==0;
        boolean incompletoPW = txtPwUsuario.getText().length()==0;
        
        if(incompletoID||incompletoPW){
            App.crearAlerta("Debe llenar todos los campos");
        }else{
            if(tipoUsuario!=null){
                if(tipoUsuario=="Enfermero"){
                    loginEnfermero();
                }else if(tipoUsuario=="Beneficiario"){
                    loginBeneficiario();
                }
            }else{
                App.crearAlerta("Debe seleccionar un tipo de usuario");
            }
        }
    }
    
    private void loginEnfermero() throws Exception{
        ResultSet queryResult = verificarLogin("CredencialesEnfermeros");
        
        while(queryResult.next()){
            if(queryResult.getInt(1)==1){
                idUsuarioLog = Integer.valueOf(txtIDUsuario.getText());
                buscarInfoEnfermero();
                buscarCantidadDonaciones();
                switchToMenuEnfermero();
            }else{
                App.crearAlerta("El ID o clave son incorrectos. \nRevise nuevamente");
            }
        }
    }
    
    private void switchToMenuEnfermero() throws IOException {
        App.setRoot("menuNuevoEnfermero");
    }
    
    private void buscarInfoEnfermero() throws SQLException{
        ResultSet queryResult = buscarInfoUsuario("enfermero",idUsuarioLog);
        while(queryResult.next()){
            nombreUsuarioLog = queryResult.getString("nombre");
            apellidoUsuarioLog = queryResult.getString("apellido");
            cedulaUsuarioLog = queryResult.getString("cedulaE");
            especialidadUsuarioLog = queryResult.getString("especialidad");
        }
    }
    
    private void buscarCantidadDonaciones() throws SQLException{
        ResultSet queryResult = buscarCantidadEspecifica("enfermero",idUsuarioLog);
        while(queryResult.next()){
                cantidadDonacionesUsuario = queryResult.getInt("cantidad");
        }
    }
    
    private void loginBeneficiario() throws Exception{
        ResultSet queryResult = verificarLogin("CredencialesBeneficiarios");
        
        while(queryResult.next()){
            if(queryResult.getInt(1)==1){
                idUsuarioLog = Integer.valueOf(txtIDUsuario.getText());
                buscarInfoBeneficiario();
                buscarCantidadSolicitudes();
                switchToMenuBeneficiario();
            }else{
                App.crearAlerta("El ID o clave son incorrectos. \nRevise nuevamente");
            }
        }
    }
    
    private void buscarInfoBeneficiario() throws SQLException{
        ResultSet queryResult = buscarInfoUsuario("beneficiario",idUsuarioLog);
        while(queryResult.next()){
            nombreUsuarioLog = queryResult.getString("nombre");
            apellidoUsuarioLog = queryResult.getString("apellido");
            tipoSangreUsuario = queryResult.getString("tipoDeSangre");
            tipificacionUsuario = queryResult.getString("tipificacionSangre");  
        }
    }
    
    private void buscarCantidadSolicitudes() throws SQLException{
        ResultSet queryResult = buscarCantidadEspecifica("beneficiario",idUsuarioLog);
        while(queryResult.next()){
                cantidadSolicitudesUsuario = queryResult.getInt("cantidad");
        }
    }
    
    private void switchToMenuBeneficiario() throws IOException{
        App.setRoot("menuNuevoBeneficiario");
    }
    
    //Procedimientos
    private ResultSet verificarLogin(String tabla){
        String query = "{CALL verificarLogin(?,?,?)}";
        ResultSet resultadoBusqueda = null;
        try{
            CallableStatement statement = App.conexionBaseDatos.prepareCall(query);
            statement.setString(1, tabla);
            statement.setInt(2, Integer.valueOf(txtIDUsuario.getText()));
            statement.setString(3, txtPwUsuario.getText());
            
            resultadoBusqueda = statement.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultadoBusqueda;
    }
    
    private ResultSet buscarInfoUsuario(String tabla, int idUsuario){
        String query = "{CALL buscarInfoUsuario(?,?)}";
        ResultSet resultadoBusqueda = null;
        try{
            CallableStatement statement = App.conexionBaseDatos.prepareCall(query);
            statement.setString(1, tabla);
            statement.setInt(2, idUsuario);
            
            resultadoBusqueda = statement.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultadoBusqueda;
    }
    
    private ResultSet buscarCantidadEspecifica(String tabla, int idUsuario){
        String query = "{CALL buscarCantidadEspecifica(?,?)}";
        ResultSet resultadoBusqueda = null;
        try{
            CallableStatement statement = App.conexionBaseDatos.prepareCall(query);
            statement.setString(1,tabla);
            statement.setInt(2,idUsuario);
            resultadoBusqueda = statement.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultadoBusqueda;
    }
     
}