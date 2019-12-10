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
//import com.example.arago.Model.Service;
//import com.example.arago.Model.Service;
//import com.example.arago.NonUI;
//import com.example.arago._USER.Fragment.FragmentHistory;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ServiceDAO {
//
//    private DatabaseReference mDatabase;
//    NonUI nonUI;
//    Context context;
//    String key;
//    FragmentHistory fragmentHistory;
//    public static List<Service> list = new ArrayList<Service>();
//    public ServiceDAO(Context context) {
//        this.mDatabase = FirebaseDatabase.getInstance().getReference("Service");
//        this.context = context;
//        this.nonUI = new NonUI(context);
//    }
//
//    public ServiceDAO(Context context, FragmentHistory fr) {
//        this.mDatabase = FirebaseDatabase.getInstance().getReference("Service");
//        this.context = context;
//        this.nonUI = new NonUI(context);
//        this.fragmentHistory = fr;
//    }
//
//    public List<Service> getAll() {
//
//        ValueEventListener listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Service object and use the values to update the UI
//                list.clear();
//                for (DataSnapshot data:dataSnapshot.getChildren()){
//                    Service item = data.getValue(Service.class);
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
//    public void insert(Service item) {
//        key = mDatabase.push().getKey();
//        mDatabase.child(key).setValue(item)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        nonUI.toast("Đăng ký thành công");
//                        Log.d("insert","Đăng ký thành công");
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                nonUI.toast("Đăng ký không thành công");
//                Log.d("insert","Đăng ký không thành công");
//            }
//        });
//    }
//
//    public void delete(final Service item) {
//
//        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot data : dataSnapshot.getChildren()) {
//
//                    if (data.child("service_id").getValue(String.class).equalsIgnoreCase(String.valueOf(item.getService_id()))){
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
//    }
//
//
//    public void getIdToEqual(final Service item) {
//
//        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot data : dataSnapshot.getChildren()) {
//
//                    if (data.child("service_id").getValue(String.class).equalsIgnoreCase(String.valueOf(item.getService_id()))){
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
//    }
//}
