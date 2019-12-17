package com.example.arago._ADMIN.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.arago.DAO.CustomerDAO;
import com.example.arago.DAO.PartnerDAO;
import com.example.arago.DAO.PartnerHistoryDAO;
import com.example.arago.Model.Customer;
import com.example.arago.Model.Partner;
import com.example.arago.Model.PartnerHistory;
import com.example.arago.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentStatistic extends Fragment {

    DatabaseReference mData;
    TextView tvTongTien, tvLaisuat, tvCustomer, tvPartner;
    GridView gridView;
    double tongTien = 0;
    double tienLai;
    int soluongPartner = 0, soluongCustomer = 0;
    List<PartnerHistory> partnerHistoryList = new ArrayList<>();
    List<Partner> partnerList = new ArrayList<>();
    List<Customer> customerList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_statistic, container, false);
        // Lấy thông tin từ firebase gán vào list:
        PartnerHistoryDAO partnerHistoryDAO = new PartnerHistoryDAO(getContext());
        partnerHistoryList = partnerHistoryDAO.getAll();

        PartnerDAO partnerDAO = new PartnerDAO(getContext());
        partnerList = partnerDAO.getAll();

        CustomerDAO customerDAO =  new CustomerDAO(getContext());
        customerList = customerDAO.getAll();

        tvTongTien = view.findViewById(R.id.tv_statistic_tongtien);
        tvCustomer = view.findViewById(R.id.tv_statistic_customer);
        tvLaisuat = view.findViewById(R.id.tv_statistic_tienlai);
        tvPartner = view.findViewById(R.id.tv_statistic_partner);


        mData = FirebaseDatabase.getInstance().getReference("HistoryPartner");
        mData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    for (int i = 0; i <= partnerHistoryList.size() - 1; i++) {
                        if (partnerHistoryList.size() > 0) {
                            // Cộng tiền:
                            tongTien += Double.parseDouble(partnerHistoryList.get(i).getHistory_price());
                            // Tính lãi:
                            tienLai = tongTien * 10/100;
                        }
                    }

                    for (int j = 0; j <= partnerList.size() ; j++) {
                        if (partnerList.size() > 0) {
                            // Tổng cộng tác viên:
                            soluongPartner = j;
                        }
                    }

                    for (int k = 0; k <= customerList.size(); k++) {
                        if (customerList.size() > 0) {
                            // Tổng cộng người dùng:
                            soluongCustomer = k;
                        }
                    }

                    tvTongTien.setText(tongTien + " VND");
                    tvLaisuat.setText(tienLai + "\nVND");
                    tvPartner.setText(soluongPartner + "");
                    tvCustomer.setText(soluongCustomer + "");

                } catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Dữ liệu trống", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }
}
