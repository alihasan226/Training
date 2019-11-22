package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.acitivity.ProductDetailsActivity;
import com.oasissnacks.oasissnacks.acitivity.ProductListActivity;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.CustomTextView;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.Preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    @BindView(R.id.expandableListView)
    ExpandableListView expListView;
    public Context _context;
    public List<String> _listDataHeader = new ArrayList<>();
    // child data in format of header title, child title
    public HashMap<String, List<String>> _listDataChild = new HashMap<>();

    @BindView(R.id.lblListHeader)
    TextView gruop_text;

    LinearLayoutManager linearLayoutManager;
    public AppUser appUser = new AppUser();


    private static final int[] EMPTY_STATE_SET = {};
    private static final int[] GROUP_EXPANDED_STATE_SET = {android.R.attr.state_expanded};
    private static final int[][] GROUP_STATE_SETS = {EMPTY_STATE_SET, GROUP_EXPANDED_STATE_SET};

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).split(";")[0])
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {



        ArrayList<String> new_Arr = new ArrayList<String>();
        for (int i2 = 0; i2 < _listDataChild.get(this._listDataHeader.get(groupPosition).split(";")[0])
                .size(); i2++) {
            new_Arr.add((String) getChild(groupPosition, i2));
        }


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_category_explist_child, null);
        }
/*
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
 */
        RecyclerView recyclerView = (RecyclerView) convertView.findViewById(R.id.category_recyclerview);
        linearLayoutManager = new LinearLayoutManager(_context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        //list_image=new ArrayList<>(Arrays.asList(imageid));
        //list_text=new ArrayList<String>(Arrays.asList(expandedListText));
        recyclerView.setAdapter(new CategoryRecyclerviewAdapter(_context, new_Arr));
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_category_explist_parent, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.exp_parent);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle.split(";")[0]);


        //Removing Indicator for Empty Group
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_category_explist_parent, null);
        }

        ImageView up = (ImageView) convertView.findViewById(R.id.up);
        ImageView down = (ImageView) convertView.findViewById(R.id.down);
       /* if (getChildrenCount(groupPosition) == 0) {
            convertView.setEnabled(false);
            up.setVisibility(View.GONE);
            down.setVisibility(View.GONE);
        } else {
            convertView.setEnabled(true);
            if (isExpanded) {
                up.setVisibility(View.VISIBLE);
                down.setVisibility(View.GONE);
            } else {
                up.setVisibility(View.GONE);
                down.setVisibility(View.VISIBLE);
            }
        }
*/

        //Image view which you put in layout_group.xml


       convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    //Toast.makeText(_context,"text",Toast.LENGTH_LONG).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("actionbar_name", _listDataHeader.get(groupPosition).split(";")[0]);
                    appUser.cat_id = _listDataHeader.get(groupPosition).split(";")[1];
                Preferences.getInstance(_context).setCatID(_listDataHeader.get(groupPosition).split(";")[1]);
                    LocalRepositories.saveAppUser(_context.getApplicationContext(), appUser);
                    Intent intent = new Intent(_context, ProductListActivity.class);
                    intent.putExtras(bundle);
                    _context.startActivity(intent);



            }
        });


        if (this._listDataChild.get(this._listDataHeader.get(groupPosition).split(";")[0]).size() == 0) {
            up.setVisibility(View.GONE);
            down.setVisibility(View.GONE);


        } else {
            if (isExpanded) {
                up.setVisibility(View.VISIBLE);
                down.setVisibility(View.GONE);
            } else {
                up.setVisibility(View.GONE);
                down.setVisibility(View.VISIBLE);
            }
        }


        return convertView;

    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
