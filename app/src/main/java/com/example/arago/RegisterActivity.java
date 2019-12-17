package com.example.arago;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.DAO.CustomerDAO;
import com.example.arago.DAO.PartnerDAO;
import com.example.arago.Model.Customer;
import com.example.arago.Model.Partner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText _txtFullName, _txtEmail, _txtPass, _txtPassConfirm, _txtAddress, _txtPhone;
    private ImageView _ivAvatar;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    DatabaseReference reff;

    String id = "0";
    long maxid = 0;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBar(getWindow());
        init();
        mAuth = FirebaseAuth.getInstance();

        customer = new Customer();
        reff = FirebaseDatabase.getInstance().getReference().child("Customer");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    // LƯU Ý, VIẾT KIỂU NÀY KHÔNG ĐƯỢC XÓA BẤT KÌ DỮ LIỆU NGƯỜI DÙNG NÀO TRÊN FIREBASE.
                    // Đếm số lượng và gán cho maxid
                    maxid = (dataSnapshot.getChildrenCount());
                    // id = maxid +1
                    id = String.valueOf(maxid+1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null){
            // handle the already login user
        }
    }

    public void changeStatusBar(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.contentStatusBar));
        }
    }

    public void clickBack(View view) {
        finish();
    }

    public void clickBackToLogin(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void clickCreateAccount(View view) {
        registerUser();
    }

    public void init(){
        _txtFullName = (EditText) findViewById(R.id.register_name);
        _txtEmail = (EditText) findViewById(R.id.register_email);
        _txtPass = (EditText) findViewById(R.id.register_pass);
        _txtPassConfirm = (EditText) findViewById(R.id.register_confirm_pass);
        _txtPhone = (EditText) findViewById(R.id.register_phone);
        _txtAddress = (EditText) findViewById(R.id.register_address);
    }

    private void registerUser() {
        final String name = _txtFullName.getText().toString().trim();
        final String email = _txtEmail.getText().toString().trim();
        final String password = _txtPass.getText().toString().trim();
        final String confirm_password = _txtPassConfirm.getText().toString().trim();
        final String address = _txtAddress.getText().toString().trim();
        final String phone = _txtPhone.getText().toString().trim();
//        final int image = Integer.parseInt(_ivAvatar.getResources().toString().trim());


        final int image = R.drawable.emthree;

        if (name.isEmpty()) {
            _txtFullName.setError("Không được bỏ trống họ tên");
            _txtFullName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            _txtEmail.setError("Không được bỏ trống Email");
            _txtEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _txtEmail.setError("Địa chỉ Email không chính xác");
            _txtEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            _txtPass.setError("Không được bỏ trống mật khẩu");
            _txtPass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            _txtPass.setError("Độ dài mật khẩu ít nhất từ 6 kí tự");
            _txtPass.requestFocus();
            return;
        }

        if (confirm_password.length() < 6) {
            _txtPassConfirm.setError("Độ dài mật khẩu ít nhất từ 6 kí tự");
            _txtPassConfirm.requestFocus();
            return;
        }

        if (password.equals(confirm_password)) {
            if (address.isEmpty()) {
                _txtAddress.setError("Không được bỏ trống địa chỉ");
                _txtAddress.requestFocus();
                return;
            }

            if (address.length() <= 10) {
                _txtAddress.setError("Địa chỉ không chính xác");
                _txtAddress.requestFocus();
                return;
            }

            if (phone.isEmpty()) {
                _txtPhone.setError("Không được bỏ trống số điện thoại");
                _txtPhone.requestFocus();
                return;
            }

            if (phone.length() != 10) {
                _txtPhone.setError("Số điện thoại không chính xác");
                _txtPhone.requestFocus();
                return;
            }

            //            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
//                  progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        final Customer customer = new Customer(image, id, name, email, password, address, phone);
                        CustomerDAO customerDAO = new CustomerDAO(RegisterActivity.this);
                        customerDAO.insert(customer);
                    }else {
                        Toast.makeText(RegisterActivity.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            _txtPassConfirm.setError("Mật khẩu không trùng khớp");
            _txtPassConfirm.requestFocus();
            return;
        }
    }
}
