package com.furukawa.reportsystem.reportsystemapp.api.service;

import com.furukawa.reportsystem.reportsystemapp.api.model.Empleado;
import com.furukawa.reportsystem.reportsystemapp.api.model.Lider;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReportSystemInterface {

    @GET("servicio/api/lider/allLideres")
    Call<List<Lider>> getAllLideres();

    @GET("servicio/api/lider/liderByCodigo/{codigo}")
    Call<Lider> getLiderByCodigoEmpleado(@Path("codigo") String codigo);

    @GET("servicio/api/lider/allLideresByNombre/{nombre}")
    Call<List<Lider>> getAllLideresByNombre(@Path("nombre") String nombre);

    @FormUrlEncoded
    @POST("servicio/api/lider/modificarLider")
    Call<String> updateLider(@Field("codigoEmpleado") String codigoEmpleado,
                             @Field("area") String area,
                             @Field("linea") String linea,
                             @Field("nombre") String nombre,
                             @Field("puesto") String puesto,
                             @Field("turno") String turno);

    @FormUrlEncoded
    @POST("servicio/api/lider/deleteLiderByCodigo")
    Call<String> deleteLiderByCodigoEmpleado(@Field("codigoEmpleado") String codigoEmpleado);

    @FormUrlEncoded
    @POST("servicio/api/lider/nuevoLider")
    Call<String> saveLider(@Field("codigoEmpleado") String codigoEmpleado,
                             @Field("area") String area,
                             @Field("linea") String linea,
                             @Field("nombre") String nombre,
                             @Field("puesto") String puesto,
                             @Field("turno") String turno);
}
