package com.furukawa.reportsystem.reportsystemapp.api.model;

public class CodigoDefecto {

    private String area;

    private String gravedad;

    private String descripcion;

    private String codigoDefecto;

    private byte[] fotografia;

    private Defecto defecto;

    public CodigoDefecto() {
    }

    public CodigoDefecto(String codigoDefecto) {
        this.codigoDefecto = codigoDefecto;
    }

    public CodigoDefecto(String codigoDefecto, String area, String gravedad, String descripcion) {
        this.codigoDefecto = codigoDefecto;
        this.area = area;
        this.gravedad = gravedad;
        this.descripcion = descripcion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGravedad() {
        return gravedad;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoDefecto() {
        return codigoDefecto;
    }

    public void setCodigoDefecto(String codigoDefecto) {
        this.codigoDefecto = codigoDefecto;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public Defecto getDefecto() {
        return defecto;
    }

    public void setDefecto(Defecto defecto) {
        this.defecto = defecto;
    }
}
