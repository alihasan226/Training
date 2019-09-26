package com.example.retrofitexample;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static Jsonplaceholder getClient()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")//Here we set the root URL
                .addConverterFactory(GsonConverterFactory.create())//and this is the converter GSON
                .build();


        Jsonplaceholder jsonplaceholder=retrofit.create(Jsonplaceholder.class);
        return jsonplaceholder;
    }
}
