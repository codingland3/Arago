package com.example.arago._PARTNER.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.DAO.PartnerHistoryDAO;
import com.example.arago.Model.PartnerHistory;
import com.example.arago.R;
import com.example.arago._PARTNER.Adapter.PartnerHistory_Adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment {
    public static List<PartnerHistory> partnerHistoryList ;
    RecyclerView rv_history;
    PartnerHistory_Adapter adapter;
    PartnerHistoryDAO partnerHistoryDAO;
    DatabaseReference mDatabase;
    LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.layout_partner_history, container, false);

        rv_history = (RecyclerView) view.findViewById(R.id.rv_partner_history);

        partnerHistoryDAO = new PartnerHistoryDAO(getActivity());
        partnerHistoryList = new ArrayList<PartnerHistory>();
        // Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("HistoryPartner");
        // Get All History
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get History object and use the values to update the UI
                partnerHistoryList.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    PartnerHistory item = data.getValue(PartnerHistory.class);
                    partnerHistoryList.add(item);
                }
                recyclerViewUpdate();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabase.addValueEventListener(listener);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rv_history.setLayoutManager(mLayoutManager);

        adapter = new PartnerHistory_Adapter(getActivity(),partnerHistoryList,this);
        rv_history.setAdapter(adapter);
        return view;
    }

    public void recyclerViewUpdate(){
        adapter.notifyItemInserted(partnerHistoryDAO.list.size());
        adapter.notifyDataSetChanged();
    }
}
