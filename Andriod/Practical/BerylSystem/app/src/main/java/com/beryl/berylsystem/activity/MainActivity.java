package com.beryl.berylsystem.activity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.beryl.berylsystem.R;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.SeekBar)
    SeekBar seekbar;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressSeek=0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressSeek=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"Seek Bar Value"+progressSeek,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
