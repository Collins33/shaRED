package com.example.root.shared;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RequestListActivity extends AppCompatActivity {
    private DatabaseReference mRef;
    private FirebaseRequestListAdapter mAdapter;
    @Bind(R.id.bloodRequestRecycler) RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_activity);
        ButterKnife.bind(this);
        setUpFirebaseAdapter();
    }
    public void setUpFirebaseAdapter(){
        mRef= FirebaseDatabase.getInstance().getReference();
        Query query= mRef.child("request").orderByChild("contact").equalTo("A+");
        mAdapter=new FirebaseRequestListAdapter(Request.class,
                R.layout.request_card_layout, FirebaseRequestViewHolder.class,
                query, this);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter);
        mRecycler.setHasFixedSize(true);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
