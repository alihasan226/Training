package com.example.communicationbetweenfragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class fragmentone extends Fragment
{
    buttonclick mclick;
    private Button button1;
    private EditText textView1,textView2;

    public interface buttonclick
    {
        void sendValue(String text);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle saveInatanceState)
    {
        View view=inflater.inflate(R.layout.layoutone,container,false);

        button1=(Button)view.findViewById(R.id.buttonl);
        textView1=(EditText)view.findViewById(R.id.textview1);
        textView2=(EditText)view.findViewById(R.id.textview2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    int a=Integer.parseInt(textView1.getText().toString());
                    int b=Integer.parseInt(textView2.getText().toString());
                    int sum=a+b;
                    String result=String.valueOf(sum);
                    someMethod(result);
            }
        });

        return  view;
    }
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            mclick=(buttonclick)activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()+" must implement test click");
        }
    }

    public void someMethod(String text)
    {
        mclick.sendValue(text);
    }
    @Override
    public void onDetach()
    {
        super.onDetach();
        mclick=null;
    }
}
