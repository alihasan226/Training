package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    public final String[] string={"India","Australia","Canada","New Zealand","Indonesia","Africa"};
    public final Integer[] imageid={R.drawable.australia,R.drawable.canada,R.drawable.nz,R.drawable.south_africa,R.drawable.south_africa,R.drawable.india,R.drawable.indonesia};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomList adapter = new
                CustomList(MainActivity.this, string, imageid);
        list=(ListView)findViewById(R.id.list_view);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked at " +string[+ position], Toast.LENGTH_SHORT).show();

            }
        });
    }
}
