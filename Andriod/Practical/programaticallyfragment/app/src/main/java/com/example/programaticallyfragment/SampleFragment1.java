package com.example.programaticallyfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class SampleFragment1 extends Fragment
{
    private Button b1;
    private EditText editText1,editText2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)
    {
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
}
