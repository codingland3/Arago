package com.example.arago._USER.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.R;
import com.example.arago._USER.Interface.ItemClicListener;

public class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView job_name;
    public ImageView imagejob;
    private ItemClicListener itemClicListener;


    public void setItemClicListener(ItemClicListener itemClicListener) {
        this.itemClicListener = itemClicListener;
    }

    public JobViewHolder(@NonNull View itemView) {
        super(itemView);
        job_name = (TextView)itemView.findViewById(R.id.job_name);
        imagejob = (ImageView)itemView.findViewById(R.id.job_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClicListener.onClick(view,getAdapterPosition(),false);
    }
}
