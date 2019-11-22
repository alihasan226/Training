package com.oasissnacks.oasissnacks.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.acitivity.LandingPageActivity;
import com.oasissnacks.oasissnacks.acitivity.ProductDetailsActivity;
import com.oasissnacks.oasissnacks.databinding.RowWishlistBinding;
import com.oasissnacks.oasissnacks.interfce.OnFilterClickListner;
import com.oasissnacks.oasissnacks.network.Response.product.Products;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.Preferences;

import java.util.List;

public class WishListAdaptet extends RecyclerView.Adapter<WishListAdaptet.WishLstViewHolder> {
    public List<Products> list;
    public Context context;
    public OnFilterClickListner listner;

    public WishListAdaptet(List<Products> list, Context context,OnFilterClickListner listner) {
        this.list = list;
        this.context = context;
        this.listner=listner;
    }

    @NonNull
    @Override
    public WishLstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_wishlist, parent, false);
        return new WishLstViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishLstViewHolder holder, int position) {
        if (list != null) {
            holder.binding.tvProductName.setText(list.get(position).getName());
            holder.binding.tvProductPrice.setText("$"+list.get(position).getPrice());
            holder.binding.tvPRoductCount.setText("($"+list.get(position).getPrice_per_packet()+"/count)");

            holder.binding.ivDelete.setOnClickListener(view -> {
                   //listner.onFilterProductClick(view,position);
                ViewGroup viewGroup = view.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.row_removewishlist, viewGroup, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.CustomAlertDialog);
                builder.setView(dialogView);
                Dialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                LinearLayout notRemoveItem = (LinearLayout) dialogView.findViewById(R.id.llremove);
                notRemoveItem.setOnClickListener(view1 -> alertDialog.dismiss());

                LinearLayout removeItem=(LinearLayout)dialogView.findViewById(R.id.llEdit);
                removeItem.setOnClickListener(view1 -> {listner.onFilterProductClick(view, position);alertDialog.dismiss();});
                alertDialog.show();
                alertDialog.getWindow().setLayout(720, 360);


            });

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.no_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .dontAnimate()
                    .dontTransform();
            Glide.with(context)
                    .load(list.get(position).getImage())
                    .apply(options)
                    .into(holder.binding.ivPRoductImage);

            AppUser appUser= LocalRepositories.getAppUser(context);
            holder.binding.llProductId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appUser.product_id=list.get(position).getId();
                    LocalRepositories.saveAppUser(context.getApplicationContext(),appUser);
                    context.startActivity(new Intent(context, ProductDetailsActivity.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WishLstViewHolder extends RecyclerView.ViewHolder {
        public RowWishlistBinding binding;

        public WishLstViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
