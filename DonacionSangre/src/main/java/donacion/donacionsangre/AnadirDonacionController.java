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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author mbravop
 */
public class AnadirDonacionController {
    
    boolean infoMostrada = false;
    public static int idDonador;
    public static int idBeneficiario;
    
    ArrayList<String> errores = new ArrayList<>();

    @FXML
    private DatePicker dpFechaDonacion;
    @FXML
    private TextField txtCedulaDonante;
    @FXML
    private TextField txtNombresDonante;
    @FXML
    private TextField txtSangreDonante;
    @FXML
    private CheckBox cbBeneficiario;
    @FXML
    private Label lblIdBeneficiario;
    @FXML
    private TextField txtIdBeneficiario;
    @FXML
    private Label lblNombresBeneficiario;
    @FXML
    private TextField txtNombresBeneficiario;
    @FXML
    private Label lblSangreBeneficiario;
    @FXML
    private TextField txtSangreBeneficiario;
    @FXML
    private TextField txtInfoEnfermero;
    @FXML
    private Button btnValidarBeneficiario;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        idBeneficiario = 0;
        txtInfoEnfermero.setText(SecondaryController.idEnfermeroLog + " - "+SecondaryController.nombreEnfermeroLog+" "+SecondaryController.apellidoEnfermeroLog);
    }    

    @FXML
    private void aparecerInfoBeneficiario() {
        if(!infoMostrada){
            aparecerBeneficiario(true);
        }else{
            aparecerBeneficiario(false);
            txtNombresBeneficiario.setText("");
            txtSangreBeneficiario.setText("");
            txtIdBeneficiario.setText("");
        }
    }

    @FXML
    private void switchToMenuDonaciones() throws IOException {
       App.setRoot("menuDonaciones");
    }
    
    private void aparecerBeneficiario(boolean b){
        lblIdBeneficiario.setVisible(b);
        txtIdBeneficiario.setVisible(b);
        lblNombresBeneficiario.setVisible(b);
        txtNombresBeneficiario.setVisible(b);
        lblSangreBeneficiario.setVisible(b);
        txtSangreBeneficiario.setVisible(b);
        btnValidarBeneficiario.setVisible(b);
        infoMostrada = b;
    }

    @FXML
    private void validarCedulaDonante() {
        if(!txtCedulaDonante.getText().chars().allMatch( Character :: isDigit ) || txtCedulaDonante.getText().length()!=10 ){
            App.crearAlerta("Numero de cédula no válido");
        }
        else{
            
            txtNombresDonante.setText("");
            txtSangreDonante.setText("");

            String cedula = txtCedulaDonante.getText();
            String busqueda = "SELECT idDonador, nombre, apellido, tipoDeSangre, tipificacionSangre FROM Donador WHERE cedulaD= '"+cedula+"'";

            try{
                Statement statement = App.conexionBaseDatos.createStatement();
                ResultSet queryResult = statement.executeQuery(busqueda);

                while(queryResult.next()){
                    idDonador = queryResult.getInt("idDonador");
                    txtNombresDonante.setText(queryResult.getString("nombre")+" "+queryResult.getString("apellido"));
                    txtSangreDonante.setText(queryResult.getString("tipoDeSangre")+" "+queryResult.getString("tipificacionSangre"));
                }
                statement.close();
            }catch(Exception e){
                e.printStackTrace();
            }

            if(txtNombresDonante.getText()=="") App.crearAlerta("No se ha encontrado un donador con ese número de cédula");
        }
    }

    @FXML
    private void validarIDBeneficiario(ActionEvent event) { //HACER VALIDACIONES!!!!!!!
        if(!txtIdBeneficiario.getText().chars().allMatch( Character :: isDigit )){
            App.crearAlerta("El ID del beneficiario no es válido");
        }
        else{
            txtNombresBeneficiario.setText("");
            txtSangreBeneficiario.setText("");
            idBeneficiario = 0;

            String id = txtIdBeneficiario.getText();
            String busqueda = "SELECT idBeneficiario, nombre, apellido, tipoDeSangre, tipificacionSangre FROM Beneficiario WHERE idBeneficiario= '"+id+"'";

            try{
                Statement statement = App.conexionBaseDatos.createStatement();
                ResultSet queryResult = statement.executeQuery(busqueda);

                while(queryResult.next()){
                    idBeneficiario = queryResult.getInt("idBeneficiario");
                    txtNombresBeneficiario.setText(queryResult.getString("nombre")+" "+queryResult.getString("apellido"));
                    txtSangreBeneficiario.setText(queryResult.getString("tipoDeSangre")+" "+queryResult.getString("tipificacionSangre"));
                }
                statement.close();
            }catch(Exception e){
                e.printStackTrace();
            }

            if(txtNombresBeneficiario.getText()=="") App.crearAlerta("No se ha encontrado un beneficiario con ese ID");
        }
    }

    @FXML
    private void agregarDonacion() throws IOException {     
        try{
            String consulta = "INSERT INTO Donacion(idDonador,idEnfermero,fechaDonacion,idDestino,aceptacion) VALUES ("+ idDonador +", "+SecondaryController.idEnfermeroLog+", DATE('"+String.valueOf(dpFechaDonacion.getValue())+"'),'"+idBeneficiario+"','-')";
            PreparedStatement ps = App.conexionBaseDatos.prepareStatement(consulta);
            ps.executeUpdate();
            switchToMenuDonaciones();
        }  catch(SQLException e){
            e.printStackTrace();
        }
        
        
    }

}
