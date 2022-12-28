/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import donacion.donacionsangre.modelo.Donador;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
 * @author mbravop
 */
public class MenuDonadoresController {
    
    ArrayList<Donador> listaDonadores = new ArrayList<>();

    @FXML
    private TableView<Donador> tvDonadores;
    @FXML
    private TableColumn<Donador, Integer> colIdDonador;
    @FXML
    private TableColumn<Donador, String> colCedulaDonador;
    @FXML
    private TableColumn<Donador, String> colNombreDonador;
    @FXML
    private TableColumn<Donador, String> colApellidoDonador;
    @FXML
    private TableColumn<Donador, String> colSexoDonador;
    @FXML
    private TableColumn<Donador, String> colTipoDonador;
    @FXML
    private TableColumn<Donador, String> colTipificacionDonador;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        colIdDonador.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCedulaDonador.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombreDonador.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoDonador.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colSexoDonador.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colTipoDonador.setCellValueFactory(new PropertyValueFactory<>("tipoDeSangre"));
        colTipificacionDonador.setCellValueFactory(new PropertyValueFactory<>("tipificacionDeSangre"));
        llenarTabla();
    }    
    
    
    public void llenarTabla(){
        String busqueda = "SELECT * FROM Donador";
        
        try{
            Statement statement = App.conexionBaseDatos.createStatement();
            ResultSet queryResult = statement.executeQuery(busqueda);
            
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
            statement.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        tvDonadores.getItems().addAll(listaDonadores);
    }

    @FXML
    private void agregarDonador() throws IOException {
        App.setRoot("anadirDonador");
    }

    @FXML
    private void switchToMenuEnfermero() throws IOException {
        App.setRoot("menuEnfermero");
    }
    
}
