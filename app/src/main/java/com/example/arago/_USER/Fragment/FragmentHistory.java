package com.example.arago._USER.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.DAO.CustomerDAO;
import com.example.arago.DAO.HistoryDAO;
import com.example.arago.Model.Customer;
import com.example.arago.R;
import com.example.arago._USER.Adapter.History_Adapter;
import com.example.arago._USER.Model.History;
import com.example.arago._USER.OrderActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment {
    RecyclerView rv_history;
    History_Adapter adapter;
    DatabaseReference mDatabase;
    LinearLayoutManager mLayoutManager;
    private FirebaseAuth auth;
    String current_customer_id = "";
    String date, name,price;
    HistoryDAO historyDAO;
    History history;
    List<History> historyList2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.layout_history, container, false);
        rv_history = (RecyclerView) view.findViewById(R.id.rv_history);

        // Lấy lịch sử của người dùng hiện tại.
        // ĐẦU TIÊN LÀ LẤY ĐƯỢC ID NGƯỜI DÙNG HIỆN TẠI, SAU ĐÓ LẤY TẤT CẢ LỊCH SỬ BỎ VÀO 1 LIST
        // XONG SO SÁNH ID NGƯỜI DÙNG HIỆN TẠI VỚI ID ĐÃ GÁN BÊN TRONG BẢNG LỊCH SỬ
        // NẾU KHỚP THÌ BỎ VÀO 1 LIST KHÁC RỒI SHOW LÊN
        List<Customer> customers = new ArrayList<Customer>();
        CustomerDAO customerDAO = new CustomerDAO(getActivity());
        customers = customerDAO.getAll();

        // Chổ này lấy tài tài khoản hiện tại, so sánh với email bên trong database
        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        String email = currentUser.getEmail();
        for (int j = 0; j <= customers.size() - 1; j++) {
            if (customers.size() > 0) {
                if (email.equals(customers.get(j).getCustomer_email())) {
                    current_customer_id = customers.get(j).getCustomer_id();
                }
            }
        }

        List<History> historyList = new ArrayList<History>();
        historyDAO = new HistoryDAO(getActivity());
        historyList = historyDAO.getAll();
        historyList2 = new ArrayList<History>();


        // Chổ này lấy id hiện tại, so sánh với id bên trong database
        auth = FirebaseAuth.getInstance();
        for (int j = 0; j <= historyList.size() - 1; j++) {
            if (historyList.size() > 0) {
                if (current_customer_id.equals(historyList.get(j).getCustomerID())) {
                    name = historyList.get(j).getName();
                    price = historyList.get(j).getPrice();
                    date = historyList.get(j).getDate();
                    history = new History();
                    history.setPrice(price);
                    history.setDate(date);
                    history.setName(name);
                    historyList2.add(history);
                }
            }
        }


        mLayoutManager = new LinearLayoutManager(getActivity());
        rv_history.setLayoutManager(mLayoutManager);

        adapter = new History_Adapter(getActivity(),historyList2,this);

        rv_history.setAdapter(adapter);
        recyclerViewUpdate();

        return view;
    }

    public void recyclerViewUpdate(){
        adapter.notifyItemInserted(historyDAO.list.size());
        adapter.notifyDataSetChanged();
    }
}
