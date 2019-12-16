package com.example.toastexample.Interface;

import com.example.toastexample.Network.Movie.MovieResponse;
import com.example.toastexample.Network.UserLogin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherResponse {

    @GET("weather")
    Call<UserLogin> getweather(@Query("q") String location, @Query("appid") String APIKEY);

    @GET("3/movie/550")
    Call<MovieResponse> getMovie(@Query("api_key") String API_KEY);
}
