package com.example.retrofitget;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tv_marvel;
    private ImageView imageView;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_marvel=findViewById(R.id.textview);


        //taking API from Simplified Coading
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://simplifiedcoding.net/demos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        apiInterface=retrofit.create(ApiInterface.class);
        fetchData();
    }

    public void fetchData()
    {
        Call<List<Pojo>> call=apiInterface.getName();

        call.enqueue(new Callback<List<Pojo>>() {
            @Override
            public void onResponse(Call<List<Pojo>> call, Response<List<Pojo>> response) {

                if(!response.isSuccessful())
                {
                    tv_marvel.setText("Code"+response.code());
                    return;
                }

                List<Pojo> marvel=response.body();
                for(Pojo pojo:marvel)
                {
                    String content="";
                    content+="Name : "+pojo.getName()+"\n";
                    content+="Real Name : "+pojo.getRealname()+"\n";
                    content+="Team : "+pojo.getTeam()+"\n";
                    content+="First Appearance : "+pojo.getFirstappearance()+"\n";
                    content+="Created By : "+pojo.getCreatedby()+"\n";
                    content+="Publisher : "+pojo.getPublisher()+"\n";
                    content+="Image Url : "+pojo.getImageurl()+"\n";
                    content+="Bio : "+pojo.getBio()+"\n\n";


                    tv_marvel.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Pojo>> call, Throwable t) {
                tv_marvel.setText(t.getMessage());
            }
        });
    }
}
