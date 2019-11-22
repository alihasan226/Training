package com.oasissnacks.oasissnacks.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.acitivity.CartActivity;
import com.oasissnacks.oasissnacks.acitivity.ProductListActivity;
import com.oasissnacks.oasissnacks.databinding.LayoutCartdetailsBinding;
import com.oasissnacks.oasissnacks.databinding.RowOrdersummaryBinding;
import com.oasissnacks.oasissnacks.interfce.OnCartItemClickListner;
import com.oasissnacks.oasissnacks.network.Response.cartresponse.CartProductResponse;

import java.util.ArrayList;
import java.util.List;

import static com.oasissnacks.oasissnacks.R.drawable.*;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public String[] item_quantity = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "23", "23", "24", "25", "26", "27", "28", "29", "30"};
    public ArrayAdapter<Integer> adapter;
    private LinearLayout btn_remove, btn_edit;
    private TextView tvQty;
    Context context;
    String cartEntity;
    OnCartItemClickListner listner;
    public List<CartProductResponse> list;
    private static final int CART_PAGE=1;
    private static final int ORDER_PAGE=2;

    public CartAdapter(Context context, List<CartProductResponse> list, String cartEntity, OnCartItemClickListner listner) {
        this.context = context;
        this.list = list;
        this.cartEntity = cartEntity;
        this.listner = listner;
    }



    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        if(viewType==ORDER_PAGE){
            view = inflater.inflate(R.layout.row_ordersummary, parent, false);
            return new ViewHolderOrder(view);
        }
        else if(viewType==CART_PAGE){
            view = inflater.inflate(R.layout.layout_cartdetails, parent, false);
            return new ViewHolderCart(view);
        }
        else {
            throw new RuntimeException("The type has to be ONE or TWO");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case CART_PAGE:
                cartLayout((ViewHolderCart) holder, position);
                break;
            case ORDER_PAGE:
                orderLayout((ViewHolderOrder) holder, position);
                break;
            default:
                break;
        }
    }

    private void cartLayout(ViewHolderCart holder, int position) {
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
                .into(holder.binding.productImage);
        if (cartEntity.equalsIgnoreCase("Checkout")) {
            holder.binding.itemDelete.setVisibility(View.INVISIBLE);
        } else {
            //holder.binding.itemDelete.setOnClickListener(view -> listner.onCartClick(view, position, "Remove"));
            holder.binding.itemDelete.setOnClickListener(view -> {
                ViewGroup viewGroup = view.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_removeitem, viewGroup, false);
                AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.CustomAlertDialog);
                builder.setView(dialogView);
                Dialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                LinearLayout notRemoveItem = (LinearLayout) dialogView.findViewById(R.id.llremove);
                notRemoveItem.setOnClickListener(view1 -> alertDialog.dismiss());

                LinearLayout removeItem=(LinearLayout)dialogView.findViewById(R.id.llEdit);
                removeItem.setOnClickListener(view1 -> {
                    listner.onCartClick(view, position, "Remove");
                    alertDialog.dismiss();});
                alertDialog.show();
                alertDialog.getWindow().setLayout(720, 390);
            });
        }

        holder.binding.productName.setText(list.get(position).getName());
        holder.binding.tvProductPrice.setText("$" + list.get(position).getPrice());
        holder.binding.rlRoot.setOnClickListener(view -> {
            listner.onCartClick(view,position,"Product");
        });


        if (list.get(position).getStock()) {
            holder.binding.tvStock.setText("In Stock");
            holder.binding.tvStock.setTextColor(Color.parseColor("#0FA319"));
        } else {
            holder.binding.tvStock.setText("Out of Stock");
            holder.binding.tvStock.setTextColor(Color.parseColor("#EC0E0E"));
        }


        holder.binding.tvQty.setText(list.get(position).getQuantity());
        holder.binding.layoutQty.setOnClickListener(view -> {
            ViewGroup viewGroup = view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_quantity, viewGroup, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(dialogView);
            Dialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            ImageView imageCancel = (ImageView) dialogView.findViewById(R.id.imageCancel);
            imageCancel.setOnClickListener(view1 -> alertDialog.dismiss());

            ListView listQntyItem = (ListView) dialogView.findViewById(R.id.listQntyItem);
            listQntyItem.setAdapter(new ArrayAdapter<String>(context, R.layout.layout_qntyitem, item_quantity));
            listQntyItem.setOnItemClickListener((adapterView, view12, i, l) -> {
                String value = item_quantity[i];
                listner.onCartClick(view, position, value);
                alertDialog.dismiss();
                /*try{

                    holder.binding.tvQty.setText(""+value);
                }catch (Exception e){
                    e.printStackTrace();
                }*/
            });
            alertDialog.show();
            alertDialog.getWindow().setLayout(600, 950);
        });

    }

    private void orderLayout(ViewHolderOrder holder,int position) {

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
                .into(holder.binding.productImage);

        holder.binding.productName.setText(list.get(position).getName());
        holder.binding.tvProductPrice.setText("$"+list.get(position).getPrice());


        if(list.get(position).getStock()){
            holder.binding.tvStock.setText("In Stock");
            holder.binding.tvStock.setTextColor(Color.parseColor("#0FA319"));
        }
        else{
            holder.binding.tvStock.setText("Out of Stock");
            holder.binding.tvStock.setTextColor(Color.parseColor("#EC0E0E"));
        }


        holder.binding.tvQty.setText(list.get(position).getQuantity());
        holder.binding.layoutQty.setOnClickListener(view ->{
            ViewGroup viewGroup = view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_quantity, viewGroup, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(dialogView);
            Dialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            ImageView imageCancel=(ImageView)dialogView.findViewById(R.id.imageCancel);
            imageCancel.setOnClickListener(view1 -> alertDialog.dismiss());

            ListView listQntyItem=(ListView)dialogView.findViewById(R.id.listQntyItem);
            listQntyItem.setAdapter(new ArrayAdapter<String>(context,R.layout.layout_qntyitem,item_quantity));
            listQntyItem.setOnItemClickListener((adapterView, view12, i, l) -> {
                String value=item_quantity[i];
                listner.onCartClick(view12,position,value);
                alertDialog.dismiss();
            });

            alertDialog.show();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.getWindow().setLayout(600, 950);
        } );

    }


    @Override
    public int getItemViewType(int position) {
        if(cartEntity.equalsIgnoreCase("Checkout")){
            return ORDER_PAGE;
        }
        else{
            return CART_PAGE;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderCart extends RecyclerView.ViewHolder {
        public LayoutCartdetailsBinding binding;


        public ViewHolderCart(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

    }

    class ViewHolderOrder extends RecyclerView.ViewHolder{
        public RowOrdersummaryBinding binding;

        public ViewHolderOrder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }

}
















