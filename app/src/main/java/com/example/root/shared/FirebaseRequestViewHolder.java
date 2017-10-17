package com.example.root.shared;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by root on 10/16/17.
 */

public class FirebaseRequestViewHolder extends RecyclerView.ViewHolder {
    View view;
    Context context;

    public FirebaseRequestViewHolder(View itemView){
        super(itemView);
        view=itemView;
        context=itemView.getContext();
    }
    public void bindRequest(Request request){
        TextView name=(TextView) itemView.findViewById(R.id.nameText);
        TextView hospital=(TextView) itemView.findViewById(R.id.hospitalText);
        TextView bloodType=(TextView) itemView.findViewById(R.id.bloodtypeText);
        //bind view to object
        name.setText(request.getName());
        hospital.setText(request.getHospital());
        bloodType.setText(request.getBloodtype());
    }
}
