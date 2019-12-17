package com.example.arago._ADMIN.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arago._ADMIN.Adapter.PartnerAdapter;
import com.example.arago.DAO.PartnerDAO;
import com.example.arago.R;
import com.example.arago.Model.Partner;
import com.example.arago._USER.OrderActivity;
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
import java.util.Calendar;
import java.util.List;

public class PartnerFragment extends Fragment {
    public static List<Partner> partnerList ;
    RecyclerView rv_partner;
    PartnerAdapter adapter;
    PartnerDAO partnerDAO;
    DatabaseReference mDatabase;
    LinearLayoutManager mLayoutManager;
    Dialog myDialog;

    TextView txtClose;
    String partnerNamePopup, partnerEmailPopup, partnerAddressPopup, partnerPhonePopup, partnerCMNDPopup;

    private EditText _txtID, _txtFullName, _txtEmail, _txtPass, _txtPassConfirm, _txtPhone, _txtAddress, _txtCMND;
    private TextView _txtBirthDay;
    private RadioButton _radioMale, _radioFemale;
    private String sex;
    private FirebaseAuth mAuth;
    private FloatingActionButton floatingActionButton;
    private TextView tvClickCreatePartnerAccount;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    static final String TAG="QLSV";

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
        adapter = new PartnerAdapter(getActivity(),partnerList,this);


        mDatabase = FirebaseDatabase.getInstance().getReference("Partner");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                partnerList.clear();
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

        rv_partner.setAdapter(adapter);

        myDialog = new Dialog(getContext());



        adapter.setOnItemClickListener(new PartnerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Lấy thông tin theo vị trí click của recyclerview
                partnerNamePopup = partnerList.get(position).getPartner_name();
                partnerEmailPopup = partnerList.get(position).getPartner_email();
                partnerAddressPopup = partnerList.get(position).getPartner_address();
                partnerPhonePopup = partnerList.get(position).getPartner_phone();
                partnerCMNDPopup = partnerList.get(position).getPartner_cmnd();
                showPopup(getView());
            }
        });
        return view;
    }

    public void showPopup(View view){
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        // Gắn layout vào view
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.popup_partner_layout, null);
        myDialog.setContentView(view);

        // init
        TextView namePopup = view.findViewById(R.id.tv_popup_name);
        TextView emailPopup = view.findViewById(R.id.tv_popup_email);
        TextView addressPopup = view.findViewById(R.id.tv_popup_address);
        TextView phonePopup = view.findViewById(R.id.tv_popup_phone);
        TextView cmndPopup = view.findViewById(R.id.tv_popup_address);

        namePopup.setText(partnerNamePopup);
        emailPopup.setText(partnerEmailPopup);
        addressPopup.setText("Địa chỉ: " + partnerAddressPopup);
        phonePopup.setText("Số điện thoại: " + partnerPhonePopup);
        cmndPopup.setText("Chứng minh thư: " + partnerCMNDPopup);

        txtClose = view.findViewById(R.id.tv_close_partner_popup);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        // Set kích thước dialog
        myDialog.getWindow().setLayout((6 * width)/7, (3 * height)/7);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
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
                _txtBirthDay = (TextView) v.findViewById(R.id.register_birthday);
                _radioMale = (RadioButton) v.findViewById(R.id.radio_btn_male);
                _radioFemale = (RadioButton) v.findViewById(R.id.radio_btn_female);
                _txtEmail = (EditText) v.findViewById(R.id.register_email);
                _txtPass = (EditText) v.findViewById(R.id.register_pass);
                _txtPassConfirm = (EditText) v.findViewById(R.id.register_confirm_pass);
                _txtPhone = (EditText) v.findViewById(R.id.register_phone);
                _txtAddress = (EditText) v.findViewById(R.id.register_address);
                _txtCMND = (EditText) v.findViewById(R.id.register_cmnd);

                _txtBirthDay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar=Calendar.getInstance();
                        int year=calendar.get(Calendar.YEAR);
                        int month=calendar.get(Calendar.MONTH);
                        int day=calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog dialog= new DatePickerDialog(
                                getActivity(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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
                        _txtBirthDay.setText(date);
                    }
                } ;

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
