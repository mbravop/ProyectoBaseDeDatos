/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import donacion.donacionsangre.modelo.Donacion;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Date;
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
public class MenuNuevoDonacionesController{

    ArrayList<Donacion> listaDonaciones = new ArrayList<>();
    @FXML
    private Label lblNombreEnfermero;
    @FXML
    private Label lblIDEnfermero;
    @FXML
    private TableView<Donacion> tvDonaciones;
    @FXML
    private TableColumn<Donacion, String> colCedulaDonador;
    @FXML
    private TableColumn<Donacion, String> colNombreDonador;
    @FXML
    private TableColumn<Donacion, String> colApellidoDonador;
    @FXML
    private TableColumn<Donacion, String> colEstadoDonacion;
    @FXML
    private TableColumn<Donacion, String> colTipoSangre;
    @FXML
    private TableColumn<Donacion, String> colTipificacion;
    @FXML
    private TableColumn<Donacion, Date> colFecha;
    @FXML
    private TableColumn<Donacion, Integer> colIDDonacion;
    
    /**
     * Initializes the controller class.
     */
    public void initialize() throws Exception{
        lblNombreEnfermero.setText(LoginGeneralController.nombreUsuarioLog+ " "+LoginGeneralController.apellidoUsuarioLog);
        lblIDEnfermero.setText("ID #"+LoginGeneralController.idUsuarioLog);
        
        colIDDonacion.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCedulaDonador.setCellValueFactory(new PropertyValueFactory<>("cedulaDonador"));
        colNombreDonador.setCellValueFactory(new PropertyValueFactory<>("nombreDonador"));
        colApellidoDonador.setCellValueFactory(new PropertyValueFactory<>("apellidoDonador"));
        colEstadoDonacion.setCellValueFactory(new PropertyValueFactory<>("aceptacion"));
        colTipoSangre.setCellValueFactory(new PropertyValueFactory<>("tipoDeSangre"));
        colTipificacion.setCellValueFactory(new PropertyValueFactory<>("tipificacionSangre"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaDonacion"));
        llenarTabla();
    }
    
    public void llenarTabla() throws Exception{
        ResultSet queryResult = obtenerDonaciones();
        while(queryResult.next()){
                listaDonaciones.add(new Donacion(queryResult.getInt("do.idDonacion"),
                                               queryResult.getString("d.cedulaD"),
                                               queryResult.getString("d.nombre"),
                                               queryResult.getString("d.apellido"),
                                               queryResult.getInt("do.idEnfermero"),
                                               queryResult.getInt("do.idDestino"),
                                               queryResult.getString("do.aceptacion"),
                                               queryResult.getString("d.tipoDeSangre"),
                                               queryResult.getString("d.tipificacionSangre"),
                                               queryResult.getDate("do.fechaDonacion")
                ));
            }        
        tvDonaciones.getItems().addAll(listaDonaciones);
    }
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menuNuevoEnfermero");
    }

    @FXML
    private void switchToDonadores() throws IOException {
        App.setRoot("menuNuevoDonadores");
    }

    @FXML
    private void switchToDonaciones() throws IOException {
        App.setRoot("menuNuevoDonaciones");
    }

    @FXML
    private void switchToRevisiones() throws IOException {
        App.setRoot("menuNuevoRevisiones");
    }

    @FXML
    private void switchToSolicitudes() throws IOException {
        App.setRoot("menuNuevoSolicitudes");
    }

    @FXML
    private void switchToInicio() throws IOException {
        App.setRoot("loginGeneral");
    }

    @FXML
    private void agregarDonacion() throws IOException{
        App.setRoot("nuevoAnadirDonacion");
    }

    @FXML
    private void revisarDonaciones() throws IOException {
        App.setRoot("menuNuevoRevisarDonaciones");
    }
    
    private ResultSet obtenerDonaciones(){
        String query = "{CALL obtenerDonaciones()}";
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
