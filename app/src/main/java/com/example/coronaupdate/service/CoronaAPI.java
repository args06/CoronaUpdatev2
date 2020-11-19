package com.example.coronaupdate.service;

import com.example.coronaupdate.model.global.CoronaResponse;
import com.example.coronaupdate.model.indo.AccResponse;
import com.example.coronaupdate.model.indo.ProvinceResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoronaAPI {
    String URL_BASE = "https://api.covid19api.com/";
    String URL_BASE_INDO = "https://indonesia-covid-19.mathdro.id/";

    @GET("summary")
    Call<CoronaResponse> getCoronaGlobal();

    @GET("api")
    Call<AccResponse> getCoronaIndo();

    @GET("api/provinsi")
    Call<ProvinceResponse> getCoronaProvince();

}
