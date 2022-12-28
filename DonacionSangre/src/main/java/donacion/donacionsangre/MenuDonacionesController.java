/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import donacion.donacionsangre.modelo.Donacion;
import donacion.donacionsangre.modelo.Donador;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author mbravop
 */
public class MenuDonacionesController{

    
    ArrayList<Donacion> listaDonaciones = new ArrayList<>();

    @FXML
    private TableView<Donacion> tvDonadores;
    @FXML
    private TableColumn<Donacion, Integer> colIdDonador;
    @FXML
    private TableColumn<Donacion, String> colCedulaDonador;
    @FXML
    private TableColumn<Donacion, Boolean> colestadoDonacion;
    @FXML
    private TableColumn<Donacion, String> colTipoSangre;
    @FXML
    private TableColumn<Donacion, String> colTipificacion;
    @FXML
    private TableColumn<Donacion, Date> colFecha;
    /**
     * Initializes the controller class.
     */
    
    
    public void initialize() {
        colIdDonador.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCedulaDonador.setCellValueFactory(new PropertyValueFactory<>("cedulaDonador"));
        colestadoDonacion.setCellValueFactory(new PropertyValueFactory<>("aceptacion"));
        colTipoSangre.setCellValueFactory(new PropertyValueFactory<>("tipoDeSangre"));
        colTipificacion.setCellValueFactory(new PropertyValueFactory<>("tipificacionSangre"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaDonacion"));
        llenarTabla();
    }    
    
    public void llenarTabla(){
        String busqueda = "SELECT d.idDonador, d.cedulaD, d.tipoDeSangre, d.tipificacionSangre, do.idDonacion, do.aceptacion, do.idEnfermero, do.idDestino, do.fechaDonacion FROM Donador d JOIN Donacion do ON do.idDonador = d.idDonador";
        
        try{
            Statement statement = App.conexionBaseDatos.createStatement();
            ResultSet queryResult = statement.executeQuery(busqueda);
            
            while(queryResult.next()){
                listaDonaciones.add(new Donacion(queryResult.getInt("do.idDonacion"),
                                               queryResult.getString("d.cedulaD"),
                                               queryResult.getInt("do.idEnfermero"),
                                               queryResult.getInt("do.idDestino"),
                                               queryResult.getBoolean("do.aceptacion"),
                                               queryResult.getString("d.tipoDeSangre"),
                                               queryResult.getString("d.tipificacionSangre"),
                                               queryResult.getDate("do.fechaDonacion")
                ));
            }
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tvDonadores.getItems().addAll(listaDonaciones);
    }
    
    @FXML
    private void switchToMenuEnfermero(ActionEvent event) {
    }

    @FXML
    private void agregarDonador(ActionEvent event) {
    }

}
