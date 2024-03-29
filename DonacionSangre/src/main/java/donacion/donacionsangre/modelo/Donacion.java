/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package donacion.donacionsangre.modelo;

import java.sql.Date;

/**
 *
 * @author mbravop
 */
public class Donacion {
    int id, idEnfermero, idDestino;
    String cedulaDonador;
    String nombreDonador,apellidoDonador;
    String aceptacion;
    String tipoDeSangre, tipificacionSangre;
    Date fechaDonacion;

    public Donacion(int id, String cedulaDonador,String nombreDonador, String apellidoDonador, int idEnfermero, int idDestino, String aceptacion, String tipoDeSangre, String tipificacionSangre, Date fechaDonacion) {
        this.id = id;
        this.cedulaDonador = cedulaDonador;
        this.nombreDonador = nombreDonador;
        this.apellidoDonador = apellidoDonador;
        this.idEnfermero = idEnfermero;
        this.idDestino = idDestino;
        this.aceptacion = aceptacion;
        this.tipoDeSangre = tipoDeSangre;
        this.tipificacionSangre = tipificacionSangre;
        this.fechaDonacion = fechaDonacion;
    }

    public String getNombreDonador() {
        return nombreDonador;
    }

    public String getApellidoDonador() {
        return apellidoDonador;
    }

    public int getId() {
        return id;
    }

    public String getCedulaDonador() {
        return cedulaDonador;
    }

    public int getIdEnfermero() {
        return idEnfermero;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public String getAceptacion() {
        return aceptacion;
    }

    public String getTipoDeSangre() {
        return tipoDeSangre;
    }

    public String getTipificacionSangre() {
        return tipificacionSangre;
    }

    public Date getFechaDonacion() {
        return fechaDonacion;
    }
    
    
}
