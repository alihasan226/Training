package com.example.programaticallyfragment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button b1;
    private ActionBar actionBar;
    boolean status=false;
    private static final String COMMON_TAG="CombinedLifeCycle";
    private static final String ACTIVITY_NAME=MainActivity.class.getSimpleName();
    private static final String TAG=COMMON_TAG;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Fragment Transaction");
        b1=(Button)findViewById(R.id.button1);

        final Bundle new_bundle=new Bundle();//Bundle contain the set information
        new_bundle.putString("name","alihasan");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FragmentManager fragmentManager1=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager1.beginTransaction();
                if(!status)
                {
                    //b1.setClickable(false);//to make button not responsive on pressing
                    SampleFragment sample1=new SampleFragment();
                    sample1.setArguments(new_bundle);
                    fragmentTransaction.add(R.id.framelayout,sample1);
                    fragmentTransaction.addToBackStack(null);//fragment back stack is managed by the Fragment Manager
                    fragmentTransaction.commit();
                    b1.setText("Page 2");
                    status=true;
                }
                else
                {

                    SampleFragment1 sample1=new SampleFragment1();
                    fragmentTransaction.add(R.id.framelayout,sample1);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    b1.setText("Page 1");
                    status=false;
                }
            }
        });

        Log.i(TAG,ACTIVITY_NAME+"onCreate()");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.i(TAG,ACTIVITY_NAME+"onStart()");
    }


    @Override
    public void onResume()
    {
        super.onResume();
        Log.i(TAG,ACTIVITY_NAME+"onResume()");
    }
    @Override
    public void onPause()
    {
        super.onPause();
        Log.i(TAG,ACTIVITY_NAME+"onPause()");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.i(TAG,ACTIVITY_NAME+"onStop()");
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG,ACTIVITY_NAME+"onDestroy()");
    }

}
