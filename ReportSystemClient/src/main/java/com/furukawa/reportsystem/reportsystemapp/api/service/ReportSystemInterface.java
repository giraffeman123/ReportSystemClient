package com.furukawa.reportsystem.reportsystemapp.api.service;

import com.furukawa.reportsystem.reportsystemapp.api.model.Lider;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReportSystemInterface {

    @GET("/servicio/webresources/app/allLideres")
    Call<List<Lider>> getAllLideres();

    @GET("/servicio/webresources/app/liderByCodigo/{codigo}")
    public String getLiderByCodigoEmpleado(@Path("codigo") String codigo);

    @GET("/servicio/webresources/app/allLideresByNombre/{nombre}")
    Call<List<Lider>> getAllLideresByNombre(@Path("nombre") String nombre);

}
