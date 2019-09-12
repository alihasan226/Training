package com.example.programaticallyfragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class SampleFragment extends Fragment
{
    private static final String COMMON_TAG="CombinedLifeCycle";
    private static final String FRAGMENT_NAME=SampleFragment.class.getSimpleName();
    private static final String TAG=COMMON_TAG;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState)
    {
        Log.i(TAG,FRAGMENT_NAME+"onCreateView");
        String strtext=getArguments().getString("name");
        Toast toast=Toast.makeText(getActivity(),strtext,Toast.LENGTH_LONG);
        toast.show();
        return inflater.inflate(R.layout.sample,container,false);
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
