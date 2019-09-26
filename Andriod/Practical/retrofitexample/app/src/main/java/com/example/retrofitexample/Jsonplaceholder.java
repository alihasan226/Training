package com.example.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



//we create an interface in which we have getPost() method to get data from web API.
public interface Jsonplaceholder {

    @GET("posts")//Here we declare the relative URL
    Call<List<Post>> getPost();//we only declare the method here

    //Post is POJO class to get the data from API
    //In Post method we use List<Post> because the data in our API is starting from JSONArray and callback is used to get the response from API and it will set it in our POJO class
}
