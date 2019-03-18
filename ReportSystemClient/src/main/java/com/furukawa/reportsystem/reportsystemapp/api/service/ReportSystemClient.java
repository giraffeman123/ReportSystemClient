package com.furukawa.reportsystem.reportsystemapp.api.service;

import com.furukawa.reportsystem.reportsystemapp.api.model.Lider;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ReportSystemClient {

    @GET("/api/lider/consulta_general")
    Call<List<Lider>> getAllLideres();
}
