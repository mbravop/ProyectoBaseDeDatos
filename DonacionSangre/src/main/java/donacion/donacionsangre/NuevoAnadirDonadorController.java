/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package donacion.donacionsangre;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author mbravop
 */
public class NuevoAnadirDonadorController {

    ArrayList<String> errores = new ArrayList<>();
    @FXML
    private Label lblNombreEnfermero;
    @FXML
    private Label lblIDEnfermero;
    @FXML
    private TextField txtCedulaDonador;
    @FXML
    private TextField txtNombreDonador;
    @FXML
    private TextField txtApellidoDonador;
    @FXML
    private ComboBox<String> cmbTipoSangre;
    @FXML
    private ComboBox<String> cmbTipificacionSangre;
    @FXML
    private ComboBox<String> cmbSexo;
    /**
     * Initializes the controller class.
     */

    public void initialize() {
        lblNombreEnfermero.setText(LoginGeneralController.nombreUsuarioLog+ " "+LoginGeneralController.apellidoUsuarioLog);
        lblIDEnfermero.setText("ID #"+LoginGeneralController.idUsuarioLog);
        cmbTipoSangre.getItems().addAll("A", "B", "AB", "O");
        cmbTipificacionSangre.getItems().addAll("+", "-");
        cmbSexo.getItems().addAll("M", "F");
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
    private void switchToDonaciones() throws IOException{
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
    private void confirmarAgregar() throws IOException{
        boolean a = txtCedulaDonador.getText().isEmpty();
        boolean b = txtNombreDonador.getText().isEmpty();
        boolean c = txtApellidoDonador.getText().isEmpty();
        boolean d = cmbSexo.getValue()==null;
        boolean e = cmbTipoSangre.getValue()==null;
        boolean f = cmbTipificacionSangre.getValue()==null;
        if ((a) || (b) || (c) || (d) || (e) || (f)) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setContentText("Por favor llene todos los campos ");
            alerta.showAndWait();
        } else {
            erroresValidacionesCampos();
            if (errores.size() > 0) {
                String cadenaErrores = "";
                for (String s : errores) {
                    cadenaErrores += (s + "\n");
                }

                App.crearAlerta(cadenaErrores);
            } else {
                String query = "{CALL insertarDonador(?,?,?,?,?,?)}";
                try {
                    CallableStatement statement = App.conexionBaseDatos.prepareCall(query);
                    statement.setString(1, txtCedulaDonador.getText());
                    statement.setString(2, txtNombreDonador.getText());
                    statement.setString(3, txtApellidoDonador.getText());
                    statement.setString(4, cmbSexo.getValue());
                    statement.setString(5, cmbTipoSangre.getValue());
                    statement.setString(6, cmbTipificacionSangre.getValue());
                    statement.executeUpdate();
                    switchToDonadores();                    
                } catch (SQLException x) {
                    x.printStackTrace();
                }
            }
        }
    }
    
    private void erroresValidacionesCampos() {
        errores.clear();
        boolean revisionCedula = txtCedulaDonador.getText().chars().allMatch(Character::isDigit);
        boolean revisionNombre = txtNombreDonador.getText().chars().allMatch(Character::isLetter);
        boolean revisionApellido = txtApellidoDonador.getText().chars().allMatch(Character::isLetter);

        if (!revisionCedula || txtCedulaDonador.getText().length() != 10) {
            errores.add("Número de cédula no válido");
        }
        if (!revisionNombre) {
            errores.add("Nombre no válido");
        }
        if (txtNombreDonador.getText().length() >= 50) {
            errores.add("Campo de nombre supera 50 caracteres");
        }
        if (!revisionApellido) {
            errores.add("Apellido no válido");
        }
        if (txtApellidoDonador.getText().length() >= 50) {
            errores.add("Campo de apellido supera 50 caracteres");
        }
    }
}
