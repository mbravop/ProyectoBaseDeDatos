/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import donacion.donacionsangre.modelo.Revision;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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
 * @author Dereck Santander
 */
public class MenuRevisionesController {

    ArrayList<Revision> listaRevisiones = new ArrayList<>();
    @FXML
    private TableView<Revision> tvRevisiones;
    @FXML
    private TableColumn<Revision, Integer> colIdRevision;
    @FXML
    private TableColumn<Revision, Integer> colIdEnfermero;
    @FXML
    private TableColumn<Revision, Integer> colIdDonacion;
    @FXML
    private TableColumn<Revision, Date> colFechaRevision;
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        colIdRevision.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdEnfermero.setCellValueFactory(new PropertyValueFactory<>("idEnfermero"));
        colIdDonacion.setCellValueFactory(new PropertyValueFactory<>("idDonacion"));
        colFechaRevision.setCellValueFactory(new PropertyValueFactory<>("fechaRevision"));
        llenarTabla();
    }    
    
    @FXML
    private void switchToMenuEnfermero() throws IOException {
        App.setRoot("menuEnfermero");
    }
    
    private void llenarTabla(){
        String busqueda = "SELECT * FROM Revision";
        
        try{
            Statement statement = App.conexionBaseDatos.createStatement();
            ResultSet queryResult = statement.executeQuery(busqueda);
            
            while(queryResult.next()){
                listaRevisiones.add(new Revision(queryResult.getInt("idRevision"),
                                               queryResult.getInt("idEnfermero"),
                                               queryResult.getInt("idDonacion"),
                                               queryResult.getDate("fechaRevision")
                ));
            }
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tvRevisiones.getItems().addAll(listaRevisiones);
    }

}
