package com.example.arago.ADMIN.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.ADMIN.Fragment.CustomerFragment;
import com.example.arago.DAO.CustomerDAO;
import com.example.arago.R;
import com.example.arago.USER.Model.Customer;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    public Context context;
    CustomerDAO customerDAO;
    CustomerFragment customerFragment;
    List<Customer> arrCustomer;

    // Constructor
    public CustomerAdapter(Context context, List<Customer> list, CustomerFragment fr) {
        this.context = context;
        this.arrCustomer = list;
        this.customerFragment = fr;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvPhone, tvEmail, tvAddress;
        public ImageView imgXoa;
        public ViewHolder(View itemView) {
            super(itemView);
            imgXoa = itemView.findViewById(R.id.iv_delete_cell_partner);
            tvName = itemView.findViewById(R.id.tv_cell_name);
            tvPhone = itemView.findViewById(R.id.tv_cell_phone);
            tvEmail = itemView.findViewById(R.id.tv_cell_email);
            tvAddress = itemView.findViewById(R.id.tv_cell_address);
        }
    }

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tao view va gan layout vao view
        View v = LayoutInflater.from(context)
                .inflate(R.layout.customer_recycler_items, parent, false);
        // gan cac thuoc tinh nhu size, margins, paddings.....
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomerAdapter.ViewHolder holder, final int position) {
        // - lay  phan tu tu danh sach du lieu tai vi tri position
        // - thay the noi dung cua view voi phan tu do
        final Customer customer = arrCustomer.get(position);

        holder.tvName.setText(customer.getCustomer_name());
        holder.tvPhone.setText(customer.getCustomer_phone());
        holder.tvEmail.setText(customer.getCustomer_email());
        holder.tvAddress.setText(customer.getCustomer_address());

        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerDAO = new CustomerDAO(context);
                customerFragment.customerDelete(customer);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrCustomer.size();
    }
}
