/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import donacion.donacionsangre.modelo.Donador;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author mbravop
 */
public class MenuNuevoDonadoresController {

    ArrayList<Donador> listaDonadores = new ArrayList<>();
    @FXML
    private Label lblNombreEnfermero;
    @FXML
    private Label lblIDEnfermero;
    @FXML
    private TableColumn<Donador, String> colCedulaDonador;
    @FXML
    private TableColumn<Donador, String> colNombreDonador;
    @FXML
    private TableColumn<Donador, String> colApellidoDonador;
    @FXML
    private TableColumn<Donador, String> colSexoDonador;
    @FXML
    private TableColumn<Donador, String> colTipoSangreDonador;
    @FXML
    private TableColumn<Donador, String> colTipificacionDonador;
    @FXML
    private TableView<Donador> tvDonadores;
    /**
     * Initializes the controller class.
     */
    public void initialize() throws Exception{
        lblNombreEnfermero.setText(LoginGeneralController.nombreUsuarioLog+ " "+LoginGeneralController.apellidoUsuarioLog);
        lblIDEnfermero.setText("ID #"+LoginGeneralController.idUsuarioLog);
        
        colCedulaDonador.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombreDonador.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoDonador.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colSexoDonador.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colTipoSangreDonador.setCellValueFactory(new PropertyValueFactory<>("tipoDeSangre"));
        colTipificacionDonador.setCellValueFactory(new PropertyValueFactory<>("tipificacionDeSangre"));
        llenarTabla();
    }    
    
    private void llenarTabla() throws Exception{
        ResultSet queryResult = obtenerDonadores();
        while(queryResult.next()){
            listaDonadores.add(new Donador(queryResult.getInt("idDonador"),
                                           queryResult.getString("cedulaD"),
                                           queryResult.getString("nombre"),
                                           queryResult.getString("apellido"),
                                           queryResult.getString("sexo"),
                                           queryResult.getString("tipoDeSangre"),
                                           queryResult.getString("tipificacionSangre")
                                ));
        }
        tvDonadores.getItems().addAll(listaDonadores);
    }
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menuNuevoEnfermero");
    }
    
    @FXML
    private void switchToDonadores() throws IOException {
        
    }

    @FXML
    private void switchToDonaciones() throws IOException  {
        App.setRoot("menuNuevoDonaciones");
    }

    @FXML
    private void switchToRevisiones() throws IOException  {
        App.setRoot("menuNuevoRevisiones");
    }

    @FXML
    private void switchToSolicitudes() throws IOException  {
        App.setRoot("menuNuevoSolicitudes");
    }

    @FXML
    private void switchToInicio() throws IOException {
        App.setRoot("loginGeneral");
    }

    @FXML
    private void agregarDonador() throws IOException {
        App.setRoot("nuevoAnadirDonador");
    }
    
    //Procedimiento
    private ResultSet obtenerDonadores(){
        String query = "{CALL obtenerDonadores()}";
        ResultSet resultadoBusqueda = null;
        try{
            CallableStatement statement = App.conexionBaseDatos.prepareCall(query);
            resultadoBusqueda = statement.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultadoBusqueda;
    }
}
