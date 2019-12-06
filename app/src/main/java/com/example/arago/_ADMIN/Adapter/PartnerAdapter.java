package com.example.arago._ADMIN.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago._ADMIN.Fragment.PartnerFragment;
import com.example.arago.DAO.PartnerDAO;
import com.example.arago.R;
import com.example.arago.Model.Partner;

import java.util.List;

public class PartnerAdapter extends RecyclerView.Adapter<PartnerAdapter.ViewHolder> {
    public Context context;
    PartnerDAO partnerDAO;
    PartnerFragment partnerFragment;
    List<Partner> arrPartner;

    // Constructor
    public PartnerAdapter(Context context, List<Partner> list, PartnerFragment fr) {
        this.context = context;
        this.arrPartner = list;
        this.partnerFragment = fr;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvPhone, tvEmail, tvAddress, tvCMND;
        public ImageView imgXoa;
        public ViewHolder(View itemView) {
            super(itemView);
            imgXoa = itemView.findViewById(R.id.iv_delete_cell_partner);
            tvName = itemView.findViewById(R.id.tv_cell_name);
            tvPhone = itemView.findViewById(R.id.tv_cell_phone);
            tvEmail = itemView.findViewById(R.id.tv_cell_email);
            tvAddress = itemView.findViewById(R.id.tv_cell_address);
            tvCMND = itemView.findViewById(R.id.tv_cell_cmnd);
        }
    }

    @NonNull
    @Override
    public PartnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tao view va gan layout vao view
        View v = LayoutInflater.from(context)
                .inflate(R.layout.partner_recycler_items, parent, false);
        // gan cac thuoc tinh nhu size, margins, paddings.....
        return new PartnerAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull final PartnerAdapter.ViewHolder holder, final int position) {
        // - lay  phan tu tu danh sach du lieu tai vi tri position
        // - thay the noi dung cua view voi phan tu do
        final Partner partner = arrPartner.get(position);

        holder.tvName.setText(partner.getPartner_name());
        holder.tvPhone.setText(partner.getPartner_phone());
        holder.tvEmail.setText(partner.getPartner_email());
        holder.tvAddress.setText(partner.getPartner_address());
        holder.tvCMND.setText(partner.getPartner_cmnd());

        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                partnerDAO = new PartnerDAO(context);
                partnerFragment.partnerDelete(partner);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrPartner.size();
    }
}
