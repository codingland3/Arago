//package com.example.arago.USER.Adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.arago.R;
//import com.example.arago.USER.Model.Service;
//
//import java.util.ArrayList;
//
//public class Services_RecyclerViewAdapter extends RecyclerView.Adapter<Services_RecyclerViewAdapter.MyViewHolder> {
//
//    private ArrayList<Service> mServices;
//    private OnItemClickListener mListener;
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener){
//        mListener = listener;
//    }
//
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//
//        public ImageView imgService;
//        private TextView tvServiceTitle, tvServiceDescription;
//
//        public MyViewHolder(View itemView, final OnItemClickListener listener){
//            super(itemView);
//            imgService = itemView.findViewById(R.id.custom_itemlist_iv_services);
//            tvServiceTitle = itemView.findViewById(R.id.custom_itemlist_tv_services_title);
//            tvServiceDescription = itemView.findViewById(R.id.custom_itemlist_tv_services_desc);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (listener != null){
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION){
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });
//        }
//    }
//
//    public Services_RecyclerViewAdapter(ArrayList<Service> services){
//        mServices = services;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recyclerview_services, parent, false);
//        MyViewHolder myViewHolder = new MyViewHolder(v, mListener);
//        return myViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Service currentItem = mServices.get(position);
//        holder.imgService.setImageResource(currentItem.getImages());
//        holder.tvServiceTitle.setText(currentItem.getTitle());
//        holder.tvServiceDescription.setText(currentItem.getDescription());
//    }
//
//    @Override
//    public int getItemCount() {
//        return mServices.size();
//    }
//}