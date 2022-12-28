/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package donacion.donacionsangre.modelo;

/**
 *
 * @author mbravop
 */
public class Donador {
    int id;
    String cedula, nombre, apellido, sexo, tipoDeSangre, tipificacionDeSangre;

    public Donador(int id, String cedula, String nombre, String apellido, String sexo, String tipoDeSangre, String tipificacionDeSangre) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.tipoDeSangre = tipoDeSangre;
        this.tipificacionDeSangre = tipificacionDeSangre;
    }

    public int getId() {
        return id;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public String getTipoDeSangre() {
        return tipoDeSangre;
    }

    public String getTipificacionDeSangre() {
        return tipificacionDeSangre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setTipoDeSangre(String tipoDeSangre) {
        this.tipoDeSangre = tipoDeSangre;
    }

    public void setTipificacionDeSangre(String tipificacionDeSangre) {
        this.tipificacionDeSangre = tipificacionDeSangre;
    }
    
    
    
}
