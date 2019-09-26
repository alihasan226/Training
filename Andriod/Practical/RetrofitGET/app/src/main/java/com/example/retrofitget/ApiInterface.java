package com.example.retrofitget;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("marvel")
    Call<List<Pojo>> getName();
}
