/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import java.io.IOException;
import java.net.URL;
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

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author mbravop
 */
public class NuevoAnadirDonacionController{

    boolean infoMostrada = false;
    public static int idDonador;
    public static int idBeneficiario;

    ArrayList<String> errores = new ArrayList<>();

    @FXML
    private Label lblNombreEnfermero;
    @FXML
    private Label lblIDEnfermero;
    @FXML
    private DatePicker dpFechaDonacion;
    @FXML
    private TextField txtNombresDonante;
    @FXML
    private TextField txtSangreDonante;
    @FXML
    private CheckBox cbBeneficiario;
    @FXML
    private Label lblIdBeneficiario;
    @FXML
    private Label lblNombresBeneficiario;
    @FXML
    private TextField txtNombresBeneficiario;
    @FXML
    private Label lblSangreBeneficiario;
    @FXML
    private TextField txtSangreBeneficiario;
    @FXML
    private TextField txtCedulaDonante;
    @FXML
    private TextField txtIdBeneficiario;
    @FXML
    private Button btnValidarBeneficiario;
    /**
     * Initializes the controller class.
     */

    public void initialize() {
        idBeneficiario = 0;
        lblNombreEnfermero.setText(LoginGeneralController.nombreUsuarioLog+ " "+LoginGeneralController.apellidoUsuarioLog);
        lblIDEnfermero.setText("ID #"+LoginGeneralController.idUsuarioLog);
    }    
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menuNuevoEnfermero");
    }

    @FXML
    private void switchToDonadores() throws IOException{
        App.setRoot("menuNuevoDonador");
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

    
    //Metodos de la pantalla
    
    @FXML
    private void realizarAgregarDonacion() throws IOException {
        boolean a = dpFechaDonacion.getValue() == null;
        boolean b = txtCedulaDonante.getText().isEmpty();
        boolean c = txtNombresBeneficiario.getText().isEmpty();
        if (infoMostrada) {
            if ((a) || (b) || (c)) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setContentText("Por favor llene todos los campos ");
                alerta.showAndWait();
            } else {
                try {
                    String consulta = "INSERT INTO Donacion(idDonador,idEnfermero,fechaDonacion,idDestino,aceptacion) VALUES (" + idDonador + ", " + LoginGeneralController.idUsuarioLog + ", DATE('" + String.valueOf(dpFechaDonacion.getValue()) + "'),'" + idBeneficiario + "','-')";
                    PreparedStatement ps = App.conexionBaseDatos.prepareStatement(consulta);
                    ps.executeUpdate();
                    switchToDonaciones();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else{
            if ((a) || (b)) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setContentText("Por favor llene todos los campos ");
                alerta.showAndWait();
            } else {
                try {
                    String consulta = "INSERT INTO Donacion(idDonador,idEnfermero,fechaDonacion,idDestino,aceptacion) VALUES (" + idDonador + ", " + LoginGeneralController.idUsuarioLog + ", DATE('" + String.valueOf(dpFechaDonacion.getValue()) + "'),'" + idBeneficiario + "','-')";
                    PreparedStatement ps = App.conexionBaseDatos.prepareStatement(consulta);
                    ps.executeUpdate();
                    switchToDonaciones();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void aparecerInfoBeneficiario() {
        if (!infoMostrada) {
            aparecerBeneficiario(true);
        } else {
            aparecerBeneficiario(false);
            txtNombresBeneficiario.setText("");
            txtSangreBeneficiario.setText("");
            txtIdBeneficiario.setText("");
        }
    }
    
    private void aparecerBeneficiario(boolean b) {
        lblIdBeneficiario.setVisible(b);
        txtIdBeneficiario.setVisible(b);
        lblNombresBeneficiario.setVisible(b);
        txtNombresBeneficiario.setVisible(b);
        lblSangreBeneficiario.setVisible(b);
        txtSangreBeneficiario.setVisible(b);
        btnValidarBeneficiario.setVisible(b);
        infoMostrada = b;
    }


    @FXML
    private void validarCedulaDonante(ActionEvent event) {
        txtNombresDonante.setText("");
        txtSangreDonante.setText("");
        if (!txtCedulaDonante.getText().chars().allMatch(Character::isDigit) || txtCedulaDonante.getText().length() != 10) {
            App.crearAlerta("Numero de cédula no válido");
        } else{

            String cedula = txtCedulaDonante.getText();
            String busqueda = "SELECT idDonador, nombre, apellido, tipoDeSangre, tipificacionSangre FROM Donador WHERE cedulaD= '" + cedula + "'";

            try {
                Statement statement = App.conexionBaseDatos.createStatement();
                ResultSet queryResult = statement.executeQuery(busqueda);

                while (queryResult.next()) {
                    idDonador = queryResult.getInt("idDonador");
                    txtNombresDonante.setText(queryResult.getString("nombre") + " " + queryResult.getString("apellido"));
                    txtSangreDonante.setText(queryResult.getString("tipoDeSangre") + " " + queryResult.getString("tipificacionSangre"));
                }
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (txtNombresDonante.getText() == "") {
                App.crearAlerta("No se ha encontrado un donador con ese número de cédula");
            }
        }
    }

    @FXML
    private void validarIDBeneficiario(ActionEvent event) {
        if (!txtIdBeneficiario.getText().chars().allMatch(Character::isDigit)) {
            App.crearAlerta("El ID del beneficiario no es válido");
        } else {
            txtNombresBeneficiario.setText("");
            txtSangreBeneficiario.setText("");
            idBeneficiario = 0;

            String id = txtIdBeneficiario.getText();
            String busqueda = "SELECT idBeneficiario, nombre, apellido, tipoDeSangre, tipificacionSangre FROM Beneficiario WHERE idBeneficiario= '" + id + "'";

            try {
                Statement statement = App.conexionBaseDatos.createStatement();
                ResultSet queryResult = statement.executeQuery(busqueda);

                while (queryResult.next()) {
                    idBeneficiario = queryResult.getInt("idBeneficiario");
                    txtNombresBeneficiario.setText(queryResult.getString("nombre") + " " + queryResult.getString("apellido"));
                    txtSangreBeneficiario.setText(queryResult.getString("tipoDeSangre") + " " + queryResult.getString("tipificacionSangre"));
                }
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (txtNombresBeneficiario.getText() == "") {
                App.crearAlerta("No se ha encontrado un beneficiario con ese ID");
            }
        }
    }

}
