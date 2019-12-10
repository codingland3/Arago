package com.example.arago._USER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.arago.Model.Job;
import com.example.arago.R;
import com.example.arago._USER.Fragment.FragmentHome;
import com.example.arago._USER.Interface.ItemClicListener;
import com.example.arago._USER.ViewHolder.JobViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class JobList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference joblist;
    FirebaseRecyclerAdapter<Job, JobViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joblist);
        //FireBase
        database=FirebaseDatabase.getInstance();
        joblist=database.getReference("detailJob");
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_listjob);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        String id = intent.getStringExtra(FragmentHome.ID);

        loadlistjob(id);
    }

    private void loadlistjob(String categoryID) {
        adapter = new FirebaseRecyclerAdapter<Job, JobViewHolder>(Job.class,R.layout.customer_reyclerview_request,JobViewHolder.class,
                joblist.orderByChild("MenuId").equalTo(categoryID)) {
            //like select*from job where menu='category'
            @Override
            protected void populateViewHolder(JobViewHolder jobViewHolder, Job Job, int i) {
                jobViewHolder.job_name.setText(Job.getName());
                Picasso.with(getBaseContext()).load(Job.getImage()).into(jobViewHolder.imagejob);
                final Job local = Job;
                jobViewHolder.setItemClicListener(new ItemClicListener() {
                    @Override
                    public void onClick(View view, int postion, boolean isLongClick) {
                        //Start new Activity
                        Intent jobDetalls=new Intent(JobList.this,JobDetails.class);
                        jobDetalls.putExtra("JobID",adapter.getRef(postion).getKey());//send Id job to new Activity
                        startActivity(jobDetalls);
                    }
                });


            }
        };
        //set Adapter

        recyclerView.setAdapter(adapter);

    }
}
