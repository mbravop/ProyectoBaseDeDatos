/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import donacion.donacionsangre.modelo.Solicitud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
 * @author Dereck Santander
 */
public class MenuSolicitudBeneficiarioController implements Initializable {
    
    ArrayList<Solicitud> listaSolicitudes= new ArrayList<>();

    @FXML
    private TableView<Solicitud> tvSolicitudes;
    @FXML
    private TableColumn<Solicitud, Integer> colIdSolicitud;
    @FXML
    private TableColumn<Solicitud, Integer> colcantidadSolicitada;
    @FXML
    private TableColumn<Solicitud, Integer> colcantidadRecibida;
    @FXML
    private TableColumn<Solicitud, Date> colfechaSolicitud;
    @FXML
    private TableColumn<Solicitud, Boolean> colCompletado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colIdSolicitud.setCellValueFactory(new PropertyValueFactory<>("idSolicitud"));
        colcantidadSolicitada.setCellValueFactory(new PropertyValueFactory<>("cantidadSolicitada"));
        colcantidadRecibida.setCellValueFactory(new PropertyValueFactory<>("cantidadRecibida"));
        colfechaSolicitud.setCellValueFactory(new PropertyValueFactory<>("fechaSolicitud"));
        colCompletado.setCellValueFactory(new PropertyValueFactory<>("realizada"));
        llenarTabla();
    }    
    
    private void llenarTabla(){
        String consulta= "SELECT s.idSolicitud,s.cantidadSolicitada, s.cantidadRecibida, s.fechaSolicitud,s.idBeneficiario, s.realizada "
                + "FROM Solicitud s WHERE s.idBeneficiario="+LogInBeneficiarioController.idBeneficiarioLog+"";
        try{
            Statement statement = App.conexionBaseDatos.createStatement();
            ResultSet queryResult = statement.executeQuery(consulta);
            
            while(queryResult.next()){
                listaSolicitudes.add(new Solicitud(queryResult.getInt("s.idSolicitud"),
                                               queryResult.getInt("s.cantidadSolicitada"),
                                               queryResult.getInt("s.cantidadRecibida"),
                                               queryResult.getDate("s.fechaSolicitud"),
                                               queryResult.getInt("s.idBeneficiario"),
                                               queryResult.getBoolean("s.realizada")
                ));
            }
            statement.close();
        }catch(Exception e){
            
            e.printStackTrace();
        }
        tvSolicitudes.getItems().addAll(listaSolicitudes);
    }
    
    @FXML
    private void switchToMenuUsuario() throws IOException {
        App.setRoot("logInBeneficiario");
    }

    @FXML
    private void agregarSolicitud() throws IOException {
        App.setRoot("anadirSolicitud");
    }

}
