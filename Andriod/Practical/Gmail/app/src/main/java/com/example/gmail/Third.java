package com.example.gmail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Third extends AppCompatActivity {

    private ActionBar actionBar;
    private EditText et_mail,et_password;
    private Button btn_register;
    public FirebaseAuth mAuth;
    public final String TAG="REGISTER ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);

        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        et_mail=findViewById(R.id.et_mail);
        et_password=findViewById(R.id.et_pass);
        btn_register=findViewById(R.id.btn_submit);

        mAuth=FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createAccount();
            }
        });

        /*

        tv_email=findViewById(R.id.second_textview);
        Intent intent=getIntent();
        String new_user=intent.getSerializableExtra("userId").toString();
        tv_email.setText(new_user);
        */


    }
    public void createAccount()
    {
        if(TextUtils.isEmpty(et_mail.getText()))
        {
            et_mail.setError("Required Field");
            et_mail.requestFocus();
        }
        else if(TextUtils.isEmpty(et_password.getText()))
        {
            et_password.setError("Required Field");
            et_password.requestFocus();
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(et_mail.getText().toString(),et_password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                Toast.makeText(Third.this, "Successfully Account Created", Toast.LENGTH_SHORT).show();
                                //FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(Third.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
