package com.example.accessingcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE=1;
    protected Button button1;
    protected ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.buttonl);
        imageview=(ImageView)findViewById(R.id.imageview1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!=null)
                {
                    startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestcode,int resultCode,Intent data)
    {
        if(requestcode==REQUEST_IMAGE_CAPTURE)
        {
            Bundle extras=data.getExtras();
            Bitmap image=(Bitmap)extras.get("data");
            imageview.setImageBitmap(image);
        }
    }
}









