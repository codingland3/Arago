package com.example.arago._USER;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.arago.R;

public class FormRequestSuaNuocActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_request_suanuoc);
    }

    public void clickBack(View view) {
        finish();
    }
}
