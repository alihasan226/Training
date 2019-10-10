package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView et_commet;
    private Button btn_submit;
    private EditText et_userid,et_title,et_text;
    private Jsonplaceholder jsonplaceholder;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_commet=findViewById(R.id.textview);

        et_userid=findViewById(R.id.et_userId);
        et_title=findViewById(R.id.et_title);
        et_text=findViewById(R.id.et_text);

        btn_submit=findViewById(R.id.btn_submit);

        progressBar=findViewById(R.id.progress_bar);


        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel((HttpLoggingInterceptor.Level.HEADERS));

        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        //Gson gson=new GsonBuilder().serializeNulls().create();   in term of putting the null value if we don't pass anything using patch method than we can use it.
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")//that is my base URL
                .addConverterFactory(GsonConverterFactory.create())//but we will have to pass the gson object in the parenthsis of this method.
                .client(okHttpClient)
                .build();

        jsonplaceholder=retrofit.create(Jsonplaceholder.class);
        //getpost();
        //fetchComment();
        //createPost();
        //putPost();
        //patchpost();
        //deletePost();


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patchpost(et_userid.getText().toString(),et_title.getText().toString(),et_text.getText().toString());
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    public void getpost()
    {
        Map<String,String> parameter=new HashMap<>();
        parameter.put("userId","1");
        parameter.put("_sort","id");
        parameter.put("_order","desc");

        Call<List<Post>> call=jsonplaceholder.getPost(parameter);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    et_commet.setText("Code"+response.code());
                    return;
                }

                List<Post> posts=response.body();

                for(Post post:posts)
                {
                    String content="";
                    content+="ID : "+post.getId()+"\n";
                    content+="User ID : "+post.getUserId()+"\n";
                    content+="Title : "+post.getTitle()+"\n";
                    content+="Text : "+post.getText()+"\n";
                    et_commet.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                et_commet.setText(t.getMessage());
            }

        });
    }


    public void fetchComment()
    {
        //using this we can get the comment of respective post id
        Call<List<PostComment>> call=jsonplaceholder.getComment(3);

        call.enqueue(new Callback<List<PostComment>>() {
            @Override
            public void onResponse(Call<List<PostComment>> call, Response<List<PostComment>> response) {
                if(!response.isSuccessful()){
                    et_commet.setText("Code"+response.code());
                    return;
                }

                List<PostComment> posts=response.body();

                for(PostComment post:posts)
                {
                    String content="";
                    content+="ID : "+post.getId()+"\n";
                    content+="Post ID : "+post.getPostId()+"\n";
                    content+="Name : "+post.getName()+"\n";
                    content+="Email : "+post.getEmail()+"\n";
                    content+="Body : "+post.getBody()+"\n\n";

                    et_commet.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<PostComment>> call, Throwable t) {
                et_commet.setText(t.getMessage());

            }
        });
    }



    public void createPost()
    {
        Post post=new Post(23,"New Title","New Text");//here we are sending the data to the server using body and


        Call<Post> call=jsonplaceholder.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful())
                {
                    et_commet.setText("Code : "+response.code());//HTTP response status codes indicates whether a specific HTTP request has been successfully completed.
                    return; //if we get the 400-499 than it means the request has been failed nad there response code will be concatinated in the textview(that we have already been declared)
                }

                Toast.makeText(getBaseContext(),"Succesfully ========== Login",Toast.LENGTH_LONG).show();

                /*Post postresponse=response.body();
                //here we reteriving the post request

                    String content="";
                    content+="Code : "+response.code()+"\n";//this is response code that is generated after the HTTP request action performed.
                    content+="ID : "+postresponse.getId()+"\n";
                    content+="User ID : "+postresponse.getUserId()+"\n";
                    content+="Title : "+postresponse.getTitle()+"\n";
                    content+="Text: "+postresponse.getText()+"\n\n";
                    et_commet.append(content);*/
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t){
                et_commet.setText(t.getMessage());//this message will print after getting failure
                Toast.makeText(getApplicationContext(),"Authentication Failure",Toast.LENGTH_LONG).show();
            }
        });
    }

    /*
    private void putPost()
    {
        Post new_post=new Post(12,null,"New Text");
        Call<Post> call=jsonplaceholder.patchPost(1,new_post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful()){
                    et_commet.setText("Code"+response.code());
                    return;
                }

                Post postresponse=response.body();
                //here we reteriving the post request

                String content="";
                content+="Code : "+response.code()+"\n";//this is response code that is generated after the HTTP request action performed.
                content+="ID : "+postresponse.getId()+"\n";
                content+="User ID : "+postresponse.getUserId()+"\n";
                content+="Title : "+postresponse.getTitle()+"\n";
                content+="Text: "+postresponse.getText()+"\n\n";
                et_commet.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                et_commet.setText(t.getMessage());
            }
        });
    }
*/
    private void deletePost()
    {
        Call<Void> call=jsonplaceholder.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                et_commet.setText("Code : "+response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                et_commet.setText(t.getMessage());
            }
        });
    }

    private void patchpost(String userId,String title,String text)
    {

        Post new_post=new Post(Integer.parseInt(userId),title,text);
        Call<Post> call=jsonplaceholder.patchPost(1,new_post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful()){
                    et_commet.setText("Code"+response.code());
                    return;
                }

                Post postresponse=response.body();
                //here we reteriving the post request

                if(response.code()==200)
                {
                    Toast.makeText(getApplicationContext(),"User successfully Added",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
                String content="";
                //content+="Code : "+response.code()+"\n";//this is response code that is generated after the HTTP request action performed.
                //content+="ID : "+postresponse.getId()+"\n";
                content+="User ID : "+postresponse.getUserId()+"\n";
                content+="Title : "+postresponse.getTitle()+"\n";
                content+="Text: "+postresponse.getText()+"\n\n";
                et_commet.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                et_commet.setText(t.getMessage());
            }
        });
    }


}
