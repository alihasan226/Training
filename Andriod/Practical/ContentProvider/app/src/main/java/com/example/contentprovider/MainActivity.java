package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.net.Uri;
import android.content.ContentResolver;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ArrayList<String> list=new ArrayList<>();
                Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String[] projection={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
                String selection=null;
                String[] selectionArgs=null;
                String sortOrder=null;

                ContentResolver contentResolver=getContentResolver();
                Cursor cursor=contentResolver.query(uri,projection,selection,selectionArgs,sortOrder);

                while(cursor.moveToNext())
                {
                    String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    list.add(name+"\n"+number);
                }


                ((ListView)findViewById(R.id.listview)).setAdapter(new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,list));

            }
        });

    }
}





















