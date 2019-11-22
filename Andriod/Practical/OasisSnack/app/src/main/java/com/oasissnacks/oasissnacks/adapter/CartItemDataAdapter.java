package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.oasissnacks.oasissnacks.R;

import java.util.HashMap;
import java.util.List;

public class CartItemDataAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listtitle;
    private HashMap<String,List<String>> listdetails;

    public CartItemDataAdapter(Context context, List<String> listtitle, HashMap<String, List<String>> listdetails) {
        this.listdetails=listdetails;
        this.listtitle=listtitle;
        this.context=context;
    }


    @Override
    public int getGroupCount() {
        return this.listtitle.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.listdetails.get(this.listtitle.get(i)).size();
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
            view=inflater.inflate(R.layout.layout_cartlistgroup,null);

            TextView listTitleTextView = (TextView) view
                    .findViewById(R.id.expandable_cartlistgroup);
            listTitleTextView.setTypeface(null, Typeface.BOLD);
            listTitleTextView.setText(headertitle);
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        final String expandedListText = (String) getChild(i, i1);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.layout_cartlistitem, null);
        }
        TextView expandedListTextView = (TextView) view
                .findViewById(R.id.expandable_listitem);
        expandedListTextView.setText(expandedListText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
