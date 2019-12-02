package com.example.arago;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GetStartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);

        changeStatusBar(getWindow());
    }

    public void changeStatusBar(Window window){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public void clickLogin(View view) {
        Intent intent = new Intent(GetStartedActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void clickToRegister(View view) {
        Intent intent = new Intent(GetStartedActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void clickLoginFB(View view) {
        Toast.makeText(GetStartedActivity.this, "Chức năng đang dược phát triển", Toast.LENGTH_SHORT).show();
    }
}
