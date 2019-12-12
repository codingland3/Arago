package com.example.arago;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CauHoiThuongGap extends AppCompatActivity {

    ListView listView;
    String mMainifest[]={"Dịch vụ khách hàng","Arago hoạt động như thế nào?","làm thế nào để đặt dịch vụ","Dịch vụ của Arago có đáng tin hay không","Tôi có thể đặt dịch vụ này định kỳ không?","Tôi có thể thanh toán phí dịch vụ bằng cách nào?","Tôi có thể hủy dịch vụ không?","Tôi có được hoàn tiền nếu hủy dịch vụ?","tôi tích lũy điểm thưởng và đổi điểm thưởng như thế nào?"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi_thuong_gap);
        listView=findViewById(R.id.lvAskQuestion);
        CustomerAsk customerAsk=new CustomerAsk();
        listView.setAdapter(customerAsk);
    }

    private class CustomerAsk extends BaseAdapter {
        @Override
        public int getCount() {
            return 0;
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
            View view1=getLayoutInflater().inflate(R.layout.rowaskquestion,null);
            TextView name=view1.findViewById(R.id.tvaskquestion);
            name.setText(mMainifest[i]);

            return view1;
        }
    }
}
