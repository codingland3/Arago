package com.example.arago._USER.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.Model.Order;
import com.example.arago.R;
import com.example.arago._USER.ViewHolder.CartViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<Order> listdata = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemview=inflater.inflate(R.layout.customer_recyclerview_cart,parent,false);
        return new CartViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.txt_Cartname.setText(listdata.get(position).getName());
        holder.txtSDT.setText(listdata.get(position).getPhone());
        holder.txtuyeucau.setText(listdata.get(position).getErrortype());
        holder.txtaddress.setText(listdata.get(position).getAddress());
    }

    @Override
    public int getItemCount() {

        return listdata.size();
    }
}
