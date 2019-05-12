package com.furukawa.reportsystem.reportsystemapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CodigoDefecto {

    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("maquina")
    @Expose
    private String maquina;
    @SerializedName("gravedad")
    @Expose
    private String gravedad;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("codigoDefecto")
    @Expose
    private String codigoDefecto;
<<<<<<< HEAD
=======

    private String maquina;

>>>>>>> aef899631c60eeffb0173cbb4bb13e4a2848545a
    private byte[] fotografia;

    public CodigoDefecto() {
    }

    public CodigoDefecto(String codigoDefecto) {
        this.codigoDefecto = codigoDefecto;
    }

<<<<<<< HEAD
    public CodigoDefecto(String codigoDefecto, String area, String maquina, String gravedad, String descripcion) {
=======
    public CodigoDefecto(String area, String gravedad, String descripcion, String codigoDefecto,  String maquina) {
>>>>>>> aef899631c60eeffb0173cbb4bb13e4a2848545a
        this.codigoDefecto = codigoDefecto;
        this.area = area;
        this.maquina = maquina;
        this.gravedad = gravedad;
        this.descripcion = descripcion;
        this.maquina = maquina;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
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

<<<<<<< HEAD
=======
    public Defecto getDefecto() {
        return defecto;
    }

    public void setDefecto(Defecto defecto) {
        this.defecto = defecto;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }
>>>>>>> aef899631c60eeffb0173cbb4bb13e4a2848545a
}
