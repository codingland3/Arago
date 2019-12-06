//package com.example.arago.USER;
//
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.arago.R;
//import com.example.arago.USER.Adapter.Services_RecyclerViewAdapter;
//import com.example.arago.Model.Service;
//
//import java.util.ArrayList;
//
//public class ServicesActivity extends AppCompatActivity {
//
//    private RecyclerView mRecyclerView;
//    private Services_RecyclerViewAdapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
//    private ArrayList<Service> services;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_services);
//
//        // Danh sách dịch vụ:
//        createServiceList();
//        // Hiển thị recyclerView:
//        buildRecyclerView();
//    }
//
//    public void createServiceList(){
//        services = new ArrayList<>();
//        services.add(new Service(R.drawable.fix_icon, "Sửa ống nước, thiết bị nhà vệ sinh","Giá không cố định"));
//        services.add(new Service(R.drawable.fix_icon, "Sửa máy bơm nước", "Giá không cố định"));
//        services.add(new Service(R.drawable.fix_icon, "Tư vấn thiết kế, cung cấp lắp đặt hệ thống điện nước", "Giá không cố định"));
//    }
//
//    public void buildRecyclerView(){
//        mRecyclerView = findViewById(R.id.rv_services);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(this);
//        mAdapter = new Services_RecyclerViewAdapter(services);
//
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
//
//        // Sự kiện click chọn 1 dòng trên danh sách:
//        mAdapter.setOnItemClickListener(new Services_RecyclerViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//
//                if (position == 0) {
//
//                }
//
//                //
//                changItem(position, "Clicked");
//                mAdapter.notifyItemChanged(position);
//            }
//        });
//    }
//
//    //
//    public void changItem(int position, String text){
//        services.get(position).changTitle(text);
//    }
//
//    public void clickBack(View view) {
//        finish();
//    }
//}
