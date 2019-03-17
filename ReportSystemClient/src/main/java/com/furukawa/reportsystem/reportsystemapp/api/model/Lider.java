package com.furukawa.reportsystem.reportsystemapp.api.model;

public class Lider {

    private String codigoEmpleado;
    private String area;
    private int linea;

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

    @Override
    public String toString() {
        return "Lider{" +
                "codigoEmpleado='" + codigoEmpleado + '\'' +
                ", area='" + area + '\'' +
                ", linea=" + linea +
                '}';
    }
}
