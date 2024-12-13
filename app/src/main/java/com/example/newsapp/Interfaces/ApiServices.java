package com.example.newsapp.Interfaces;

import com.example.newsapp.Models.NewResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServices {


    @GET("everything/cnn.json")
    Call<NewResponse> getData();

    @GET("top-headlines/category/{category}/in.json")
    Call<NewResponse> getDataByCategories(@Path("category") String category);

}
