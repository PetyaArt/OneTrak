package com.example.company.onetrak.network;


import com.google.gson.JsonArray;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiServer {

    @GET("intern/metric.json")
    Observable<JsonArray> getData();


}
