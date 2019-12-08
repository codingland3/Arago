package com.example.arago._USER;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arago.DAO.RequestDAO;
import com.example.arago.Model.Request;
import com.example.arago.R;
import com.google.firebase.auth.FirebaseAuth;

public class FormRequestSuaDienActivity extends AppCompatActivity {

    EditText edName, edSdt, edAddress, edTime, edMota;
    TextView btnAccept;
    RequestDAO requestDAO;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_request_suadien);
        anhxa();

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int image = R.drawable.email;
                final String NameService = "Thợ Điện";
                final String Name = edName.getText().toString().trim();
                final String Phone = edSdt.getText().toString().trim();
                final String Address = edAddress.getText().toString().trim();
                final String dateTime = edTime.getText().toString().trim();
                final String Mota = edMota.getText().toString().trim();

                requestDAO = new RequestDAO(FormRequestSuaDienActivity.this);
                final Request request = new Request(image, Name, Phone, Address, dateTime, Mota, NameService);
                requestDAO.insert(request);
            }
        });


    }

    public void clickBack(View view) {
        finish();
    }

    public void anhxa(){
        edName = findViewById(R.id.request_customer_name);
        edSdt = findViewById(R.id.request_customer_phone);
        edAddress = findViewById(R.id.request_customer_address);
        edTime = findViewById(R.id.request_time);
        edMota = findViewById(R.id.request_problem);
        btnAccept = findViewById(R.id.tvClickCreatePartnerAccount);
    }
}
