package com.example.arago._USER;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arago.CauHoiThuongGap;
import com.example.arago.ChatvoiAdmin;
import com.example.arago.Danhgiaapp;
import com.example.arago.R;
import com.example.arago._ADMIN.Adapter.CustomerAdapter;
import com.example.arago.callCSKHTrung;

public class HelpActivity extends AppCompatActivity {

    ListView listView;
    String mMaintest[]={"Tổng đài CSKH","Câu hỏi thường gặp","Chat với Admin","Đánh giá ứng dụng"};
    int[]imagesss={R.drawable.ic_phone,R.drawable.ic_question_answer,R.drawable.ic_chat,R.drawable.ic_done_black_24dp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        listView=findViewById(R.id.listviewhelp);
        CustomerCSKH customerCSKH=new CustomerCSKH();
        listView.setAdapter(customerCSKH);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    Intent cskh=new Intent(HelpActivity.this,callCSKHTrung.class);
                    startActivity(cskh);
                }
                if (i==1){
                    Intent cskh2=new Intent(HelpActivity.this, CauHoiThuongGap.class);
                    startActivity(cskh2);
                }if (i==2){
                    Intent cskh2=new Intent(HelpActivity.this, ChatvoiAdmin.class);
                    startActivity(cskh2);
                }if (i==3){
                    Intent cskh2=new Intent(HelpActivity.this, Danhgiaapp.class);
                    startActivity(cskh2);
                }
            }
        });
    }

    public void clickBack(View view) {
        finish();
    }

    private class CustomerCSKH extends BaseAdapter {
        @Override
        public int getCount() {
            return imagesss.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1=getLayoutInflater().inflate(R.layout.row,null);
            TextView name=view1.findViewById(R.id.tvmain);
            ImageView imageView=view1.findViewById(R.id.imagesHelp);
            name.setText(mMaintest[i]);
            imageView.setImageResource(imagesss[i]);
            return view1;
        }
    }
}
