package com.example.arago._ADMIN.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago._ADMIN.Adapter.PartnerAdapter;
import com.example.arago.DAO.PartnerDAO;
import com.example.arago.R;
import com.example.arago.Model.Partner;
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

    private EditText _txtID, _txtFullName, _txtBirthDay, _txtEmail, _txtPass, _txtPassConfirm, _txtPhone, _txtAddress, _txtCMND;
    private RadioButton _radioMale, _radioFemale;
    private RadioGroup _radioGroupSex;
    private String sex;
    private FirebaseAuth mAuth;
    private FloatingActionButton floatingActionButton;
    private TextView tvClickCreatePartnerAccount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.layout_partner, container, false);

        rv_partner = (RecyclerView) view.findViewById(R.id.rv_partner);
        floatingActionButton = view.findViewById(R.id.fab_create_partner_account);

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

        clickAddPartner();

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




    public void clickAddPartner (){
        floatingActionButton.show();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                // Gắn layout vào view
                LayoutInflater inflater = getActivity().getLayoutInflater();
                v = inflater.inflate(R.layout.activity_register_partner, null);
                alertDialog.setView(v);

                _txtID = (EditText) v.findViewById(R.id.register_id);
                _txtFullName = (EditText) v.findViewById(R.id.register_name);
                _txtBirthDay = (EditText) v.findViewById(R.id.register_birthday);
                _radioGroupSex = (RadioGroup) v.findViewById(R.id.radioGroupSex);
                _radioMale = (RadioButton) v.findViewById(R.id.radio_btn_male);
                _radioFemale = (RadioButton) v.findViewById(R.id.radio_btn_female);
                _txtEmail = (EditText) v.findViewById(R.id.register_email);
                _txtPass = (EditText) v.findViewById(R.id.register_pass);
                _txtPassConfirm = (EditText) v.findViewById(R.id.register_confirm_pass);
                _txtPhone = (EditText) v.findViewById(R.id.register_phone);
                _txtAddress = (EditText) v.findViewById(R.id.register_address);
                _txtCMND = (EditText) v.findViewById(R.id.register_cmnd);

                tvClickCreatePartnerAccount = v.findViewById(R.id.tvClickCreatePartnerAccount);
                tvClickCreatePartnerAccount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        registerPartner();
                    }
                });
                alertDialog.show();
            }
        });
    }

    private void registerPartner() {
        final String id = _txtID.getText().toString().trim();
        final String name = _txtFullName.getText().toString().trim();
        if (_radioMale.isChecked()){
            sex = "Nam";
        } else if (_radioFemale.isChecked()){
            sex = "Nữ";
        }
        final String birthday = _txtBirthDay.getText().toString().trim();
        final String email = _txtEmail.getText().toString().trim();
        final String password = _txtPass.getText().toString().trim();
        final String confirm_password = _txtPassConfirm.getText().toString().trim();
        final String address = _txtAddress.getText().toString().trim();
        final String phone = _txtPhone.getText().toString().trim();
        final String cmnd = _txtCMND.getText().toString().trim();
//        final int image = Integer.parseInt(_ivAvatar.getResources().toString().trim());
        final int image = R.drawable.emthree;


        if (id.isEmpty()) {
            _txtID.setError("Không được bỏ trống trường này");
            _txtID.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            _txtFullName.setError("Không được bỏ trống trường này");
            _txtFullName.requestFocus();
            return;
        }

        if (birthday.isEmpty()) {
            _txtBirthDay.setError("Không được bỏ trống trường này");
            _txtBirthDay.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            _txtEmail.setError("Không được bỏ trống trường này");
            _txtEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _txtEmail.setError("Nhập sai trường này");
            _txtEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            _txtPass.setError("Không được bỏ trống trường này");
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
                _txtAddress.setError("Không được bỏ trống trường này");
                _txtAddress.requestFocus();
                return;
            }

            if (address.length() <= 10) {
                _txtAddress.setError("Nhập sai trường này");
                _txtAddress.requestFocus();
                return;
            }

            if (phone.isEmpty()) {
                _txtPhone.setError("Không được bỏ trống trường này");
                _txtPhone.requestFocus();
                return;
            }

            if (phone.length() != 10) {
                _txtPhone.setError("Nhập sai trường này");
                _txtPhone.requestFocus();
                return;
            }

            if (cmnd.isEmpty()) {
                _txtCMND.setError("Không được bỏ trống trường này");
                _txtCMND.requestFocus();
                return;
            }

            if (cmnd.length() < 9) {
                _txtCMND.setError("Nhập sai trường này");
                _txtCMND.requestFocus();
                return;
            }

            // Tạo tài khoản
//            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
//                  progressBar.setVisibility(View.GONE);
                    PartnerDAO partnerDAO = new PartnerDAO(getContext());
                    final Partner partner = new Partner(image, id, name, email, password, address, phone, cmnd, sex, birthday);
                    partnerDAO.insert(partner);
                }
            });
        } else {
            _txtPassConfirm.setError("Mật khẩu không trùng khớp");
            _txtPassConfirm.requestFocus();
            return;
        }
    }
}
