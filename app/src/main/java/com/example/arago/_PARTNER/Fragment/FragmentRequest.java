package com.example.arago._PARTNER.Fragment;

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

import com.example.arago.DAO.RequestDAO;
import com.example.arago.Model.Request;
import com.example.arago.R;
import com.example.arago._USER.Adapter.RequestAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentRequest extends Fragment {
    public static List<Request> requestList;
    RecyclerView rv_Request;
    RequestAdapter adapter;
    RequestDAO requestDAO;
    DatabaseReference mDatabase;
    LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.layout_request, container, false);

        rv_Request = (RecyclerView) view.findViewById(R.id.rv_request);

        requestDAO = new RequestDAO(getActivity());
        requestList = new ArrayList<Request>();

        mDatabase = FirebaseDatabase.getInstance().getReference("Request");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Request item = data.getValue(Request.class);
                    requestList.add(item);
                }
                listViewUpdate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "err: " + databaseError, Toast.LENGTH_SHORT).show();
            }
        };

        mDatabase.addValueEventListener(listener);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rv_Request.setLayoutManager(mLayoutManager);

        adapter=new RequestAdapter(getActivity(),requestList,this);
        rv_Request.setAdapter(adapter);

        return view;
    }

    public void partnerDelete(Request c){
        requestDAO.delete(c);
        listViewUpdate();
    }

    public void listViewUpdate(){
        adapter.notifyItemInserted(requestList.size());
        adapter.notifyDataSetChanged();
    }
}
