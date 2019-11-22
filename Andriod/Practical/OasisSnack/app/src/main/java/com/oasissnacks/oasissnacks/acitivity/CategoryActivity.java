package com.oasissnacks.oasissnacks.acitivity;

import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.CategoryAdapter;
import com.oasissnacks.oasissnacks.adapter.CategoryData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    ArrayList list_child;
    HashMap<String, List<String>> list_group;
    private ExpandableListAdapter expandableListAdapter;

    @BindView(R.id.category_expandablelist)
    ExpandableListView expandableListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        list_group = CategoryData.getData();
        list_child = new ArrayList<>(list_group.keySet());
        expandableListAdapter = (ExpandableListAdapter) new CategoryAdapter(getApplicationContext(), list_child, list_group);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setGroupIndicator(null);

    }
}
