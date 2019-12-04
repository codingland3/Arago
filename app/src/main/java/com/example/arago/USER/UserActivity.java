package com.example.arago.USER;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.arago.GetStartedActivity;
import com.example.arago.MainActivity;
import com.example.arago.R;
import com.example.arago.USER.Adapter.User_GridAdapter;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {

    GridView gridView;
    User_GridAdapter user_gridAdapter;
    Integer change_password = 0, update_address = 1, service_manager = 2;
    EditText change_pass_old_pass, change_pass_new_pass, change_pass_new_pass_confirm;
    private FirebaseAuth auth;
    View v;

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

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // <Code>
                if (position == change_password){
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(UserActivity.this);
                    // Gắn layout vào view
                    v = getLayoutInflater().inflate(R.layout.change_password_layout, null);
                    // Ánh xạ
                    change_pass_old_pass = v.findViewById(R.id.change_pass_old_pass);
                    change_pass_new_pass = v.findViewById(R.id.change_pass_new_pass);
                    change_pass_new_pass_confirm = v.findViewById(R.id.change_pass_new_pass_confirm);
                    clickChangePassword(view);
                    alertDialog.setView(v);
                    AlertDialog dialog = alertDialog.create();
                    dialog.show();

                } else if (position == update_address) {
                    Toast.makeText(UserActivity.this, "Cập nhật địa chỉ", Toast.LENGTH_SHORT).show();
                } else if (position == service_manager){
                    Toast.makeText(UserActivity.this, "Quản lý dịch vụ", Toast.LENGTH_SHORT).show();
                }
                // </Code>
            }
        });
    }

    public void clickBack(View view) {
        finish();
    }

    public void clickLogout(View view) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        Intent login = new Intent(UserActivity.this, GetStartedActivity.class);
        startActivity(login);
        finish();
    }

    public void clickChangeAvatar(View view) {
        Toast.makeText(this, "Đổi ảnh đại diện", Toast.LENGTH_SHORT).show();
    }

    public void clickChangePassword(View view) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (change_pass_new_pass.getText().toString().equals(change_pass_new_pass.getText().toString())) {
            try {
                final String old_pass = change_pass_old_pass.getText().toString().trim();
                final String new_pass = change_pass_new_pass.getText().toString().trim();
                final String new_pass_confirm = change_pass_new_pass_confirm.getText().toString().trim();

                if (old_pass.isEmpty()){
                    change_pass_old_pass.setError("Vui lòng nhập đầy đủ thông tin");
                    change_pass_old_pass.requestFocus();
                    return;
                }
                else if (change_pass_old_pass.getText().toString().trim().length() < 6) {
                    change_pass_old_pass.setError("Mật khẩu quá ngắn, độ dài ít nhất 6 kí tự");
                }

                else if (new_pass.isEmpty()){
                    change_pass_new_pass.setError("Vui lòng nhập đầy đủ thông tin");
                    change_pass_new_pass.requestFocus();
                    return;
                }

                else if (change_pass_new_pass.getText().toString().trim().length() < 6) {
                    change_pass_new_pass.setError("Mật khẩu quá ngắn, độ dài ít nhất 6 kí tự");
                }

                else if (new_pass_confirm.isEmpty()){
                    change_pass_new_pass_confirm.setError("Vui lòng nhập đầy đủ thông tin");
                    change_pass_new_pass_confirm.requestFocus();
                    return;
                }

                else if (change_pass_new_pass_confirm.getText().toString().trim().length() < 6) {
                    change_pass_new_pass_confirm.setError("Mật khẩu quá ngắn, độ dài ít nhất 6 kí tự");
                }

                else {
                    user.updatePassword(change_pass_new_pass.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(UserActivity.this, "Password is updated, sign in with new password!", Toast.LENGTH_SHORT).show();
                                        auth.signOut();
                                        finish();
                                    } else {
                                        Toast.makeText(UserActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } return;
            } catch (NullPointerException npe) {
                Toast.makeText(UserActivity.this, "Lỗi NullPointerException", Toast.LENGTH_SHORT).show();
            }
        } else {
            change_pass_new_pass_confirm.setError("Xác nhận không trùng khớp");
        }
    }
}
