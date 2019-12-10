package com.example.arago._USER;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arago.CartActivity;
import com.example.arago.Model.Order;
import com.example.arago.R;
import com.example.arago._USER.Database.Database;

public class OrderActivity extends AppCompatActivity {
    TextView txtcheck;
    String JOBid="";
    EditText edthovaten,edtSDT,edtAddress,edtyeucau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_fix);
        anhxa();
        txtcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addtocard(new Order(

                        edthovaten.getText().toString(),
                        edtSDT.getText().toString(),
                        edtAddress.getText().toString(),
                        edtyeucau.getText().toString()

                ));

                Toast.makeText(OrderActivity.this, "Add to cart", Toast.LENGTH_SHORT).show();
                OpenCartOrder();


            }
        });
    }

    private void OpenCartOrder() {
        Intent cart=new Intent(OrderActivity.this, CartActivity.class);
        startActivity(cart);
    }

    private void anhxa() {
        txtcheck=(TextView)findViewById(R.id.tvClickCreatePartnerAccount);
        edthovaten=(EditText)findViewById(R.id.request_customer_name);
        edtSDT=(EditText)findViewById(R.id.request_customer_phone);
        edtAddress=(EditText)findViewById(R.id.request_customer_address);
        edtyeucau=(EditText)findViewById(R.id.request_problem);
    }
}
