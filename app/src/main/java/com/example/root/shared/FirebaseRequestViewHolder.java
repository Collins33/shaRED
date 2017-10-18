package com.example.root.shared;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by root on 10/16/17.
 */

public class FirebaseRequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View view;
    Context context;



    public FirebaseRequestViewHolder(View itemView){
        super(itemView);
        view=itemView;
        context=itemView.getContext();
        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        final ArrayList<Request> request = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_REQUEST);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    request.add(snapshot.getValue(Request.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(context, DonationDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("request", Parcels.wrap(request));

                context.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
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
