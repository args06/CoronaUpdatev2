package com.example.coronaupdate.service;

import com.example.coronaupdate.CoronaListener;
import com.example.coronaupdate.model.global.CoronaResponse;
import com.example.coronaupdate.model.global.CountriesItem;
import com.example.coronaupdate.model.global.Global;
import com.example.coronaupdate.model.indo.AccResponse;
import com.example.coronaupdate.model.indo.DataItem;
import com.example.coronaupdate.model.indo.ProvinceResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoronaService {
    private Retrofit retrofit = null;

    public CoronaAPI getGlobalAPI(){
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(CoronaAPI.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(CoronaAPI.class);
    }

    public CoronaAPI getIndoAPI(){
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(CoronaAPI.URL_BASE_INDO)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(CoronaAPI.class);
    }

    public void getGlobal(final CoronaListener<Global> listener){
        getGlobalAPI().getCoronaGlobal().enqueue(new Callback<CoronaResponse>() {
            @Override
            public void onResponse(Call<CoronaResponse> call, Response<CoronaResponse> response) {
                CoronaResponse data = response.body();

                if (data != null && data.getGlobal() != null ){
                    listener.onSuccess(data.getGlobal()); //kayak return
                }
            }

            @Override
            public void onFailure(Call<CoronaResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }

    public void getCountries(final CoronaListener<List<CountriesItem>> listener){
        getGlobalAPI().getCoronaGlobal().enqueue(new Callback<CoronaResponse>() {
            @Override
            public void onResponse(Call<CoronaResponse> call, Response<CoronaResponse> response) {
                CoronaResponse data = response.body();

                if (data != null && data.getGlobal() != null ){
                    listener.onSuccess(data.getCountries()); //kayak return
                }
            }

            @Override
            public void onFailure(Call<CoronaResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }

    public void getIndo(final CoronaListener<AccResponse> listener){
        getIndoAPI().getCoronaIndo().enqueue(new Callback<AccResponse>() {
            @Override
            public void onResponse(Call<AccResponse> call, Response<AccResponse> response) {
                AccResponse data = response.body();

                if (data != null ){
                    listener.onSuccess(data); //kayak return
                }
            }

            @Override
            public void onFailure(Call<AccResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }

    public void getProvince(final CoronaListener<List<DataItem>> listener){
        getIndoAPI().getCoronaProvince().enqueue(new Callback<ProvinceResponse>() {
            @Override
            public void onResponse(Call<ProvinceResponse> call, Response<ProvinceResponse> response) {
                ProvinceResponse data = response.body();

                if (data != null){
                    listener.onSuccess(data.getData()); //kayak return
                }
            }

            @Override
            public void onFailure(Call<ProvinceResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }
}
