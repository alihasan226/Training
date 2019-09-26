package com.example.retrofitexample;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tv_data;
    protected ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        actionBar=getSupportActionBar();
        actionBar.setTitle("RetroFit Get Method");
        tv_data=findViewById(R.id.textview);



        //Here we declare the Retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")//Here we set the root URL
                .addConverterFactory(GsonConverterFactory.create())//and this is the converter GSON
                .build();

        //jsonholder return the APi interface class object to the MainActivity
        Jsonplaceholder jsonplaceholder=retrofit.create((Jsonplaceholder.class));


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
