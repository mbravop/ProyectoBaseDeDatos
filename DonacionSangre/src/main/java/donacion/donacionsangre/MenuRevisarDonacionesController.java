/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import donacion.donacionsangre.modelo.Donacion;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Dereck Santander
 */
public class MenuRevisarDonacionesController implements Initializable {
    
    ArrayList<Donacion> listaDonacionesXRevisar= new ArrayList<>();
    
    @FXML
    private TableColumn<Donacion, Integer> colIdDonacion;
    @FXML
    private TableView<Donacion> tvDonacionesXRevisar;
    @FXML
    private TableColumn<Donacion, String> colCedulaDonador;
    @FXML
    private TableColumn<Donacion, String> colestadoDonacion;
    @FXML
    private TableColumn<Donacion, String> colTipoSangre;
    @FXML
    private TableColumn<Donacion, String> colTipificacion;
    @FXML
    private TableColumn<Donacion, Date> colFecha;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colIdDonacion.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCedulaDonador.setCellValueFactory(new PropertyValueFactory<>("cedulaDonador"));
        colestadoDonacion.setCellValueFactory(new PropertyValueFactory<>("aceptacion"));
        colTipoSangre.setCellValueFactory(new PropertyValueFactory<>("tipoDeSangre"));
        colTipificacion.setCellValueFactory(new PropertyValueFactory<>("tipificacionSangre"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaDonacion"));
        llenarTabla();
    }    
    
    
    @FXML
    private void switchToMenuDonaciones() throws IOException {
        App.setRoot("menuDonaciones");
    }

    public void llenarTabla(){
        String busqueda = "SELECT d.idDonador, d.cedulaD, d.tipoDeSangre, d.tipificacionSangre, do.idDonacion, do.aceptacion, do.idEnfermero, do.idDestino, do.fechaDonacion FROM Donador d JOIN Donacion do ON do.idDonador = d.idDonador WHERE do.aceptacion='-'";
        
        try{
            Statement statement = App.conexionBaseDatos.createStatement();
            ResultSet queryResult = statement.executeQuery(busqueda);
            
            while(queryResult.next()){
                listaDonacionesXRevisar.add(new Donacion(queryResult.getInt("do.idDonacion"),
                                               queryResult.getString("d.cedulaD"),
                                               queryResult.getInt("do.idEnfermero"),
                                               queryResult.getInt("do.idDestino"),
                                               queryResult.getString("do.aceptacion"),
                                               queryResult.getString("d.tipoDeSangre"),
                                               queryResult.getString("d.tipificacionSangre"),
                                               queryResult.getDate("do.fechaDonacion")
                ));
            }
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tvDonacionesXRevisar.getItems().addAll(listaDonacionesXRevisar);
    }
    
    @FXML
    private void aceptarDonacion() throws IOException{
        Donacion d= (Donacion) tvDonacionesXRevisar.getSelectionModel().getSelectedItem();
        try{
            String consulta = "UPDATE Donacion SET aceptacion='A' WHERE idDonacion="+d.getId()+"";
            PreparedStatement ps = App.conexionBaseDatos.prepareStatement(consulta);
            ps.executeUpdate();
            switchToMenuDonaciones();
        }  catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void rechazarDonacion() throws IOException {
        Donacion d= (Donacion) tvDonacionesXRevisar.getSelectionModel().getSelectedItem();
        try{
            String consulta = "UPDATE Donacion SET aceptacion='N' WHERE idDonacion="+d.getId()+"";
            PreparedStatement ps = App.conexionBaseDatos.prepareStatement(consulta);
            ps.executeUpdate();
            switchToMenuDonaciones();
        }  catch(SQLException e){
            e.printStackTrace();
        }
    }

}
