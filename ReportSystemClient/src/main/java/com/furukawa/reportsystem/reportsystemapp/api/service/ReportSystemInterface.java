package com.furukawa.reportsystem.reportsystemapp.api.service;

import com.furukawa.reportsystem.reportsystemapp.api.model.CodigoDefecto;
import com.furukawa.reportsystem.reportsystemapp.api.model.Empleado;
import com.furukawa.reportsystem.reportsystemapp.api.model.Lider;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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


    @FormUrlEncoded
    @POST("servicio/api/codigoDefecto/nuevoCodigoDefecto")
    Call<String> saveCodigoDefecto(@Field("area") String area,
                           @Field("maquina") String maquina,
                           @Field("gravedad") String gravedad,
                           @Field("descripcion") String descripcion);

    @GET("servicio/api/codigoDefecto/codigoDefectoByCodigoDefecto/{codigo}")
    Call<CodigoDefecto> getCodigoDefectoByCodigo(@Path("codigoDefecto") String codigo);

    @FormUrlEncoded
    @POST("servicio/api/codigoDefecto/modificarCodigoDefecto")
    Call<String> uptadeCodigoDefecto(@Field("codigoDefecto") String codigoDefecto,
                                     @Field("gravedad") String gravedad,
                                     @Field("descripcion") String descripcion);

    @FormUrlEncoded
    @POST("servicio/api/codigoDefecto/eliminarCodigoDefecto")
    Call<String> deleteCodigoDefectoByCodigo(@Field("codigoDefecto") String codigoDefecto);

    @FormUrlEncoded
    @POST("servicio/api/codigoDefecto/allCodigoDefectoByAreaAndMaquina")
    Call<List<CodigoDefecto>> allCodigoDefectoByAreaAndMaquina(@Field("area") String area,
                             @Field("maquina") String maquina);

    @FormUrlEncoded
    @POST("servicio/api/codigoDefecto/allCodigoDefectoByGravedad")
    Call<List<CodigoDefecto>> allCodigoDefectoByGravedad(@Field("gravedad") String gravedad);
}
