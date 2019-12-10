package com.example.arago._USER.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.R;
import com.example.arago._USER.Interface.ItemClicListener;


public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txt_Cartname,txtSDT,txtaddress,txtuyeucau;
    private ItemClicListener itemClicListener;

    public void setTxt_Cartname(TextView txt_Cartname) {
        this.txt_Cartname = txt_Cartname;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_Cartname=(TextView)itemView.findViewById(R.id.card_item_name);
        txtSDT=(TextView)itemView.findViewById(R.id.card_item_phone);
        txtaddress=(TextView)itemView.findViewById(R.id.card_item_address);
        txtuyeucau=(TextView)itemView.findViewById(R.id.card_item_errortype);
    }

    @Override
    public void onClick(View view) {

    }
}

