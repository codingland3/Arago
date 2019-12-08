package com.example.arago._USER.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.arago.R;
import com.example.arago._USER.Adapter.Main_CardViewAdapter;
import com.example.arago._USER.Adapter.Main_GridAdapter;
import com.example.arago.Model.Event;
import com.example.arago._USER.FormRequestGiupViecActivity;
import com.example.arago._USER.FormRequestITActivity;
import com.example.arago._USER.FormRequestSuaDienActivity;
import com.example.arago._USER.FormRequestSuaNuocActivity;
import com.example.arago._USER.FormRequestTrangDiemActivity;
import com.example.arago._USER.FormRequestXayDungActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentHome extends Fragment {

    GridView gridServices;
    Main_GridAdapter main_gridAdapter;
    Integer suanuoc = 0, trangdiem = 1, suadien = 2, xaydung = 3, it = 4, giupviec = 5;

    ViewPager viewPager;
    Main_CardViewAdapter cardViewAdapter;
    List<Event> events;
    TextView tvHello;
    CircleImageView circleImageView;



    String[] values = {
            "Thợ sửa nước",
            "Thợ trang điểm",
            "Thợ sửa điện",
            "Thợ xây dựng",
            "Thợ IT",
            "Người giúp việc"
    };

    int[] images = {
            R.drawable.tho_sua_nuoc,
            R.drawable.tho_trang_diem,
            R.drawable.tho_sua_dien,
            R.drawable.tho_xay_dung,
            R.drawable.tho_it,
            R.drawable.nguoi_giup_viec
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        init();

//
//
//        MainActivity activity = (MainActivity) getActivity();
//
//        Bundle hm = activity.getDataOfFacebookFromMainActivity();
//        String name = hm.getString("name");
//        String surname = hm.getString("surname");
//        String avatar = hm.getString("imgUrl");
//
//        tvHello.setText("Xin chào " + name);

//        new FragmentHome().downloadImage(circleImageView).execute(imageUrl);


        // Tham chiếu và đưa dữ liệu lên gridview
        gridServices = (GridView) view.findViewById(R.id.main_gv_services);
        main_gridAdapter = new Main_GridAdapter(getContext(), values, images);
        gridServices.setAdapter(main_gridAdapter);

        gridServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // <Code>
                //
                if (position == suanuoc){
                    Intent intent = new Intent(getContext(), FormRequestSuaNuocActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(), "Sửa nước", Toast.LENGTH_SHORT).show();
                } else if (position == trangdiem) {
                    Intent intent = new Intent(getContext(), FormRequestTrangDiemActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(), "Trang điểm", Toast.LENGTH_SHORT).show();
                } else if (position == suadien){
                    Intent intent = new Intent(getContext(), FormRequestSuaDienActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(), "Sửa điện", Toast.LENGTH_SHORT).show();
                } else if (position == xaydung){
                    Intent intent = new Intent(getContext(), FormRequestXayDungActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(), "Xây dựng", Toast.LENGTH_SHORT).show();
                } else if (position == it){
                    Intent intent = new Intent(getContext(), FormRequestITActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(), "IT", Toast.LENGTH_SHORT).show();
                } else if (position == giupviec){
                    Intent intent = new Intent(getContext(), FormRequestGiupViecActivity.class);
                    startActivity(intent);
                    Toast.makeText(getContext(), "Giúp việc", Toast.LENGTH_SHORT).show();
                }
                // </Code>
            }
        });

        //
        events = new ArrayList<>();
        events.add(new Event(R.drawable.handphone, "Khuyến mãi 50% phí sử dụng dịch vụ", "Mừng ra mắt ứng dụng, Arago khuyến mãi 50% phí sử dụng tất cả các dịch vụ", "Xem chi tiết"));
        events.add(new Event(R.drawable.customer, "Tri ân khách hàng thân thiết của Arago", "Khuyến mãi 10% cho khách hàng đủ 10 lần sử dụng dịch vụ của Arago trong tháng", "Xem chi tiết"));
        events.add(new Event(R.drawable.hoamai, "Khuyến mãi 5% từ ngày 25 đến 28 Tết", "Nhân dịp lễ Tết, Arago khuyến mãi 5% cho khách hàng sử dụng dịch vụ xây dựng và giúp việc", "Xem chi tiết"));

        cardViewAdapter = new Main_CardViewAdapter(events, getContext());
        viewPager = view.findViewById(R.id.main_viewPager_event);
        viewPager.setAdapter(cardViewAdapter);
        viewPager.setPadding(130,0, 130, 0);
        return view;
    }

    public void init (){
        tvHello = getActivity().findViewById(R.id.textViewUser);
        circleImageView = getActivity().findViewById(R.id.profileCircleImageView);
    }
}