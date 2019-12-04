package com.example.arago.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.arago.ADMIN.CustomerFragment;
import com.example.arago.NonUI;
import com.example.arago.USER.Model.Customer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private DatabaseReference mDatabase;

    NonUI nonUI;
    Context context;
    String key;
    CustomerFragment customerFragment;

    public CustomerDAO(Context context) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("Customer");
        this.context = context;
        this.nonUI = new NonUI(context);
    }

    public CustomerDAO(Context context, CustomerFragment fr) {
        this.mDatabase = FirebaseDatabase.getInstance().getReference("Customer");
        this.context = context;
        this.nonUI = new NonUI(context);
        this.customerFragment = fr;
    }

    public List<Customer> getAll() {
        final List<Customer> list = new ArrayList<Customer>();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get NguoiDung object and use the values to update the UI
                list.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Customer item = data.getValue(Customer.class);
                    list.add(item);
                }
//                NguoiDungFragment.capnhatLV(); // Bên fragment 2
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                nonUI.toast("Không kết nối Database");
            }
        };
        mDatabase.addValueEventListener(listener);
        return list;
    }

    public void insert(Customer item) {
        key = mDatabase.push().getKey();
        mDatabase.child(key).setValue(item)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        nonUI.toast("Thêm thành công");
                        Log.d("insert","Thêm thành công");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                nonUI.toast("Thêm không thành công");
                Log.d("insert","Thêm không thành công");
            }
        });
    }

    public void update(final Customer item) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("customer_email").getValue(String.class).equals(item.getCustomer_email())){
                        key = data.getKey();
                        mDatabase.child(key).setValue(item)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        nonUI.toast("Sửa thành công");
                                        Log.d("update","Sửa thành công");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        nonUI.toast("Sửa không thành công");
                                        Log.d("update","Sửa không thành công");
                                    }
                                });
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void delete(final Customer item) {

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data : dataSnapshot.getChildren()) {

                    if (data.child("customer_email").getValue(String.class).equalsIgnoreCase(item.getCustomer_email())){
                        key = data.getKey();

                        Log.d("getKey", "onCreate: key :" + key);

                        mDatabase.child(key).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        nonUI.toast("Xóa thành công");
                                        Log.d("delete","Xóa thành công");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        nonUI.toast("Xóa không thành công");
                                        Log.d("delete","Xóa không thành công");
                                    }
                                });
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

