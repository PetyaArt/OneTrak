package com.example.company.onetrak.network;


import com.example.company.onetrak.data.Fit;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiServer {

    @GET("intern/metric.json")
    Observable<JsonArray> getData();


}
