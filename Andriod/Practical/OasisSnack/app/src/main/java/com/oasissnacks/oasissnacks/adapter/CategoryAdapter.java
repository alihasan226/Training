package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.acitivity.LandingPageActivity;
import com.oasissnacks.oasissnacks.acitivity.ProductListActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CategoryAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listtitle;
    private HashMap<String,List<String>> listdetails;

    //constant for change the state of icon after the expanded and shrink.
    private static final int[] EMPTY_STATE_SET = {};
    private static final int[] GROUP_EXPANDED_STATE_SET = { android.R.attr.state_expanded };
    private static final int[][] GROUP_STATE_SETS = {EMPTY_STATE_SET,GROUP_EXPANDED_STATE_SET};



    LinearLayoutManager linearLayoutManager;

    ArrayList list_text;
    String[] price={"Bubly Water","Bubly Orange","Bubly Peach","Bubly Cherry","Bubly Apple","Bubly Water","Bubly Orange","Bubly Peach","Bubly Cherry","Bubly Apple","Bubly Water","Bubly Orange"};
    public final Integer[] imageid={R.drawable.bubly_sparking_water,R.drawable.bubly_sparking_water_orange,R.drawable.bubly_sparking_water_peach,R.drawable.bubly_sparking_water_cherry,R.drawable.bubly_sparking_water_apple,R.drawable.bubly_sparking_water,R.drawable.bubly_sparking_water_orange,R.drawable.bubly_sparking_water_peach,R.drawable.bubly_sparking_water_cherry,R.drawable.bubly_sparking_water_apple};


    public CategoryAdapter(Context context, List<String> listtitle, HashMap<String, List<String>> listdetails) {
        this.listdetails=listdetails;
        this.listtitle=listtitle;
        this.context=context;
    }

    @Override
    public int getGroupCount() {
        return listtitle.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return this.listtitle.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.listdetails.get(this.listtitle.get(i))
                .get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headertitle=(String)getGroup(i);
        if(view==null)
        {
            LayoutInflater inflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.layout_category_explist_parent,null);

            TextView listTitleTextView = (TextView) view.findViewById(R.id.exp_parent);
            listTitleTextView.setTypeface(null, Typeface.BOLD);
            listTitleTextView.setText(headertitle);
        }

        //Removing Indicator for Empty Group
        if (view == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.layout_category_explist_parent, null);
        }

        //Image view which you put in layout_group.xml
        View ind = view.findViewById(R.id.up);
        if (ind != null)
        {

            ImageView indicator = (ImageView) ind;
            if (listdetails.get(this.listtitle.get(i)).size() == 0)
            {
                indicator.setVisibility(View.INVISIBLE);
            }
            else
            {
                indicator.setVisibility(View.VISIBLE);
                int stateSetIndex = (b ? 1 : 0);
                Drawable drawable = indicator.getDrawable();
                drawable.setState(GROUP_STATE_SETS[stateSetIndex]);
            }
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ArrayList<String> new_Arr=new ArrayList<String>();
        for(int i2=0;i2<listdetails.get(this.listtitle.get(i)).size();i2++)
        {
            new_Arr.add((String)getChild(i,i2));
        }

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.layout_category_explist_child, null);
        }

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.category_recyclerview);
        linearLayoutManager=new LinearLayoutManager(null,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //list_image=new ArrayList<>(Arrays.asList(imageid));
        //list_text=new ArrayList<String>(Arrays.asList(expandedListText));
        recyclerView.setAdapter(new CategoryRecyclerviewAdapter(context,new_Arr));

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }



}
