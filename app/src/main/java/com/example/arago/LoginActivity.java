package com.example.arago;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.arago.DAO.CustomerDAO;
import com.example.arago.DAO.PartnerDAO;
import com.example.arago.Model.Customer;
import com.example.arago.Model.Partner;
import com.example.arago._USER.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    CheckBox cb_remember;
    private FirebaseAuth auth;
    SharedPreferences luutru;
    String user, pass;
    List<Customer> customers = new ArrayList<>();
    List<Partner> partners = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        changeStatusBar(getWindow());
        init();
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        // Get sharePreferent
        luutru = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);  // MODE_PRIVATE thì chỉ ứng dụng này mới có thể đọc được thôi.
        // Nạp thông tin lên form từ sharePreference
        final Boolean luuthongtin = luutru.getBoolean("save_information", false);
        if (luuthongtin) {
            password.setText(luutru.getString("password", ""));
            cb_remember.setChecked(true);
        }
    }

    private void init() {
        cb_remember = findViewById(R.id.cb_remember);
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
        clickLoginWithFirebase();
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

    public void clickLoginWithFirebase(){
        // user và pass là 2 biến để get và chứa dữ liệu của editText trong form đăng nhập.
        user = username.getText().toString();
        pass = password.getText().toString();

        if (user.isEmpty()) {
            username.setError("Mời nhập tài khoản");
            username.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            username.setError("Địa chỉ Email không hợp lệ");
            username.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            password.setError("Mời nhập mật khẩu");
            password.requestFocus();
            return;
        }

        if (pass.length() < 6) {
            password.setError("Độ dài mật khẩu ít nhất từ 6 kí tự");
            password.requestFocus();
            return;
        } else {
            // Tài khoản admin gán cứng
            if (user.equals("arago@gmail.com") && pass.equals("123456")) {
                Intent intent = new Intent(LoginActivity.this, com.example.arago._ADMIN.MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập với tư cách admin", Toast.LENGTH_SHORT).show();
                return;
            } else {
                // Authenticate user
                auth.signInWithEmailAndPassword(user, pass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // Nếu đăng nhập thất bại, hiển thị thông báo cho người dùng. Nếu đăng nhập thành công
                                // trình nghe trạng thái auth sẽ được thông báo và logic để xử lý
                                // người dùng đã đăng nhập có thể được xử lý trong trình nghe.
                                if (task.isSuccessful()) {
                                    PartnerDAO partnerDAO = new PartnerDAO(LoginActivity.this);
                                    partners = partnerDAO.getAll();
                                    CustomerDAO customerDAO = new CustomerDAO(LoginActivity.this);
                                    customers = customerDAO.getAll();

                                    // Vòng lặp so sánh text từ edittext và danh sách partners
                                    for (int j = 0; j <= partners.size() - 1; j++) {
                                        if (partners.size() > 0) {
                                            if (user.equals(partners.get(j).getPartner_email())) {
                                                Intent intent = new Intent(LoginActivity.this, com.example.arago._PARTNER.MainActivity.class);
                                                startActivity(intent);
                                                Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập với tư cách cộng tác viên", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                    }

                                    // Vòng lặp so sánh text từ edittext và danh sách customers
                                    for (int a = 0; a <= customers.size() - 1; a++) {
                                        if (customers.size() > 0) {
                                            if (user.equals(customers.get(a).getCustomer_email())) {
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập với tư cách người dùng", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                    }

                                } else {
                                    Toast.makeText(LoginActivity.this,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }
}
