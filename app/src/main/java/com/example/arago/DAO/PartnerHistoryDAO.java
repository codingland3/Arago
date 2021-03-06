package com.example.arago.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.arago.Model.Partner;
import com.example.arago.Model.PartnerHistory;
import com.example.arago.NonUI;
import com.example.arago._ADMIN.Fragment.PartnerFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PartnerHistoryDAO {

    private DatabaseReference mDatabase;
    NonUI nonUI;
    Context context;
    String key;
    PartnerFragment partnerFragment;
    public static List<PartnerHistory> list = new ArrayList<PartnerHistory>();
    public PartnerHistoryDAO(Context context) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("HistoryPartner");
        this.context = context;
        this.nonUI = new NonUI(context);
    }

    public PartnerHistoryDAO(Context context, PartnerFragment fr) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("HistoryPartner");
        this.context = context;
        this.nonUI = new NonUI(context);
        this.partnerFragment = fr;
    }

    public PartnerHistoryDAO() {
    }

    public List<PartnerHistory> getAll() {
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get History Partner object and use the values to update the UI
                list.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    PartnerHistory item = data.getValue(PartnerHistory.class);
                    list.add(item);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                nonUI.toast("Không kết nối Database");
            }
        };
        mDatabase.addValueEventListener(listener);
        return list;
    }

    public void insert(PartnerHistory item) {
        key = mDatabase.push().getKey();
        mDatabase.child(key).setValue(item)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        nonUI.toast("Đăng ký thành công");
                        Log.d("insert","Đăng ký thành công");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                nonUI.toast("Đăng ký không thành công");
                Log.d("insert","Đăng ký không thành công");
            }
        });
    }
}
