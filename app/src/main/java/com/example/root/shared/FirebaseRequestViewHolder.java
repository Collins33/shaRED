package com.example.root.shared;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by root on 10/16/17.
 */

public class FirebaseRequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View view;
    Context context;
    ArrayList<Request> mRequest;


    public FirebaseRequestViewHolder(View itemView){
        super(itemView);
        view=itemView;
        context=itemView.getContext();
        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        //get specific item clicked
        int position=getLayoutPosition();
        Intent intent=new Intent(context,DonationDetailActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("request", Parcels.wrap(mRequest));
        context.startActivity(intent);

    }
    public void bindRequest(Request request){
        TextView name=(TextView) itemView.findViewById(R.id.nameText);
        TextView hospital=(TextView) itemView.findViewById(R.id.hospitalText);
        TextView bloodType=(TextView) itemView.findViewById(R.id.bloodtypeText);
        //bind view to object
        name.setText(request.getName());
        hospital.setText(request.getHospital());
        bloodType.setText(request.getContact());
    }
}
