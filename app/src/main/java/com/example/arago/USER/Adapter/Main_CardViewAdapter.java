package com.example.arago.USER.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.arago.R;
import com.example.arago.USER.Model.Event;

import java.util.List;

public class Main_CardViewAdapter extends PagerAdapter {

    private List<Event> events;
    private LayoutInflater layoutInflater;
    private Context context;

    public Main_CardViewAdapter(List<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cardview_item_event, container, false);

        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.img_cardview);
        title = view.findViewById(R.id.tv_title_cardview);
        desc = view.findViewById(R.id.tv_description_cardview);

        imageView.setImageResource(events.get(position).getImages());
        title.setText(events.get(position).getTitle());
        desc.setText(events.get(position).getDesc());

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
