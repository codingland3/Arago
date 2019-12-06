//package com.example.arago.DAO;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
//import com.example.arago.NonUI;
//import com.example.arago.USER.Fragment.FragmentHistory;
//import com.example.arago.Model.Partner;
//import com.example.arago.Model.Request;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//public class RequestDAO {
//
//    private DatabaseReference mDatabase;
//    NonUI nonUI;
//    Context context;
//    String key;
//    FragmentHistory fragmentHistory;
//    public static List<Request> list = new ArrayList<Request>();
//    public RequestDAO(Context context) {
//        this.mDatabase = FirebaseDatabase.getInstance().getReference("Request");
//        this.context = context;
//        this.nonUI = new NonUI(context);
//    }
//
//    public RequestDAO(Context context, FragmentHistory fr) {
//        this.mDatabase = FirebaseDatabase.getInstance().getReference("Request");
//        this.context = context;
//        this.nonUI = new NonUI(context);
//        this.fragmentHistory = fr;
//    }
//
//    public List<Request> getAll() {
//
//        ValueEventListener listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Request object and use the values to update the UI
//                list.clear();
//                for (DataSnapshot data:dataSnapshot.getChildren()){
//                    Request item = data.getValue(Request.class);
//                    list.add(item);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                nonUI.toast("Không kết nối Database");
//            }
//        };
//        mDatabase.addValueEventListener(listener);
//        return list;
//    }
//
//    public void insert(Request item) {
//        key = mDatabase.push().getKey();
//        mDatabase.child(key).setValue(item)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        nonUI.toast("Thêm thành công");
//                        Log.d("insert","Thêm thành công");
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                nonUI.toast("Thêm không thành công");
//                Log.d("insert","Thêm không thành công");
//            }
//        });
//    }
//
//    public void delete(final Request item) {
//
//        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot data : dataSnapshot.getChildren()) {
//
//                    if (data.child("request_email").getValue(String.class).equalsIgnoreCase(item.getRequest_id())){
//                        key = data.getKey();
//
//                        Log.d("getKey", "onCreate: key :" + key);
//
//                        mDatabase.child(key).removeValue()
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        nonUI.toast("Xóa thành công");
//                                        Log.d("delete","Xóa thành công");
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        nonUI.toast("Xóa không thành công");
//                                        Log.d("delete","Xóa không thành công");
//                                    }
//                                });
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });
//    }}
