package com.example.root.shared;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by root on 10/18/17.
 */

public class FirebaseProfileViewHolder extends RecyclerView.ViewHolder {
    View view;
    Context context;
    //constructor
    public FirebaseProfileViewHolder(View itemView){
        super(itemView);
        view=itemView;
        context=itemView.getContext();
    }
    //bind the views
    public void bindAccount(Account account){
        //get views from the layout
        //bind them to the account object
        TextView name=(TextView) view.findViewById(R.id.profileName);
        TextView dateOfBirth=(TextView) view.findViewById(R.id.profileDateOfBirth);
        TextView gender=(TextView) view.findViewById(R.id.profileGender);
        TextView bloodType=(TextView) view.findViewById(R.id.profileBloodType);
        TextView location=(TextView) view.findViewById(R.id.profileResidency);
        TextView contact=(TextView) view.findViewById(R.id.profileEmail);
        //bind them to the object
        name.setText(account.getName());
        dateOfBirth.setText(account.getDateOfBirth());
        gender.setText(account.getSex());
        bloodType.setText(account.getBloodType());
        location.setText(account.getResidence());
        contact.setText(account.getEmail());
    }
}
