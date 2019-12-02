package com.example.arago;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        changeStatusBar(getWindow());
        init();
    }

    private void init() {
        username = findViewById(R.id.username_input);
        password = findViewById(R.id.password_input);
    }

    public void changeStatusBar(Window window){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.contentStatusBar));
        }
    }

    public void clickLogin(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        // Lấy tài khoản của người dùng gán vào dbuser
        String dbuser_username = "nguoidung@gmail.com";
        String dbuser_password = "123";
        // Lấy tài khoản partner gán vào dbuser
        String dbpartner_username = "congtacvien@gmail.com";
        String dbpartner_password = "123";

        // Điều kiện
        if (user.equals("arago@gmail.com") && pass.equals("arago123")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập với tư cách admin", Toast.LENGTH_SHORT).show();
        }else if (user.equals(dbuser_username) && pass.equals(dbuser_password)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập với tư cách người dùng", Toast.LENGTH_SHORT).show();
        } else if (user.equals(dbpartner_username) && pass.equals(dbpartner_password)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập với tư cách cộng tác viên", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "Nhập sai tài khoản", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickBackToGetStarted(View view) {
        Intent intent = new Intent(LoginActivity.this, GetStartedActivity.class);
        startActivity(intent);
    }

    public void clickToRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void clickToForgotPassword(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    public void clickLoginWithFB(View view) {
        Toast.makeText(LoginActivity.this, "Đăng nhập bằng tài khoản Facebook", Toast.LENGTH_SHORT).show();
    }

    public void clickLoginWithGG(View view) {
        Toast.makeText(LoginActivity.this, "Đăng nhập bằng tài khoản Google", Toast.LENGTH_SHORT).show();
    }

}
