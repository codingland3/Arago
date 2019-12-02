package com.example.arago.USER.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.arago.R;
import com.example.arago.USER.Adapter.Main_CardViewAdapter;
import com.example.arago.USER.Adapter.Main_GridAdapter;
import com.example.arago.USER.Model.Event;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    GridView gridView;
    Main_GridAdapter gridAdapter;

    ViewPager viewPager;
    Main_CardViewAdapter cardViewAdapter;
    List<Event> events;

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


        // Tham chiếu và đưa dữ liệu lên gridview
        gridView = (GridView) view.findViewById(R.id.main_gv_services);
        gridAdapter = new Main_GridAdapter(getContext(), values, images);
        gridView.setAdapter(gridAdapter);

        //
        events = new ArrayList<>();
        events.add(new Event(R.drawable.handphone, "Khuyến mãi 50% phí sử dụng dịch vụ", "Mừng ra mắt ứng dụng, Arago khuyến mãi 50% phí sử dụng tất cả các dịch vụ", "Xem chi tiết"));
        events.add(new Event(R.drawable.handphone, "Khuyến mãi 50% phí sử dụng dịch vụ", "Mừng ra mắt ứng dụng, Arago khuyến mãi 50% phí sử dụng tất cả các dịch vụ", "Xem chi tiết"));
        events.add(new Event(R.drawable.handphone, "Khuyến mãi 50% phí sử dụng dịch vụ", "Mừng ra mắt ứng dụng, Arago khuyến mãi 50% phí sử dụng tất cả các dịch vụ", "Xem chi tiết"));

        cardViewAdapter = new Main_CardViewAdapter(events, getContext());
        viewPager = view.findViewById(R.id.main_viewPager_event);
        viewPager.setAdapter(cardViewAdapter);
        viewPager.setPadding(130,0, 130, 0);
        return view;

    }
}
