package com.example.arago._USER;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.arago.DAO.CustomerDAO;
import com.example.arago.DAO.HistoryDAO;
import com.example.arago.DAO.PartnerDAO;
import com.example.arago.DAO.RequestDAO;
import com.example.arago.Model.Customer;
import com.example.arago.Model.Partner;
import com.example.arago.Model.Request;
import com.example.arago.R;
import com.example.arago._USER.Model.History;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    TextView tvClickOrderService, txtBillPrice;
    HistoryDAO historyDAO;
    RequestDAO requestDAO;
    EditText edtName,edtPhone,edtAddress, edtTime, edtProblem;
    TextView tvDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    static final String TAG="QLSV";
    private FirebaseAuth auth;
    String current_customer_id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_order);
        anhxa();

        Intent intent = getIntent();
        final String price = intent.getStringExtra(JobDetails.PRICE);
        final String service_name = intent.getStringExtra(JobDetails.SERVICE_NAME);
        txtBillPrice.setText(price);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog= new DatePickerDialog(
                        OrderActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                Log.d(TAG,"onDateSet:mm/dd/yyyy: "+dayOfMonth+"/"+month+"/"+year);
                String date = "   "+dayOfMonth +"/"+month+"/"+year;
                tvDate.setText(date);
            }
        } ;


        tvClickOrderService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get id current user
                // ĐẦU TIÊN SẼ LẤY THÔNG TIN NGƯỜI DÙNG HIỆN TẠI - SAU ĐÓ SO SÁNH EMAIL VỚI THÔNG TIN TRÊN FIREBASE
                // NẾU THÔNG TIN NÀO KHỚP SẼ LẤY ID CỦA NGƯỜI ĐÓ VỀ VÀ GÁN VÀO BIẾN customer_id
                List<Customer> customers = new ArrayList<Customer>();
                CustomerDAO customerDAO = new CustomerDAO(OrderActivity.this);
                customers = customerDAO.getAll();

                // Chổ này lấy tài tài khoản hiện tại, so sánh với email bên trong database
                auth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = auth.getCurrentUser();
                String email = currentUser.getEmail();
                for (int j = 0; j <= customers.size() - 1; j++) {
                    if (customers.size() > 0) {
                        if (email.equals(customers.get(j).getCustomer_email())) {
                            current_customer_id = customers.get(j).getCustomer_id();
                        }
                    }
                }


                String time = tvDate.getText().toString();

                String request_customer_name = edtName.getText().toString();
                String request_customer_phone = edtPhone.getText().toString();
                String request_customer_address = edtAddress.getText().toString();
                String request_datetime = tvDate.getText().toString();
                String request_errortype = edtProblem.getText().toString();
                String request_service_name = service_name;
                String request_price = price;
                int request_id = 1;

                if (request_customer_name.isEmpty()) {
                    edtName.setError("Không được bỏ trống trường này");
                    edtName.requestFocus();
                    return;
                }

                if (request_customer_phone.isEmpty()) {
                    edtPhone.setError("Không được bỏ trống trường này");
                    edtPhone.requestFocus();
                    return;
                }

                if (request_customer_phone.length() != 10) {
                    edtPhone.setError("Nhập sai trường này");
                    edtPhone.requestFocus();
                    return;
                }

                if (request_customer_address.isEmpty()) {
                    edtAddress.setError("Không được bỏ trống trường này");
                    edtAddress.requestFocus();
                    return;
                }

                if (request_customer_address.length() <= 10) {
                    edtAddress.setError("Nhập sai trường này");
                    edtAddress.requestFocus();
                    return;
                }

                if (request_datetime.isEmpty()) {
                    tvDate.setError("Không được bỏ trống trường này");
                    tvDate.requestFocus();
                    return;
                }

                // Tạo bảng request cho cộng tác viên
                requestDAO = new RequestDAO(OrderActivity.this);
                Request request = new Request(request_id, request_customer_name, request_customer_phone, request_customer_address, request_datetime, request_errortype, request_service_name, request_price);
                requestDAO.insert(request);

                // Tạo bảng lịch sử của khách hàng
                historyDAO = new HistoryDAO(OrderActivity.this);
                History history = new History(service_name, time, price, current_customer_id);
                historyDAO.insert(history);
                Toast.makeText(OrderActivity.this, "Đặt thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void anhxa() {
        txtBillPrice = (TextView) findViewById(R.id.bill_price);
        tvClickOrderService=(TextView)findViewById(R.id.tvClickOrderService);
        edtName=(EditText)findViewById(R.id.request_customer_name);
        edtPhone=(EditText)findViewById(R.id.request_customer_phone);
        edtAddress=(EditText)findViewById(R.id.request_customer_address);
        edtProblem=(EditText)findViewById(R.id.request_problem);
        tvDate = (TextView) findViewById(R.id.request_time);
    }
}
