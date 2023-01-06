/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import donacion.donacionsangre.modelo.Solicitud;
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
public class MenuNuevoSolicitudesBeneficiarioController{

    ArrayList<Solicitud> listaSolicitudes = new ArrayList<>();
    @FXML
    private Label lblNombreBeneficiario;
    @FXML
    private Label lblIDBeneficiario;
    @FXML
    private TableView<Solicitud> tvSolicitudes;
    @FXML
    private TableColumn<Solicitud, Integer> colIdSolicitud;
    @FXML
    private TableColumn<Solicitud, Integer> colCantidadSolicitada;
    @FXML
    private TableColumn<Solicitud, Integer> colCantidadRecibida;
    @FXML
    private TableColumn<Solicitud, Boolean> colSolicitudCompletada;
    @FXML
    private TableColumn<Solicitud, Date> colFechaSolicitud;
    /**
     * Initializes the controller class.
     */

    public void initialize() throws Exception{
        lblNombreBeneficiario.setText(LoginGeneralController.nombreUsuarioLog+ " "+LoginGeneralController.apellidoUsuarioLog);
        lblIDBeneficiario.setText("ID #"+LoginGeneralController.idUsuarioLog);
        
        colIdSolicitud.setCellValueFactory(new PropertyValueFactory<>("idSolicitud"));
        colCantidadSolicitada.setCellValueFactory(new PropertyValueFactory<>("cantidadSolicitada"));
        colCantidadRecibida.setCellValueFactory(new PropertyValueFactory<>("cantidadRecibida"));
        colSolicitudCompletada.setCellValueFactory(new PropertyValueFactory<>("realizada"));
        colFechaSolicitud.setCellValueFactory(new PropertyValueFactory<>("fechaSolicitud"));
        
        llenarTabla();
    }    
    
    public void llenarTabla() throws Exception{
       ResultSet queryResult = obtenerMisSolicitudes();
        while(queryResult.next()){
                listaSolicitudes.add(new Solicitud(queryResult.getInt("s.idSolicitud"),
                                               queryResult.getInt("s.cantidadSolicitada"),
                                               queryResult.getInt("s.cantidadRecibida"),
                                               queryResult.getDate("s.fechaSolicitud"),
                                               queryResult.getString("b.nombre"),
                                               queryResult.getString("b.apellido"),
                                               queryResult.getBoolean("s.realizada")
                ));
        }
        tvSolicitudes.getItems().addAll(listaSolicitudes);
        
    }
    
    @FXML
    private void switchToMenu() throws IOException{
        App.setRoot("menuNuevoBeneficiario");
    }

    @FXML
    private void switchToSolicitudes() throws IOException{
    }

    @FXML
    private void switchToInicio() throws IOException {
        App.setRoot("loginGeneral");
    }

    @FXML
    private void agregarSolicitud() throws IOException {
        App.setRoot("nuevoAnadirSolicitud");
    }
    
    private ResultSet obtenerMisSolicitudes(){
        String query = "{CALL obtenerMisSolicitudes(?)}";
        ResultSet resultadoBusqueda = null;
        try{
            CallableStatement statement = App.conexionBaseDatos.prepareCall(query);
            statement.setInt(1, LoginGeneralController.idUsuarioLog);
            resultadoBusqueda = statement.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultadoBusqueda;
    }

}
