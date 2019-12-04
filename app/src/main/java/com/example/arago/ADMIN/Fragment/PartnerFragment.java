package com.example.arago.ADMIN.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago.ADMIN.Adapter.PartnerAdapter;
import com.example.arago.DAO.PartnerDAO;
import com.example.arago.R;
import com.example.arago.RegisterActivity;
import com.example.arago.USER.Model.Customer;
import com.example.arago.USER.Model.Partner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PartnerFragment extends Fragment {
    public static List<Partner> partnerList ;
    RecyclerView rv_partner;
    PartnerAdapter adapter;
    PartnerDAO partnerDAO;
    DatabaseReference mDatabase;
    LinearLayoutManager mLayoutManager;
    FloatingActionButton floatingActionButton;
    private EditText _txtFullName, _txtEmail, _txtPass, _txtPassConfirm, _txtAddress, _txtPhone, _txtCMND;
    private FirebaseAuth mAuth;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.layout_partner, container, false);

        rv_partner = view.findViewById(R.id.rv_partner);
        partnerDAO = new PartnerDAO(getActivity());
        partnerList = new ArrayList<Partner>();

        mDatabase = FirebaseDatabase.getInstance().getReference("Partner");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Partner item = data.getValue(Partner.class);
                    partnerList.add(item);
                }
                listViewUpdate();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mDatabase.addValueEventListener(listener);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rv_partner.setLayoutManager(mLayoutManager);

        adapter=new PartnerAdapter(getActivity(),partnerList,this);
        rv_partner.setAdapter(adapter);
        return view;
    }

    public void partnerDelete(Partner c){
        partnerDAO.delete(c);
        listViewUpdate();
    }

    public void listViewUpdate(){
        adapter.notifyItemInserted(partnerList.size());
        adapter.notifyDataSetChanged();
    }

    public void clickAddPartner(){
        floatingActionButton.show();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                // Gắn layout vào view
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = inflater.inflate(R.layout.activity_register_partner, null);
                alertDialog.setView(v);

                init();

                // Gắn thêm nút và hiển thị dialog
                alertDialog.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            registerPartner();
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            return;
                        } catch (NullPointerException npe) {
                            Toast.makeText(getContext(), "Lỗi NullPointerException", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alertDialog.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });

    }


    private void registerPartner() {
        final String name = _txtFullName.getText().toString().trim();
        final String email = _txtEmail.getText().toString().trim();
        final String password = _txtPass.getText().toString().trim();
        final String confirm_password = _txtPassConfirm.getText().toString().trim();
        final String address = _txtAddress.getText().toString().trim();
        final String phone = _txtPhone.getText().toString().trim();
        final String cmnd = _txtCMND.getText().toString().trim();
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
//                            progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Customer customer = new Customer(image, name, email, password, address, phone);
                        FirebaseDatabase.getInstance().getReference("Partner").child(
                                FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(customer)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);    // không khả thi
//                                        intent.putExtra("id", name);
//                                        startActivity(intent);
                                        } else {
                                            Toast.makeText(getContext(), "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    } else {
                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            _txtPassConfirm.setError("Mật khẩu không trùng khớp");
            _txtPassConfirm.requestFocus();
            return;
        }
    }

    public void init(){
        _txtFullName = (EditText) getActivity().findViewById(R.id.register_name);
        _txtEmail = (EditText) getActivity().findViewById(R.id.register_email);
        _txtPass = (EditText) getActivity().findViewById(R.id.register_pass);
        _txtPassConfirm = (EditText) getActivity().findViewById(R.id.register_confirm_pass);
        _txtPhone = (EditText) getActivity().findViewById(R.id.register_phone);
        _txtAddress = (EditText) getActivity().findViewById(R.id.register_address);
        _txtCMND = (EditText) getActivity().findViewById(R.id.register_cmnd);
    }


}
