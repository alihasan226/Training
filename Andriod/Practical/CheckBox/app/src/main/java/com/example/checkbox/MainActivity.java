package com.example.checkbox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    protected CheckBox cb1,cb2,cb3;
    protected Button but;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();//taking the instance of ActionBar using the method getSupportActionbar()
        actionBar.setTitle("Food Items");
        addListenerOnButtonClick();//to make our button active

    }

    private void addListenerOnButtonClick() {
        cb1=(CheckBox)findViewById(R.id.checkBox1);
        cb2=(CheckBox)findViewById(R.id.checkBox2);
        cb3=(CheckBox)findViewById(R.id.checkBox3);
        but=(Button)findViewById(R.id.button);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totalamount = 0;
                StringBuilder result = new StringBuilder();
                result.append("Select Item");
                if (cb1.isChecked()) {
                    result.append("\nPizza 100Rs");
                    totalamount += 100;
                }
                if (cb2.isChecked()) {
                    result.append("\nCoffee 80Rs");
                    totalamount += 80;
                }
                if (cb3.isChecked()) {
                    result.append("\nBurger 50Rs");
                    totalamount += 50;
                }

                result.append("\nTotal: "+totalamount+"Rs");
                //Displaying the message on the toast
                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();

            }
        });


    }
}
