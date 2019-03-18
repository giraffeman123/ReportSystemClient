package com.furukawa.reportsystem.reportsystemapp.api.service;

public class ApiUtils {

    private ApiUtils(){}

    public static final String BASE_URL = "http://192.168.1.70:8080/";

    public static ReportSystemInterface getReportSystemService(){
        return ReportSystemClient.getClient(BASE_URL).create(ReportSystemInterface.class);
    }
}
