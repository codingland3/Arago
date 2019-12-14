package com.example.arago._USER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.arago.Model.Job;
import com.example.arago.R;
import com.example.arago._USER.Fragment.FragmentHome;
import com.example.arago._USER.Interface.ItemClicListener;
import com.example.arago._USER.ViewHolder.JobViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class JobList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference joblist;
    FirebaseRecyclerAdapter<Job, JobViewHolder> adapter;
    //search Funtionality
    FirebaseRecyclerAdapter<Job, JobViewHolder> searchadapter;
    List<String> suggestist=new ArrayList<>();
    MaterialSearchBar materialSearchBar;
    String categoryId="";

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

        materialSearchBar=(MaterialSearchBar)findViewById(R.id.searchbar);
        materialSearchBar.setHint("Enter your job");
        loadsuggest(id);
        materialSearchBar.setLastSuggestions(suggestist);
        materialSearchBar.setCardViewElevation(10);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //when user type, we will change suggest list
                List<String> suggest=new ArrayList<String>();
                for (String search:suggestist)
                {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                //when search bar is close
                //restore original suggest adapter
                if (!enabled)
                    recyclerView.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                //when search finish
                //show result
                startSearch(text);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

    }

    private void startSearch(CharSequence text) {
        searchadapter=new FirebaseRecyclerAdapter<Job, JobViewHolder>(
                Job.class,
                R.layout.customer_reyclerview_request,
                JobViewHolder.class,
                joblist.orderByChild("Name").equalTo(text.toString())
        ) {
            @Override
            protected void populateViewHolder(JobViewHolder jobViewHolder, Job job, int i) {
                jobViewHolder.job_name.setText(job.getName());
                Picasso.with(getBaseContext()).load(job.getImage()).into(jobViewHolder.imagejob);
                final Job local = job;
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
        recyclerView.setAdapter(searchadapter);//set adapter for recyclerview is search result
    }

    private void loadsuggest(final String id) {
        joblist.orderByChild("MenuId").equalTo(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    Job item=postSnapshot.getValue(Job.class);
                    suggestist.add(item.getName());//Add name of food suggest
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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

    public void clickBack(View view) {
        finish();
    }
}
