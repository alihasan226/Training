package com.example.progressdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    protected Button b1;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button);
    }
    public void download(View view)
    {
        progress=new ProgressDialog(this);
        progress.setMessage("Downloading");
        progress.setIndeterminate(true);
        progress.setMax(80);//this is used to set the maximum value of progress dialog
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setProgress(0);
        progress.show();

        final int totalProgressTime=100;
        final Thread t=new Thread()
        {
            @Override
            public void run()
            {
                int jumpTime=0;

                while(jumpTime<totalProgressTime)
                {
                    try
                    {
                        sleep(200);
                        jumpTime+=5;
                        progress.setProgress(jumpTime);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
