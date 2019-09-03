package com.example.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.result);
        TextView result=(TextView)findViewById(R.id.result_view);
        Intent intent=getIntent();
        String addition=(String)intent.getSerializableExtra("SUM");
        result.setText(addition);
    }
}
