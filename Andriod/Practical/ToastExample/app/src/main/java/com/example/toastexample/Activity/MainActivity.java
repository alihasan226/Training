package com.example.toastexample.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.toastexample.Interface.WeatherResponse;
import com.example.toastexample.Network.Movie.MovieResponse;
import com.example.toastexample.Network.UserLogin;
import com.example.toastexample.R;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.etweather)
    EditText etWeather;
    @BindView(R.id.etresponse)
    EditText etResponse;
    @BindView(R.id.etmovieresponse)
    TextView etMovieResponse;
    @BindView(R.id.relativelayout)
    RelativeLayout relativeLayout;
    //private String BASE_URL="https://api.openweathermap.org/data/2.5/";
    private String BASE_URL="https://api.themoviedb.org/";
    private Retrofit retrofit;
    private WeatherResponse weatherResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_main);
        ButterKnife.bind(this);

        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okhttp=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttp.build())
                .build();
    }


    @OnClick(R.id.btnLogIn)
    public void Login() {

        weatherResponse = retrofit.create(WeatherResponse.class);
        Call<UserLogin> call = weatherResponse.getweather(etWeather.getText().toString(), "746ac30f1d6fa590e1274d7595d9a513");
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                if(!response.isSuccessful()){
                    etResponse.setText(response.code());
                }
                else if(response.code()==200){
                    UserLogin body=response.body();
                    String name="";
                    name+="ID = "+body.getSys().getId()+"\n";
                    name+="Country = "+body.getSys().getCountry()+"\n";
                    name+="State = "+body.getName()+"\n";
                    name+="TimeZone = "+body.getTime();
                    name+="Wind Speed = "+body.getWind().getSpeed()+"\n";
                    name+="Base = "+body.getBase()+"\n";
                    name+="SunRise = "+body.getSys().getSunrise()+"\n";
                    name+="SunSet = "+body.getSys().getSunset()+"\n";
                    name+="Humidity = "+body.getMain().getHumidity()+"\n";
                    name+="Pressure = "+body.getMain().getPressure();
                    etResponse.setText(name);

                    Snackbar snackbar=Snackbar
                            .make(relativeLayout,"Working",Snackbar.LENGTH_SHORT)
                            .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1=Snackbar
                                    .make(relativeLayout,"Yes It's Working",Snackbar.LENGTH_SHORT);
                            snackbar1.setActionTextColor(Color.parseColor("#EB6A38"));
                            snackbar1.show();
                        }
                    });
                    snackbar.setActionTextColor(Color.parseColor("#EB6A38"));
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                etResponse.setText(t.getMessage());
            }
        });

    }

    @OnClick(R.id.btnMovie)
    public void getMovie(){
        weatherResponse=retrofit.create(WeatherResponse.class);
        Call<MovieResponse> call=weatherResponse.getMovie("eddad944d23715a95cdca7e928e90597");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(!response.isSuccessful()){
                    etMovieResponse.setText(response.code());
                }else if(response.code()==200){
                    MovieResponse movieResponse=response.body();
                    String name="";
                    name+="Movie Name = "+movieResponse.getOriginal_title();
                    etMovieResponse.setText(name);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                etMovieResponse.setText(t.getMessage());
            }
        });
    }
}

//MOVIE API FOR TOPRATED MOVIE FROM MOVIEDB.ORG  http://api.themoviedb.org/3/movie/top_rated?api_key=eddad944d23715a95cdca7e928e90597