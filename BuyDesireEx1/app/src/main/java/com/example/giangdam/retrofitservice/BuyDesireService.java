package com.example.giangdam.retrofitservice;

import com.example.giangdam.retrofitmodel.ProductBound;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Giang.Dam on 8/18/2015.
 */
public interface BuyDesireService {

    @GET("/api/Tablet/Desires/GetDesires")
    void getDesires(@Query("request") String request, retrofit.Callback<ProductBound> respone);

}
