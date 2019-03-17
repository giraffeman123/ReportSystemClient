package com.furukawa.reportsystem.reportsystemapp.api.model;

import java.util.Date;

public class Defecto {

    private String codigoDefecto;

    private int linea;

    private int estacion;

    private int cantidad;

    private Date fecha;

    private String comentario;

    private Empleado asociado;

    private CodigoDefecto codigoDefecto1;

    private Lider lider;

    public Defecto() {
    }

    public Defecto(String codigoDefecto) {
        this.codigoDefecto = codigoDefecto;
    }

    public Defecto(String codigoDefecto, int linea, int estacion, int cantidad, Date fecha) {
        this.codigoDefecto = codigoDefecto;
        this.linea = linea;
        this.estacion = estacion;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getCodigoDefecto() {
        return codigoDefecto;
    }

    public void setCodigoDefecto(String codigoDefecto) {
        this.codigoDefecto = codigoDefecto;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getEstacion() {
        return estacion;
    }

    public void setEstacion(int estacion) {
        this.estacion = estacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Empleado getAsociado() {
        return asociado;
    }

    public void setAsociado(Empleado asociado) {
        this.asociado = asociado;
    }

    public CodigoDefecto getCodigoDefecto1() {
        return codigoDefecto1;
    }

    public void setCodigoDefecto1(CodigoDefecto codigoDefecto1) {
        this.codigoDefecto1 = codigoDefecto1;
    }

    public Lider getLider() {
        return lider;
    }

    public void setLider(Lider lider) {
        this.lider = lider;
    }
}
