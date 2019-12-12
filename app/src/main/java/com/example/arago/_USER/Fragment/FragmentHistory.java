package com.example.arago._USER.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.DAO.HistoryDAO;
import com.example.arago.R;
import com.example.arago._USER.Adapter.History_Adapter;
import com.example.arago._USER.Model.History;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment {
    public static List<History> historyList ;
    RecyclerView rv_history;
    History_Adapter adapter;
    HistoryDAO historyDAO;
    DatabaseReference mDatabase;
    LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.layout_history, container, false);
        rv_history = (RecyclerView) view.findViewById(R.id.rv_history);

        historyDAO = new HistoryDAO(getActivity());
        historyList = new ArrayList<History>();
        // Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("History");
        // Get All History
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get History object and use the values to update the UI
                historyList.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    History item = data.getValue(History.class);
                    historyList.add(item);
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

        adapter = new History_Adapter(getActivity(),historyList,this);
        rv_history.setAdapter(adapter);
        return view;
    }

    public void recyclerViewUpdate(){
        adapter.notifyItemInserted(historyDAO.list.size());
        adapter.notifyDataSetChanged();
    }
}
