package com.example.arago._USER;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.arago.Model.Job;
import com.example.arago.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class JobDetails extends AppCompatActivity {
    TextView job_namede,job_description, job_price;
    ImageView job_imagesde;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btncart;
    String JOBid="";
    FirebaseDatabase database;
    DatabaseReference jobs;
    public static String PRICE = "price";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        //FireBase
        database= FirebaseDatabase.getInstance();
        jobs=database.getReference("detailJob");
        //Init view
        btncart=(FloatingActionButton)findViewById(R.id.btncard);
        job_price = (TextView) findViewById(R.id.service_price);
        job_description=(TextView)findViewById(R.id.service_description);
        job_namede=(TextView)findViewById(R.id.job_namedetails);
        job_imagesde=(ImageView)findViewById(R.id.img_JobDetails);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OPenOrderFix();
            }
        });

        //get food id from Intent
        if (getIntent()!=null)
            JOBid=getIntent().getStringExtra("JobID");
        if (!JOBid.isEmpty()){
            getDetailJOb(JOBid);
        }
    }

    private void OPenOrderFix() {
        String price = job_price.getText().toString();
        Intent intent=new Intent(JobDetails.this,OrderActivity.class);
        intent.putExtra(PRICE, price);
        startActivity(intent);
    }

    private void getDetailJOb(final String jobId) {
        jobs.child(jobId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Job job = dataSnapshot.getValue(Job.class);
                //set Images
                Picasso.with(getBaseContext()).load(job.getImage()).into(job_imagesde);

                collapsingToolbarLayout.setTitle(job.getName());
                job_namede.setText(job.getName());
                job_description.setText(job.getDescription());
                job_price.setText(job.getPrice());

                String getJob = job.getDescription();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
