package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.databinding.RowReviewBinding;
import com.oasissnacks.oasissnacks.network.Response.homeresponse.ReviewResponse;

import java.util.List;

public class ReviewAdapter  extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    public List<ReviewResponse> list;
    public Context context;

    public ReviewAdapter(List<ReviewResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_review,parent,false);
        return new ReviewViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.binding.tvRating.setText(""+Integer.parseInt(list.get(position).getRating())/20);
        holder.binding.tvTitleReview.setText(list.get(position).getReviewDetail());
        holder.binding.tvReviewDescription.setText(list.get(position).getReviewNickname());
        holder.binding.tvReviewGivenPersonName.setText("by "+list.get(position).getReviewNickname()+" "+list.get(position).getReviewDate());
        holder.binding.tvProductName.setText(list.get(position).getProductName());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.no_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform();
        Glide.with(context)
                .load(list.get(position).getProductImg())
                .apply(options)
                .into(holder.binding.ivReviewImage);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  ReviewViewHolder extends RecyclerView.ViewHolder{
        public RowReviewBinding binding;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
}
