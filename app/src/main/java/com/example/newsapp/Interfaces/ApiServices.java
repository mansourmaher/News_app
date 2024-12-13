package com.example.newsapp.Interfaces;

import com.example.newsapp.Models.NewResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {


    @GET("everything/cnn.json")
    Call<NewResponse> getData();
}
