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

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // Constructor
    public PartnerAdapter(Context context, List<Partner> list, PartnerFragment fr) {
        this.context = context;
        this.arrPartner = list;
        this.partnerFragment = fr;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvEmail;
        public ImageView imgXoa;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            imgXoa = itemView.findViewById(R.id.iv_delete_cell_partner);
            tvName = itemView.findViewById(R.id.tv_cell_partner_name);
            tvEmail = itemView.findViewById(R.id.tv_cell_partner_email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int posititon = getAdapterPosition();
                        if (posititon != RecyclerView.NO_POSITION){
                            listener.onItemClick(posititon);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public PartnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tao view va gan layout vao view
        View v = LayoutInflater.from(context)
                .inflate(R.layout.partner_recycler_items, parent, false);
        // gan cac thuoc tinh nhu size, margins, paddings.....
        return new PartnerAdapter.ViewHolder(v, mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull final PartnerAdapter.ViewHolder holder, final int position) {
        // - lay  phan tu tu danh sach du lieu tai vi tri position
        // - thay the noi dung cua view voi phan tu do
        final Partner partner = arrPartner.get(position);

        holder.tvName.setText(partner.getPartner_name());
        holder.tvEmail.setText(partner.getPartner_email());

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
