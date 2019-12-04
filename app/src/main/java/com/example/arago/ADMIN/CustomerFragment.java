package com.example.arago.ADMIN;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.ADMIN.Adapter.CustomerAdapter;
import com.example.arago.DAO.CustomerDAO;
import com.example.arago.R;
import com.example.arago.USER.Model.Customer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerFragment extends Fragment {
    public static List<Customer> customerList ;
    RecyclerView rv_customer;
    CustomerAdapter adapter;
    CustomerDAO customerDAO;
    DatabaseReference mDatabase;
    LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.layout_customer, container, false);

        rv_customer = view.findViewById(R.id.rv_customer);
        customerDAO = new CustomerDAO(getActivity());
        customerList = new ArrayList<Customer>();

        mDatabase = FirebaseDatabase.getInstance().getReference("Customer");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Customer item = data.getValue(Customer.class);
                    customerList.add(item);
                }
                listViewUpdate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabase.addValueEventListener(listener);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rv_customer.setLayoutManager(mLayoutManager);

        adapter=new CustomerAdapter(getActivity(),customerList,this);
        rv_customer.setAdapter(adapter);
        return view;
    }

    public void customerDelete(Customer c){
        customerDAO.delete(c);
        listViewUpdate();
    }

    public void listViewUpdate(){
        adapter.notifyItemInserted(customerList.size());
        adapter.notifyDataSetChanged();
    }
}
