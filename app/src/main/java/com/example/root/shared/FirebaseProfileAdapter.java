package com.example.root.shared;

import android.content.Context;
import android.provider.ContactsContract;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by root on 10/18/17.
 */

public class FirebaseProfileAdapter extends FirebaseRecyclerAdapter<Account,FirebaseProfileViewHolder> {
    DatabaseReference mref;
    Context mContext;
    //constructor
    public FirebaseProfileAdapter(Class<Account> modelClass,int modelLayout,Class<FirebaseProfileViewHolder>viewHolder,Query ref,Context context){
        super(modelClass,modelLayout,viewHolder,ref);
        mref=ref.getRef();
        mContext=context;
    }
    @Override
    protected void populateViewHolder(FirebaseProfileViewHolder viewHolder,Account account,int position){
        viewHolder.bindAccount(account);
    }
}
