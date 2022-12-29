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
    int id, idEnfermero, idDonacion;
    Date fechaRevision;

    public Revision(int id, int idEnfermero, int idDonacion, Date fechaRevision) {
        this.id = id;
        this.idEnfermero = idEnfermero;
        this.idDonacion = idDonacion;
        this.fechaRevision = fechaRevision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEnfermero() {
        return idEnfermero;
    }

    public void setIdEnfermero(int idEnfermero) {
        this.idEnfermero = idEnfermero;
    }

    public int getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(int idDonacion) {
        this.idDonacion = idDonacion;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }
    
    
    
}
