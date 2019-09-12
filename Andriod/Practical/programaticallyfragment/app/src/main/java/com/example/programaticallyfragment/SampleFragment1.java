package com.example.programaticallyfragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SampleFragment1 extends Fragment
{
    private static final String COMMON_TAG="CombinedLifeCycle";
    private static final String FRAGMENT_NAME=SampleFragment1.class.getSimpleName();
    private static final String TAG=COMMON_TAG;
    private Button b1;
    private EditText editText1,editText2;


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        Log.i(TAG,FRAGMENT_NAME+"onAttach");
    }

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        Log.i(TAG,FRAGMENT_NAME+"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
    {
        Log.i(TAG,FRAGMENT_NAME+"onCreateView");
        View view =inflater.inflate(R.layout.sample1,container,false);

        b1=(Button)view.findViewById(R.id.button1);
        editText1=(EditText)view.findViewById(R.id.editText);
        editText2=(EditText)view.findViewById(R.id.editText1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                int first=Integer.parseInt(editText1.getText().toString());
                int second=Integer.parseInt(editText2.getText().toString());
                int sum=first+second;
                Toast toast=Toast.makeText(getActivity(),"Sum = "+String.valueOf(sum),Toast.LENGTH_LONG);
                toast.show();
            }

        });

        return view;

    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState)
    {
        super.onActivityCreated(saveInstanceState);
        Log.i(TAG,FRAGMENT_NAME+"onActivityCreated");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.i(TAG,FRAGMENT_NAME+"onStart");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.i(TAG,FRAGMENT_NAME+"onResume");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.i(TAG,FRAGMENT_NAME+"onPause");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.i(TAG,FRAGMENT_NAME+"onStop");
    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        Log.i(TAG,FRAGMENT_NAME+"onDestroyView");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG,FRAGMENT_NAME+"onDestroy");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        Log.i(TAG,FRAGMENT_NAME+"onDetach");
    }
}
