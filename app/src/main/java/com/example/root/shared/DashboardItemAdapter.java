package com.example.root.shared;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by mwas on 10/16/17.
 */

public class DashboardItemAdapter extends RecyclerView.Adapter<DashboardItemAdapter.ViewHolder> {
    private ArrayList<Category>

    public class ViewHolder extends RecyclerView.ViewHolder {
        //list all the views that are in the card view layout
        public ImageView imageIcon;
        public TextView iconTitle;

        public ViewHolder(View itemView){
            super(itemView);
            imageIcon = (ImageView) itemView.findViewById(R.id.person_photo);
            iconTitle = (TextView) itemView.findViewById(R.id.person_name);
        }
    }
}
