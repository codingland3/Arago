package com.example.arago.USER;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arago.R;
import com.example.arago.USER.Adapter.User_GridAdapter;

public class UserActivity extends AppCompatActivity {

    GridView gridView;
    User_GridAdapter user_gridAdapter;

    String[] values = {
            "Đặt lại mật khẩu",
            "Cập nhật địa chỉ",
            "Quản lý dịch vụ",

    };

    int[] images = {
            R.drawable.ic_key,
            R.drawable.ic_address_update,
            R.drawable.ic_build,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        // Tham chiếu và đưa dữ liệu lên gridview
        gridView = (GridView) findViewById(R.id.gv_user_information);
        user_gridAdapter = new User_GridAdapter(this, values, images);
        gridView.setAdapter(user_gridAdapter);
    }

    public void clickBack(View view) {
        finish();
    }

    public void clickLogout(View view) {
        Toast.makeText(this, "Đăng xuất tài khoản", Toast.LENGTH_SHORT).show();
    }

    public void clickChangeAvatar(View view) {
        Toast.makeText(this, "Đổi ảnh đại diện", Toast.LENGTH_SHORT).show();
    }
}
