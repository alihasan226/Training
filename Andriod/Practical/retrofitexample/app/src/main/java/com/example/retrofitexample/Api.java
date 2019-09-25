package com.example.retrofitexample;

import retrofit.RestAdapter;

public class Api {

    public static ApiInterface getClient()
    {
        RestAdapter adapter=new RestAdapter.Builder()
                .setEndpoint("http://mobileappdatabase.in/")
                .build();

        //creating object for our interface
        ApiInterface api=adapter.create(ApiInterface.class);
        return api;
    }
}
