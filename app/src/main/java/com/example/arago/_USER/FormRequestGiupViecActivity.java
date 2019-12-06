package com.example.arago._USER;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arago.R;

public class FormRequestGiupViecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_request_giupviec);
    }

    public void clickBack(View view) {
        finish();

    }
}
