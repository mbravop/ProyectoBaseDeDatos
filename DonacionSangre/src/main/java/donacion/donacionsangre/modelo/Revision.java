/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package donacion.donacionsangre.modelo;

import java.sql.Date;

/**
 *
 * @author Dereck Santander
 */
public class Revision {
    int id,idDonacion;
    String nombreDonador, apellidoDonador, nombreEnfermero, apellidoEnfermero;
    Date fechaRevision;

    public Revision(int id, int idDonacion, String nombreDonador,String apellidoDonador,String nombreEnfermero, String apellidoEnfermero, Date fechaRevision) {
        this.id = id;
        this.idDonacion = idDonacion;
        this.nombreDonador = nombreDonador;
        this.apellidoDonador = apellidoDonador;
        this.nombreEnfermero = nombreEnfermero;
        this.apellidoEnfermero = apellidoEnfermero;
        this.fechaRevision = fechaRevision;
    }

    public int getId() {
        return id;
    }

    public int getIdDonacion() {
        return idDonacion;
    }

    public String getNombreDonador() {
        return nombreDonador;
    }

    public String getApellidoDonador() {
        return apellidoDonador;
    }

    public String getNombreEnfermero() {
        return nombreEnfermero;
    }

    public String getApellidoEnfermero() {
        return apellidoEnfermero;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    
    
    
    
}
