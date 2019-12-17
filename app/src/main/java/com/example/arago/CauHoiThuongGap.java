package com.example.arago;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.arago._USER.Adapter.Question_Adapter;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CauHoiThuongGap extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> list;
    HashMap<String, List<String>> listHashMap;
    Question_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi_thuong_gap);

        expandableListView = findViewById(R.id.expand_ask_question);
        list = new ArrayList<>();
        listHashMap = new HashMap<>();
        adapter = new Question_Adapter(this, list, listHashMap);
        expandableListView.setAdapter(adapter);
        initListData();
    }

    private void initListData() {
        list.add(getString(R.string.group1));
        list.add(getString(R.string.group2));
        list.add(getString(R.string.group3));
        list.add(getString(R.string.group4));
        list.add(getString(R.string.group5));
        list.add(getString(R.string.group6));
        list.add(getString(R.string.group7));
        list.add(getString(R.string.group8));
        list.add(getString(R.string.group9));

        String[] array;
        List<String> list1 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group1);
        for (String item : array){
            list1.add(item);
        }

        List<String> list2 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group2);
        for (String item : array){
            list2.add(item);
        }

        List<String> list3 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group3);
        for (String item : array){
            list3.add(item);
        }

        List<String> list4 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group4);
        for (String item : array){
            list4.add(item);
        }

        List<String> list5 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group5);
        for (String item : array){
            list5.add(item);
        }

        List<String> list6 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group6);
        for (String item : array){
            list6.add(item);
        }

        List<String> list7 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group7);
        for (String item : array){
            list7.add(item);
        }

        List<String> list8 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group8);
        for (String item : array){
            list8.add(item);
        }

        List<String> list9 = new ArrayList<>();
        array = getResources().getStringArray(R.array.group9);
        for (String item : array){
            list9.add(item);
        }

        listHashMap.put(list.get(0),list1);
        listHashMap.put(list.get(1),list2);
        listHashMap.put(list.get(2),list3);
        listHashMap.put(list.get(3),list4);
        listHashMap.put(list.get(4),list5);
        listHashMap.put(list.get(5),list6);
        listHashMap.put(list.get(6),list7);
        listHashMap.put(list.get(7),list8);
        listHashMap.put(list.get(8),list9);
        adapter.notifyDataSetChanged();
    }

    public void clickBack(View view) {
        finish();
    }
}
