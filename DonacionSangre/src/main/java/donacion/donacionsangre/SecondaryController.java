package donacion.donacionsangre;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SecondaryController {
    
    public static int idEnfermeroLog;
    public static String nombreEnfermeroLog;
    public static String apellidoEnfermeroLog;
    
    @FXML
    private TextField txtIDEnfermero;

    @FXML
    private TextField txtPwEnfermero;


    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void realizarLogin(ActionEvent event) {
        validarLogin();
    }
    
    public void validarLogin(){
        
        String verificar = "SELECT count(1) FROM CredencialesEnfermeros WHERE idEnfermero = '" + txtIDEnfermero.getText() + "' AND contrasenaEnfermero = '" + txtPwEnfermero.getText() + "'";
        
        try{
            Statement statement = App.conexionBaseDatos.createStatement();
            ResultSet queryResult = statement.executeQuery(verificar);
            
            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    System.out.println("aprobado");
                    idEnfermeroLog = Integer.valueOf(txtIDEnfermero.getText());
                    buscarInfoEnfermero();
                    switchToMenuEnfermero();
                }else{
                    System.out.println("ERRONEO");
                }
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void switchToMenuEnfermero() throws IOException {
        App.setRoot("menuEnfermero");
    }
    
    private void buscarInfoEnfermero(){
        String busqueda = "SELECT nombre, apellido FROM Enfermero WHERE idEnfermero = "+ SecondaryController.idEnfermeroLog;
        
        try{
            Statement statement = App.conexionBaseDatos.createStatement();
            ResultSet queryResult = statement.executeQuery(busqueda);
            
            while(queryResult.next()){
                nombreEnfermeroLog = queryResult.getString("nombre");
                apellidoEnfermeroLog = queryResult.getString("apellido");
            }
            statement.close();   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}