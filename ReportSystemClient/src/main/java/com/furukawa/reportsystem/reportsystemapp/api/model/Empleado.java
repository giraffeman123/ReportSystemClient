package com.furukawa.reportsystem.reportsystemapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Empleado {

    @SerializedName("codigoEmpleado")
    @Expose
    private String codigoEmpleado;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("puesto")
    @Expose
    private String puesto;
    @SerializedName("turno")
    @Expose
    private String turno;
    @SerializedName("foto")
    @Expose
    private byte[] foto;
    private Lider lider;
    private List<Defecto> defectoList;

    public Empleado() {
    }

    public Empleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public Empleado(String codigoEmpleado, String nombre, String puesto, String turno) {
        this.codigoEmpleado = codigoEmpleado;
        this.nombre = nombre;
        this.puesto = puesto;
        this.turno = turno;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Lider getLider() {
        return lider;
    }

    public void setLider(Lider lider) {
        this.lider = lider;
    }

    public List<Defecto> getDefectoList() {
        return defectoList;
    }

    public void setDefectoList(List<Defecto> defectoList) {
        this.defectoList = defectoList;
    }
}
