package com.furukawa.reportsystem.reportsystemapp.api.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReportSystemClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseURL){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
