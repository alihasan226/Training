package com.example.retrofitexample;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tv_data;
    protected ActionBar actionBar;
    protected Button button;
    protected Jsonplaceholder jsonplaceholder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        actionBar=getSupportActionBar();
        actionBar.setTitle("RetroFit Get Method");
        tv_data=findViewById(R.id.textview);
        button=findViewById(R.id.btn_movie);


        //Getting the movie From  https://simplifiedcoding.net/demos/
        //Here we declare the Retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://simplifiedcoding.net/demos/")//Here we set the root URL/Base URL
                .addConverterFactory(GsonConverterFactory.create())//and this is the converter GSON
                .build();

        //jsonholder return the APi interface class object to the MainActivity
        jsonplaceholder=retrofit.create((Jsonplaceholder.class));
        //getpost();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getmovie();
            }
        });

    }



    public void getmovie()
    {

        Call<List<pojo>> call=jsonplaceholder.getmovie("Iron Man","Tony Stark");

        call.enqueue(new Callback<List<pojo>>() {
            @Override
            public void onResponse(Call<List<pojo>> call, Response<List<pojo>> response) {
                if(!response.isSuccessful())
                {
                    tv_data.setText("Code"+response.code());
                    return;
                }

                List<pojo> marvel=response.body();
                for(pojo pojo:marvel)
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
                    tv_data.append(content);
                    int number=response.code();
                    String code=String.valueOf(number);
                    Toast.makeText(getApplicationContext(),code,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<pojo>> call, Throwable t) {
                tv_data.setText(t.getMessage());
            }
        });
    }

    public void getpost()
    {
        Call<List<Post>> call=jsonplaceholder.getPost();
        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    tv_data.setText("Code"+response.code());
                    return;
                }



                List<Post> post1=response.body();
                for(Post post:post1)
                {
                    String content="";
                    content+="ID : "+post.getId()+"\n";
                    content+="User ID : "+post.getUserId()+"\n";
                    content+="Title : "+post.getTitle()+"\n";
                    content+="Text : "+post.getText();


                    tv_data.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tv_data.setText(t.getMessage());
            }
        });
    }

}
