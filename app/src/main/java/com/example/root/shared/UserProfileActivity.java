package com.example.root.shared;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserProfileActivity extends AppCompatActivity {
    DatabaseReference mRef;
    FirebaseProfileAdapter mAdapter;
    @Bind(R.id.jobBoard) RecyclerView mRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        //get user specific data
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getUid();

        mRef=FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_PROFILE)
                .child(uid);

        setUpFirebaseAdapter();
    }

    public void setUpFirebaseAdapter(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getUid();
        mRef= FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PROFILE).child(uid);

        mAdapter=new FirebaseProfileAdapter(Account.class,R.layout.my_profile,FirebaseProfileViewHolder.class,mRef,this);
        //set up recyclerview
        mRecycler.setAdapter(mAdapter);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
