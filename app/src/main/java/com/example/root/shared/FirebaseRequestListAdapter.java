package com.example.root.shared;

import android.content.Context;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by root on 10/16/17.
 */

public class FirebaseRequestListAdapter extends FirebaseRecyclerAdapter<Request,FirebaseRequestViewHolder>{
    //database reference
    DatabaseReference mRef;
    Context mContext;
    //the constructor
    public FirebaseRequestListAdapter(Class<Request> mRequest, int modelLayout, Class<FirebaseRequestViewHolder> mViewHolder,Query mRef,Context context){
        super(mRequest,modelLayout,mViewHolder,mRef);
        mRef=mRef.getRef();
        mContext=context;
    }
    //populate the view holder
    @Override
    protected void populateViewHolder(FirebaseRequestViewHolder viewHolder,Request request,int position){
        viewHolder.bindRequest(request);
    }

}
