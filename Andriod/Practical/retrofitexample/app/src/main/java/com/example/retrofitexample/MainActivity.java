package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    pojo pojodata;
    private Button btn_submit;
    private EditText et_name,et_email,et_password;

    final String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name=findViewById(R.id.et_name);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_pass);
        btn_submit=findViewById(R.id.btn_submitt);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    public void signUp()
    {



        Api.getClient().registration(et_name.getText().toString().trim(),
                et_email.getText().toString().trim(),
                et_password.getText().toString().trim(),
                "email", new Callback<pojo>() {
                    @Override
                    public void success(pojo signUpResponse, Response response) {
                        // in this method we will get the response from API
                        pojodata = signUpResponse;
                        // display the message getting from web api
                        Toast.makeText(MainActivity.this, signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // if error occurs in network transaction then we can get the error in this method.
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                });

    }
}
