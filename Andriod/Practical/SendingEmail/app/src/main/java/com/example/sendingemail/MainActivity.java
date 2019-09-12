package com.example.sendingemail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected EditText editText1,editText2;
    protected Button button;

    /*
    For sending Email
    protected Button b1;
    protected EditText editText1,editText2,editText3;
    */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        /*

        For sending Email
        b1=(Button)findViewById(R.id.buttonl);
        editText1=(EditText)findViewById(R.id.edittext1);
        editText2=(EditText)findViewById(R.id.edittext2);
        editText3=(EditText)findViewById(R.id.edittext3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                String emailid=editText1.getText().toString();//getting the email id from the edittext
                String subject=editText2.getText().toString();//getting the subject from the edittext
                String body=editText3.getText().toString();//gettng the body from the edittext

                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");

                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{emailid,"alihasan226@gmail.com"});//we have taken a string array because to add more email or to attach multiple email for send
                intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                intent.putExtra(Intent.EXTRA_TEXT,body);


                startActivity(intent);



                //Uniform Resource Identifier (URI) is a string of characters used to identify a resourceUniform Resource Identifier (URI) is a string of characters used to identify a resource
            }
        });


         */

        button=(Button)findViewById(R.id.buttonl);
        editText1=(EditText)findViewById(R.id.edittext1);
        editText2=(EditText)findViewById(R.id.edittext2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String sms="smsto:";
                sms=sms+editText1.getText().toString();
                String body_of_message=editText2.getText().toString();
                Uri uri=Uri.parse(sms);
                Intent it=new Intent(Intent.ACTION_SENDTO,uri);
                it.putExtra("sms_body",body_of_message);
                startActivity(it);
                /*
                or
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.fromParts("sms","123456",null));
                startActivity(intent);
                */

            }
        });



    }


}
