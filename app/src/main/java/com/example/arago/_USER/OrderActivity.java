package com.example.arago._USER;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arago.DAO.HistoryDAO;
import com.example.arago.R;
import com.example.arago._USER.Model.History;

public class OrderActivity extends AppCompatActivity {
    TextView txtClickButton, txtBillPrice;
    String JOBid="";
    HistoryDAO historyDAO;
    EditText edtName,edtPhone,edtAddress, edtTime, edtProblem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_order);
        anhxa();

        Intent intent = getIntent();
        final String price = intent.getStringExtra(JobDetails.PRICE);
        final String service_name = intent.getStringExtra(JobDetails.SERVICE_NAME);
        txtBillPrice.setText(price);

        txtClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = edtTime.getText().toString();

                historyDAO = new HistoryDAO(OrderActivity.this);
                History history = new History(service_name, time, price);
                historyDAO.insert(history);
                Toast.makeText(OrderActivity.this, "Đặt thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void anhxa() {
        txtBillPrice = (TextView) findViewById(R.id.bill_price);
        txtClickButton=(TextView)findViewById(R.id.tvClickCreatePartnerAccount);
        edtName=(EditText)findViewById(R.id.request_customer_name);
        edtPhone=(EditText)findViewById(R.id.request_customer_phone);
        edtAddress=(EditText)findViewById(R.id.request_customer_address);
        edtProblem=(EditText)findViewById(R.id.request_problem);
        edtTime = (EditText) findViewById(R.id.request_time);
    }
}
