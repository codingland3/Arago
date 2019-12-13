package com.example.arago._PARTNER.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.Model.PartnerHistory;
import com.example.arago.R;
import com.example.arago._PARTNER.Fragment.FragmentHistory;
import com.example.arago._USER.Model.History;

import java.util.List;

public class PartnerHistory_Adapter extends RecyclerView.Adapter<PartnerHistory_Adapter.ViewHolder> {
    public Context context;
    FragmentHistory fragmentHistory;
    List<PartnerHistory> arrHistory;

    // Constructor
    public PartnerHistory_Adapter(Context context, List<PartnerHistory> list, com.example.arago._PARTNER.Fragment.FragmentHistory fr) {
        this.context = context;
        this.arrHistory = list;
        this.fragmentHistory = fr;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvDate, tvPrice, tvCustomerName, tvCustomerPhone, tvAddress, tvProblem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_cell_name);
            tvDate = itemView.findViewById(R.id.tv_cell_date);
            tvPrice = itemView.findViewById(R.id.tv_cell_price);

            tvCustomerName = itemView.findViewById(R.id.tv_cell_customer_name);
            tvAddress = itemView.findViewById(R.id.tv_cell_customer_address);
            tvCustomerPhone = itemView.findViewById(R.id.tv_cell_customer_phone);
            tvProblem = itemView.findViewById(R.id.tv_cell_problem);
        }
    }

    @NonNull
    @Override
    public PartnerHistory_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tao view va gan layout vao view
        View v = LayoutInflater.from(context)
                .inflate(R.layout.partner_history_recycler_items, parent, false);
        // gan cac thuoc tinh nhu size, margins, paddings.....
        return new PartnerHistory_Adapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull final PartnerHistory_Adapter.ViewHolder holder, final int position) {
        // - lay  phan tu tu danh sach du lieu tai vi tri position
        // - thay the noi dung cua view voi phan tu do
        final PartnerHistory partnerHistory = arrHistory.get(position);

        holder.tvName.setText(partnerHistory.getHistory_customer_name());
        holder.tvDate.setText("Ngày đặt: " + partnerHistory.getHistory_datetime());
        holder.tvPrice.setText(partnerHistory.getHistory_price() + " VND");
        holder.tvCustomerName.setText("Tên khách hàng: " + partnerHistory.getHistory_customer_name());
        holder.tvCustomerPhone.setText("Số điện thoại: " + partnerHistory.getHistory_customer_phone());
        holder.tvAddress.setText("Địa chỉ: " + partnerHistory.getHistory_customer_address());
        holder.tvProblem.setText("Mô tả: " + partnerHistory.getHistory_errortype());
    }

    @Override
    public int getItemCount() {
        return arrHistory.size();
    }
}
