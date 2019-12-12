package com.example.arago._USER.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.R;
import com.example.arago._USER.Fragment.FragmentHistory;
import com.example.arago._USER.Model.History;

import java.util.List;

public class History_Adapter extends RecyclerView.Adapter<History_Adapter.ViewHolder> {
    public Context context;
    FragmentHistory fragmentHistory;
    List<History> arrHistory;

    // Constructor
    public History_Adapter(Context context, List<History> list, FragmentHistory fr) {
        this.context = context;
        this.arrHistory = list;
        this.fragmentHistory = fr;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvDate, tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_cell_name);
            tvDate = itemView.findViewById(R.id.tv_cell_date);
            tvPrice = itemView.findViewById(R.id.tv_cell_price);
        }
    }

    @NonNull
    @Override
    public History_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tao view va gan layout vao view
        View v = LayoutInflater.from(context)
                .inflate(R.layout.history_recycler_items, parent, false);
        // gan cac thuoc tinh nhu size, margins, paddings.....
        return new History_Adapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull final History_Adapter.ViewHolder holder, final int position) {
        // - lay  phan tu tu danh sach du lieu tai vi tri position
        // - thay the noi dung cua view voi phan tu do
        final History history = arrHistory.get(position);

        holder.tvName.setText(history.getName());
        holder.tvDate.setText("Ngày đặt: " + history.getDate());
        holder.tvPrice.setText(history.getPrice() + " VND");
    }

    @Override
    public int getItemCount() {
        return arrHistory.size();
    }
}
