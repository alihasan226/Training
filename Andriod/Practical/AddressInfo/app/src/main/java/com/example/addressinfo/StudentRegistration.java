package com.example.addressinfo;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class StudentRegistration extends AppCompatActivity
{
    private ActionBar actionBar;
    private TextView textView;
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.result);
        actionBar=getSupportActionBar();
        actionBar.setTitle("After Form Filling");
        Intent intent=getIntent();
        String first_name=(String)intent.getSerializableExtra("firstname");
        textView=(TextView)findViewById(R.id.textview2);
        textView.setText(first_name);
    }
}
