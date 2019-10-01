package com.example.accessingcamera;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button button1;
    private ImageView imageview;
    public String currentPhotoPath;
    public Bitmap bmp;
    public static final int PICK_IMAGE = 2;
    public  Uri photoURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.buttonl);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }

        });
    }


    //creating unique name for the photo

    private File createImageFile() throws IOException {
        //creating image file
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timestamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    public void openDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();


        TextView message = new TextView(this);
        message.setText("Take Picture");
        message.setPadding(0, 25, 0, 0);
        message.setGravity(Gravity.CENTER_HORIZONTAL);
        message.setTextSize(28);
        message.setTextColor(Color.BLACK);

        alertDialog.setView(message);


        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                openGallery();
            }
        });


        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                openCamera();

            }
        });

        alertDialog.show();

        final Button camera = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        camera.setPadding(150, 0, 0, 0);
        camera.setTextColor(Color.BLUE);
        camera.setTextSize(18);


        final Button gallery = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        gallery.setPadding(0, 0, 150, 0);
        gallery.setTextColor(Color.BLUE);
        gallery.setTextSize(18);
    }

    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            //creating file where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(MainActivity.this, "com.example.accessingcamera.fileprovider", photoFile);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_IMAGE_CAPTURE) {

                this.getContentResolver().notifyChange(photoURI,null);//Notify registered observers that a row was updated and attempt to sync changes to the network
                ContentResolver cr=this.getContentResolver();
                try {
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(cr,photoURI);
                    ImageView imageView = (ImageView) findViewById(R.id.imageview1);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else if (requestCode == PICK_IMAGE) {
                Uri uri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    ImageView imageView = (ImageView) findViewById(R.id.imageview1);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}

/*
URI => A Uniform Resource Identifier (URI) is a string of characters that unambiguously identifies a particular resource.
 */









