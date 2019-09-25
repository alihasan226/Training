package com.example.retrofitexample;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("/retrofit/register.php")//API end point

    public void registration(@Field("name") String name,
                             @Field("email") String email,
                             @Field("password")String password,
                             @Field("logintype") String logintype,
                             Callback<pojo> callback);
}
