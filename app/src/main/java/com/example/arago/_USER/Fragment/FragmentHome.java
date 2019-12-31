package com.example.arago._USER.Fragment;

import android.content.Intent;
import android.net.Uri;
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

import com.bumptech.glide.Glide;
import com.example.arago.DAO.CustomerDAO;
import com.example.arago.Model.Customer;
import com.example.arago.R;
import com.example.arago._USER.Adapter.Main_CardViewAdapter;
import com.example.arago._USER.Adapter.Main_GridAdapter;
import com.example.arago.Model.Event;
import com.example.arago._USER.JobList;
import com.example.arago._USER.UserActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentHome extends Fragment {

    GridView gridServices;
    Main_GridAdapter main_gridAdapter;
    Integer suanuoc = 0, dienlanh = 1, suadien = 2, xaydung = 3, it = 4, giupviec = 5;

    ViewPager viewPager;
    Main_CardViewAdapter cardViewAdapter;
    List<Event> events;
    TextView tvHello;
    CircleImageView circleImageView;

    private FirebaseAuth auth;

    public static final String ID = "id";

    String[] values = {
            "Thợ sửa nước",
            "Thợ điện lạnh",
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
        tvHello=view.findViewById(R.id.textViewUser);
        circleImageView=view.findViewById(R.id.profileCircleImageView);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
            }
    });

//        MainActivity activity = (MainActivity) getActivity();
//
//        Bundle hm = activity.getDataOfFacebookFromMainActivity();
//        String name = hm.getString("name");
//        String surname = hm.getString("surname");
//        String avatar = hm.getString("imgUrl");
//
//        tvHello.setText("Xin chào " + name);

//        new FragmentHome().downloadImage(circleImageView).execute(imageUrl);

        List<Customer> customers = new ArrayList<Customer>();
        CustomerDAO customerDAO = new CustomerDAO(getContext());
        customers = customerDAO.getAll();

        // Chổ này lấy tài tài khoản hiện tại, so sánh với email bên trong database để lấy thông tin cần thiết ra bên ngoài
        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        String email = currentUser.getEmail();
        String indexName = "";
        for (int j = 0; j <= customers.size() - 1; j++) {
            if (customers.size() > 0) {
                if (email.equals(customers.get(j).getCustomer_email())) {
                    String[] name = customers.get(j).getCustomer_name().toUpperCase().split("\\ ");

                    if (name.length == 4){
                        indexName = name[3];
                        break;
                    }
                    if (name.length == 3){
                        indexName = name[2];
                        break;
                    }
                    if (name.length == 2){
                        indexName = name[1];
                        break;
                    }
                    else {
                        indexName = name[0];
                        break;
                    }
                }
            }
        }
        tvHello.setText("XIN CHÀO " + indexName+"!");
        tvHello.setTextSize(20);


        // Tham chiếu và đưa dữ liệu lên gridview
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            Uri personPhoto = acct.getPhotoUrl();

            tvHello.setText("Xin chào " + personName);
            Glide.with(this).load(String.valueOf(personPhoto)).into(circleImageView);
        }

        gridServices = (GridView) view.findViewById(R.id.main_gv_services);
        main_gridAdapter = new Main_GridAdapter(getContext(), values, images);
        gridServices.setAdapter(main_gridAdapter);

        gridServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // <Code>
                if (position == suanuoc){
                    Intent intent = new Intent(getContext(), JobList.class);
                    intent.putExtra(ID, "01");
                    startActivity(intent);
                    Toast.makeText(getContext(), "Sửa nước", Toast.LENGTH_SHORT).show();
                } else if (position == dienlanh) {
                    Intent intent = new Intent(getContext(), JobList.class);
                    intent.putExtra(ID, "02");
                    startActivity(intent);
                    Toast.makeText(getContext(), "Điện lạnh", Toast.LENGTH_SHORT).show();
                } else if (position == suadien){
                    Intent intent = new Intent(getContext(), JobList.class);
                    intent.putExtra(ID, "03");
                    startActivity(intent);
                    Toast.makeText(getContext(), "Sửa điện", Toast.LENGTH_SHORT).show();
                } else if (position == xaydung){
                    Intent intent = new Intent(getContext(), JobList.class);
                    intent.putExtra(ID, "04");
                    startActivity(intent);
                    Toast.makeText(getContext(), "Xây dựng", Toast.LENGTH_SHORT).show();
                } else if (position == it){
                    Intent intent = new Intent(getContext(), JobList.class);
                    intent.putExtra(ID, "05");
                    startActivity(intent);
                    Toast.makeText(getContext(), "IT", Toast.LENGTH_SHORT).show();
                } else if (position == giupviec){
                    Intent intent = new Intent(getContext(), JobList.class);
                    intent.putExtra(ID, "06");
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
