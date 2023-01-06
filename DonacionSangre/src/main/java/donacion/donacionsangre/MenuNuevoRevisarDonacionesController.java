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
import javafx.scene.control.Alert;

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
public class MenuNuevoRevisarDonacionesController {

    ArrayList<Donacion> listaDonacionesXRevisar = new ArrayList<>();

    @FXML
    private Label lblNombreEnfermero;
    @FXML
    private Label lblIDEnfermero;
    @FXML
    private TableView<Donacion> tvDonaciones;
    @FXML
    private TableColumn<Donacion, Integer> colIDDonacion;
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
    /**
     * Initializes the controller class.
     */

    public void initialize() {
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
    
    public void llenarTabla(){
        String busqueda = "SELECT d.idDonador, d.cedulaD, d.nombre, d.apellido, d.tipoDeSangre, d.tipificacionSangre, do.idDonacion, do.aceptacion, do.idEnfermero, do.idDestino, do.fechaDonacion FROM Donador d JOIN Donacion do ON do.idDonador = d.idDonador WHERE do.aceptacion='-'";

        try {
            Statement statement = App.conexionBaseDatos.createStatement();
            ResultSet queryResult = statement.executeQuery(busqueda);

            while (queryResult.next()) {
                listaDonacionesXRevisar.add(new Donacion(queryResult.getInt("do.idDonacion"),
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
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvDonaciones.getItems().addAll(listaDonacionesXRevisar);
    }
    
    
    @FXML
    private void switchToMenu() throws IOException{
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

    @FXML
    private void rechazarDonacion() throws IOException{
        Donacion d = (Donacion) tvDonaciones.getSelectionModel().getSelectedItem();
        if (d == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setContentText("Por favor seleccione una donacion para revisar ");
            alerta.showAndWait();
        } else {
            try {
                String consulta = "UPDATE Donacion SET aceptacion='N' WHERE idDonacion=" + d.getId() + "";
                PreparedStatement ps = App.conexionBaseDatos.prepareStatement(consulta);
                ps.executeUpdate();
                switchToDonaciones();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void aprobarDonacion() throws IOException{
        Donacion d = (Donacion) tvDonaciones.getSelectionModel().getSelectedItem();
        if (d == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setContentText("Por favor seleccione una donacion para revisar ");
            alerta.showAndWait();
        } else {
            try {
                String consulta = "UPDATE Donacion SET aceptacion='A' WHERE idDonacion=" + d.getId() + "";
                PreparedStatement ps = App.conexionBaseDatos.prepareStatement(consulta);
                ps.executeUpdate();
                switchToDonaciones();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
