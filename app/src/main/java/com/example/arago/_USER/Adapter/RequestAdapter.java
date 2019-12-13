package com.example.arago._USER.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.DAO.PartnerHistoryDAO;
import com.example.arago.DAO.RequestDAO;
import com.example.arago.Model.PartnerHistory;
import com.example.arago.Model.Request;
import com.example.arago.R;
import com.example.arago._PARTNER.Fragment.FragmentRequest;
import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder>{
    public Context context;
    FragmentRequest fragmentRequest;
    List<Request> arrPartner;
    PartnerHistoryDAO partnerHistoryDAO;
    RequestDAO requestDAO;

    // Constructor
    public RequestAdapter(Context context, List<Request> list, FragmentRequest fr) {
        this.context = context;
        this.arrPartner = list;
        this.fragmentRequest = fr;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNameService, tvName, tvPhone, tvAddress, tvDate, tvMota, tvPrice;
        public ImageView imgAccept;
        public ViewHolder(View itemView) {
            super(itemView);

            tvNameService = itemView.findViewById(R.id.tvNameService);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvDate = itemView.findViewById(R.id.tvDateTime);
            tvMota = itemView.findViewById(R.id.tvErrorType);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imgAccept = itemView.findViewById(R.id.iv_Accept);
        }
    }

    @NonNull
    @Override
    public RequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tao view va gan layout vao view
        View v = LayoutInflater.from(context)
                .inflate(R.layout.customer_request, parent, false);
        // gan cac thuoc tinh nhu size, margins, paddings.....
        return new RequestAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull final RequestAdapter.ViewHolder holder, final int position) {
        // - lay  phan tu tu danh sach du lieu tai vi tri position
        // - thay the noi dung cua view voi phan tu do
        final Request request = arrPartner.get(position);

        holder.tvNameService.setText(request.getRequest_service_name());
        holder.tvName.setText(request.getRequest_customer_name());
        holder.tvPhone.setText(request.getRequest_customer_phone());
        holder.tvAddress.setText(request.getRequest_customer_address());
        holder.tvDate.setText(request.getRequest_datetime());
        holder.tvMota.setText(request.getRequest_errortype());
        holder.tvPrice.setText(request.getRequest_price());

        holder.imgAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serviceName = request.getRequest_service_name();
                String customerName = request.getRequest_customer_name();
                String customerPhone = request.getRequest_customer_phone();
                String customerAddress = request.getRequest_customer_address();
                String dateTime = request.getRequest_datetime();
                String errorType = request.getRequest_errortype();
                String price = request.getRequest_price();
                int partner_history_id = 1;

                // Tạo bảng lịch sử cho cộng tác viên
                partnerHistoryDAO = new PartnerHistoryDAO(context);
                PartnerHistory partnerHistory = new PartnerHistory (partner_history_id, serviceName, customerName, customerPhone, customerAddress, dateTime, errorType, price);
                partnerHistoryDAO.insert(partnerHistory);



                // VẪN CHƯA XÓA ĐƯỢC TẠI CHƯA CÓ ID REQUEST:
//                // Xóa dòng dữ liệu đã được click
//                fragmentRequest.partnerDelete(request);

                ///


            }
        });
    }

    @Override
    public int getItemCount() {
        return arrPartner.size();
    }

}
