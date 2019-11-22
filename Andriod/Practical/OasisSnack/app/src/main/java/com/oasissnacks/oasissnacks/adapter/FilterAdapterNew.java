package com.oasissnacks.oasissnacks.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.interfce.OnFilterClickListner;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.ButtonAnimation;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.Preferences;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterAdapterNew extends RecyclerView.Adapter<FilterAdapterNew.FilterViewHolder> {
    public List<String> list;
    public Context context;
    public String ViewEntitiy;
    public OnFilterClickListner listner;
    public AppUser appUser = new AppUser();
    public int row_index = -1;
    String[] abc = {""};
    public HashMap<String, String> range = new HashMap<>();
    public SparseBooleanArray array = new SparseBooleanArray();

    public FilterAdapterNew(List<String> list, Context context, String viewEntitiy, OnFilterClickListner listner) {
        this.list = list;
        this.context = context;
        ViewEntitiy = viewEntitiy;
        this.listner = listner;
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_filter, parent, false);
        return new FilterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {
        if (array.get(position)) {
            holder.cb.setChecked(true);
        } else {
            holder.cb.setChecked(false);
        }
        if (ViewEntitiy.equalsIgnoreCase("Category")) {

        } else if (ViewEntitiy.equalsIgnoreCase("Price Range")) {
            holder.seekbar.setVisibility(View.VISIBLE);

            holder.seekbar.setMinValue(Float.parseFloat(list.get(0).split(";")[0]));
            holder.seekbar.setMaxValue(Float.parseFloat(list.get(0).split(";")[1]));


            holder.tvMinimum.setVisibility(View.VISIBLE);
            holder.tvMaximum.setVisibility(View.VISIBLE);
            holder.cb.setVisibility(View.GONE);
        } else {

            if (list.get(position).split(";").length > 3 & list.get(position).endsWith("true")) {
                holder.tvMinimum.setVisibility(View.GONE);
                holder.tvMaximum.setVisibility(View.GONE);
                holder.cb.setText(list.get(position).split(";")[0]);
                holder.seekbar.setVisibility(View.GONE);
                holder.cb.setVisibility(View.VISIBLE);
                holder.cb.setChecked(true);
            } else {
                holder.tvMinimum.setVisibility(View.GONE);
                holder.tvMaximum.setVisibility(View.GONE);
                holder.cb.setText(list.get(position).split(";")[0]);

                holder.seekbar.setVisibility(View.GONE);
                holder.cb.setVisibility(View.VISIBLE);

            }

        }
       /* holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if (compoundButton.isChecked()){
                        compoundButton.setChecked(false);
                    }else {
                        compoundButton.setChecked(true);
                    }

                }
            }
        });
*/

        holder.rlRoot.setOnClickListener(view -> {

            row_index = position;
            if (ViewEntitiy.equalsIgnoreCase("Category")) {
                listner.onFilterProductClick(view, position);
               /* if (position == row_index) {
                    holder.rlRoot.setBackgroundResource(R.drawable.bg_bottamfilter);

                } else {
                    holder.rlRoot.setBackgroundResource(R.color.colorGrayRecycler);


                }*/

            } else if (ViewEntitiy.equalsIgnoreCase("Price Range")) {


            } else {
                listner.onFilterProductClick(view, position);
                if (holder.cb.isChecked()) {
                    holder.cb.setChecked(false);

                    array.delete(holder.getAdapterPosition());

                } else {
                    holder.cb.setChecked(true);

                    array.put(holder.getAdapterPosition(), true);

                }

            }

        });
        holder.seekbar.setOnRangeSeekbarChangeListener((minValue, maxValue) -> {
            holder.tvMinimum.setText(String.format("%.2f", minValue));
            range.put("min", String.valueOf(minValue));

            Preferences.getInstance(context).setPriceMinValue(minValue.toString());

            appUser.FilerElements.put("Price Range", range);
            LocalRepositories.saveAppUser(context, appUser);
            holder.tvMaximum.setText(String.format("%.2f", maxValue));
        });

        holder.seekbar.setOnRangeSeekbarFinalValueListener((minValue, maxValue) -> {
            range.put("max", String.valueOf(maxValue));
            appUser.FilerElements.put("Price Range", range);
            Preferences.getInstance(context).setPriceMAxValue(maxValue.toString());
            LocalRepositories.saveAppUser(context, appUser);
            holder.tvMinimum.setText(String.format("%.2f", minValue));
            holder.tvMaximum.setText(String.format("%.2f", maxValue));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder {

        TextView tvFilter, tvMinimum, tvMaximum;

        CheckBox cb;
        RelativeLayout rlRoot;
        CrystalRangeSeekbar seekbar;

        public FilterViewHolder(@NonNull View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.cbFilter);
            rlRoot = itemView.findViewById(R.id.rlRoot);
            seekbar = itemView.findViewById(R.id.seekbar);
            tvMinimum = itemView.findViewById(R.id.tvMinimumText);
            tvMaximum = itemView.findViewById(R.id.tvMaximumText);


        }
    }
}
