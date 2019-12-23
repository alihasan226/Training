package com.example.usl.Network;

import com.example.usl.Network.Response.UserResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("/login.json")
    Call<UserResponse> login(Map login);
}
