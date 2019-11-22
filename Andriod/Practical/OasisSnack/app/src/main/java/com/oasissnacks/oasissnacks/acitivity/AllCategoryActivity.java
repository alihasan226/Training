package com.oasissnacks.oasissnacks.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.CategoryAdapter;
import com.oasissnacks.oasissnacks.adapter.CategoryData;
import com.oasissnacks.oasissnacks.adapter.ExpandableListAdapter;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.category.CategoryResponse;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.Helper;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllCategoryActivity extends RegisterAbstractActivity {
    @BindView(R.id.category_expandablelist)
    ExpandableListView expandableListView;
    List<String> listDataHeader,getListDataHeader;
    HashMap<String, List<String>> listDataChild;
    ActionBarDrawerToggle toggle;
    AppUser appUser;
    MyProgressDialog progressDialog;
    ExpandableListAdapter listAdapter;

    HashMap<String, List<String>> list_group;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allcategories);
        ButterKnife.bind(this);
        expandableListView.setGroupIndicator(null);


        appUser = LocalRepositories.getAppUser(this);
        LocalRepositories.saveAppUser(getApplicationContext(), appUser);
        initActionbar();
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
    }
    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();
        View viewActionBar = getLayoutInflater().inflate(R.layout.action_bar_tittle_text_layout, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(viewActionBar, params);
        TextView actionbarTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);

        actionbarTitle.setText("All Categories");
        actionbarTitle.setTextSize(18);
        //actionbarTitle.setTypeface(TypefaceCache.get(getAssets(), 0));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        ImageView back = (ImageView) viewActionBar.findViewById(R.id.back);
        back .setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_allcategories;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.ACTION_GET_CATEGORY);
        } else {
            Helper.alert(this, "No Internet Connection", "Oasis Snacks");
        }
    }
    @Subscribe
    public void getcategory(CategoryResponse response){
        progressDialog.dismiss();
        listDataChild = new HashMap<String, List<String>>();
        listDataHeader=new ArrayList<>();
        for(int i=0;i<response.getCategories().size();i++){
            listDataHeader.add(response.getCategories().get(i).getName()+";"+response.getCategories().get(i).getId());
            ArrayList<String> child=new ArrayList<>();
            if(response.getCategories().get(i).getSub_category()!=null) {
                for (int j = 0; j < response.getCategories().get(i).getSub_category().size(); j++) {
                    child.add(response.getCategories().get(i).getSub_category().get(j).getName()+";"+response.getCategories().get(i).getSub_category().get(j).getId());
                }


            }/*else{
                    child.add("");
                }*/
            listDataChild.put(response.getCategories().get(i).getName(), child);



        }

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(listAdapter);
        for (int i = 0; i < listDataHeader.size(); i++) {
            if(listDataChild.get(this.listDataHeader.get(i).split(";")[0])
                    .size()==0){

            }else
            expandableListView.expandGroup(i);
        }



    }

}
