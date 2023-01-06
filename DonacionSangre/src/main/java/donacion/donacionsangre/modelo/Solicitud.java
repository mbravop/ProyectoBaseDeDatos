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
public class Solicitud {
    int idSolicitud,cantidadSolicitada,cantidadRecibida;
    String nombreBeneficiario, apellidoBeneficiario;
    Date fechaSolicitud;
    Boolean realizada;

    public Solicitud(int idSolicitud, int cantidadSolicitada, int cantidadRecibida, Date fechaSolicitud,String nombreBeneficiario, String apellidoBeneficiario, Boolean realizada) {
        this.idSolicitud = idSolicitud;
        this.cantidadSolicitada = cantidadSolicitada;
        this.cantidadRecibida = cantidadRecibida;
        this.nombreBeneficiario = nombreBeneficiario;
        this.apellidoBeneficiario = apellidoBeneficiario;
        this.fechaSolicitud = fechaSolicitud;
        this.realizada = realizada;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public String getApellidoBeneficiario() {
        return apellidoBeneficiario;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public int getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(int cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Boolean getRealizada() {
        return realizada;
    }

    public void setRealizado(Boolean realizada) {
        this.realizada = realizada;
    }
    
    
}
