package com.furukawa.reportsystem.reportsystemapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Lider {

    @SerializedName("codigoEmpleado")
    @Expose
    private String codigoEmpleado;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("linea")
    @Expose
    private int linea;
    @SerializedName("empleado")
    @Expose
    private Empleado empleado;
    @SerializedName("defectoList")
    @Expose
    private List<Defecto> defectoList;

    public Lider() {
    }

    public Lider(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public Lider(String codigoEmpleado, String area, int linea) {
        this.codigoEmpleado = codigoEmpleado;
        this.area = area;
        this.linea = linea;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Defecto> getDefectoList() {
        return defectoList;
    }

    public void setDefectoList(List<Defecto> defectoList) {
        this.defectoList = defectoList;
    }

    @Override
    public String toString() {
        return "Lider{" +
                "codigoEmpleado='" + codigoEmpleado + '\'' +
                ", area='" + area + '\'' +
                ", linea=" + linea +
                '}';
    }
}
