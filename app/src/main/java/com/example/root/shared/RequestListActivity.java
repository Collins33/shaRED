package com.example.root.shared;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

  public class RequestListActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference mRef;
    private FirebaseRequestListAdapter mAdapter;
    @Bind(R.id.bloodRequestRecycler) RecyclerView mRecycler;
    @Bind(R.id.searchBlood) Button mFindBloodType;
    private Spinner spinnerListView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_activity);
        addItemsToSpinner();
        ButterKnife.bind(this);
        mFindBloodType.setOnClickListener(this);
        setUpInitialAdapter();
    }

    @Override
    public void onClick(View view){
        setUpFirebaseAdapter();
    }
    public void addItemsToSpinner(){
        spinnerListView1=(Spinner) findViewById(R.id.spinnerViewBlood);
        List<String> bloodGroup = new ArrayList<String>();
        bloodGroup.add("A+");
        bloodGroup.add("A-");
        bloodGroup.add("B+");
        bloodGroup.add("B-");
        bloodGroup.add("AB+");
        bloodGroup.add("AB-");
        bloodGroup.add("O+");
        bloodGroup.add("O-");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, bloodGroup);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerListView1.setAdapter(dataAdapter);
    }

    public void setUpFirebaseAdapter(){
        mRef= FirebaseDatabase.getInstance().getReference();
        String blood=String.valueOf(spinnerListView1.getSelectedItem());
        Query query= mRef.child("request").orderByChild("bloodtype").equalTo(blood);
        mAdapter=new FirebaseRequestListAdapter(Request.class,
                R.layout.request_card_layout, FirebaseRequestViewHolder.class,
                query, this);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter);
        mRecycler.setHasFixedSize(true);
    }
    public void setUpInitialAdapter(){
        mRef= FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_REQUEST);


        mAdapter=new FirebaseRequestListAdapter(Request.class,
                R.layout.request_card_layout, FirebaseRequestViewHolder.class,
                mRef, this);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter);
        mRecycler.setHasFixedSize(true);
    }

  }
