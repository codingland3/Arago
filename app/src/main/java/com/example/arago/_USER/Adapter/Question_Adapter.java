package com.example.arago._USER.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.arago.R;

import java.util.HashMap;
import java.util.List;

public class Question_Adapter extends BaseExpandableListAdapter {

    Context context;
    List<String> listHeader;
    HashMap<String, List<String>> listChild;

    public Question_Adapter(Context context, List<String> listHeader, HashMap<String, List<String>> listChild) {
        this.context = context;
        this.listHeader = listHeader;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {
        return listHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listChild.get(this.listHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChild.get(this.listHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View convertview, ViewGroup viewGroup) {
        String questions = (String) getGroup(groupPosition);
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertview = inflater.inflate(R.layout.list_group, null);
        TextView txtHeader = convertview.findViewById(R.id.list_group);
        txtHeader.setText(questions);
        return convertview;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertview, ViewGroup viewGroup) {
        String questions = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertview = inflater.inflate(R.layout.list_child, null);
        TextView txtItem = convertview.findViewById(R.id.list_child);
        txtItem.setText(questions);
        return convertview;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
