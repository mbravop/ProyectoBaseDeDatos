/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import donacion.donacionsangre.modelo.Revision;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author mbravop
 */
public class MenuNuevoRevisionesController {


    ArrayList<Revision> listaRevisiones = new ArrayList<>();
    @FXML
    private Label lblNombreEnfermero;
    @FXML
    private Label lblIDEnfermero;
    @FXML
    private TableView<Revision> tvRevisiones;
    @FXML
    private TableColumn<Revision, Integer> colIdRevision;
    @FXML
    private TableColumn<Revision, Integer> colIdDonacion;
    @FXML
    private TableColumn<Revision, String> colNombreDonador;
    @FXML
    private TableColumn<Revision, String> colApellidoDonador;
    @FXML
    private TableColumn<Revision, String> colNombreEnfermero;
    @FXML
    private TableColumn<Revision, String> colApellidoEnfermero;
    @FXML
    private TableColumn<Revision, Date> colFecha;
    /**
     * Initializes the controller class.
     */
    public void initialize() throws Exception{
        lblNombreEnfermero.setText(LoginGeneralController.nombreUsuarioLog+ " "+LoginGeneralController.apellidoUsuarioLog);
        lblIDEnfermero.setText("ID #"+LoginGeneralController.idUsuarioLog);
        
        colIdRevision.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdDonacion.setCellValueFactory(new PropertyValueFactory<>("idDonacion"));
        colNombreDonador.setCellValueFactory(new PropertyValueFactory<>("nombreDonador"));
        colApellidoDonador.setCellValueFactory(new PropertyValueFactory<>("apellidoDonador"));
        colNombreEnfermero.setCellValueFactory(new PropertyValueFactory<>("nombreEnfermero"));
        colApellidoEnfermero.setCellValueFactory(new PropertyValueFactory<>("apellidoEnfermero"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaRevision"));
        llenarTabla();
    }    
    
    public void llenarTabla() throws Exception{
        ResultSet queryResult = obtenerRevisiones();
        while(queryResult.next()){
                listaRevisiones.add(new Revision(queryResult.getInt("idRevision"),
                                               queryResult.getInt("r.idDonacion"),
                                               queryResult.getString("d.nombre"),
                                               queryResult.getString("d.apellido"),
                                               queryResult.getString("e.nombre"),
                                               queryResult.getString("e.apellido"),
                                               queryResult.getDate("fechaRevision")
                ));
        }
        
        tvRevisiones.getItems().addAll(listaRevisiones);        
    }
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menuNuevoEnfermero");
    }
    
    @FXML
    private void switchToDonadores() throws IOException{
        App.setRoot("menuNuevoDonadores");
    }

    @FXML
    private void switchToDonaciones() throws IOException{
        App.setRoot("menuNuevoDonaciones");
    }

    @FXML
    private void switchToRevisiones() throws IOException{
        App.setRoot("menuNuevoRevisiones");
    }

    @FXML
    private void switchToSolicitudes() throws IOException{
        App.setRoot("menuNuevoSolicitudes");
    }

    @FXML
    private void switchToInicio() throws IOException{
        App.setRoot("loginGeneral");
    }
    
    private ResultSet obtenerRevisiones(){
        String query = "{CALL obtenerRevisiones()}";
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
