package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.databinding.RowReviewProductdetailsBinding;
import com.oasissnacks.oasissnacks.network.Response.product.ReviewProductDetailResponse;

import java.util.List;

public class ReviewProductDetailAdapter extends RecyclerView.Adapter<ReviewProductDetailAdapter.ReviewProductViewHolder> {

    public List<ReviewProductDetailResponse> list;
    public Context context;

    public ReviewProductDetailAdapter(List<ReviewProductDetailResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_review_productdetails,parent,false);
        return new ReviewProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReviewProductViewHolder holder, int position) {
        holder.binding.tvReviewName.setText(list.get(position).getName()+ ", "+list.get(position).getCreatedAt());
        holder.binding.tvReviewDescription.setText(list.get(position).getReview());
        holder.binding.tvRating.setText(Integer.parseInt(list.get(position).getRating())/20+"");
        holder.binding.tvREviewTitile.setText(list.get(position).getReviewTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReviewProductViewHolder extends RecyclerView.ViewHolder{
        public RowReviewProductdetailsBinding binding;

        public ReviewProductViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
}
