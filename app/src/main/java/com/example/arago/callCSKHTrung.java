package com.example.arago;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class callCSKHTrung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_cskhtrung);
        Intent intent=getIntent();
    }

    public void clickBack(View view) {
        finish();
    }
}
