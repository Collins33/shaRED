package com.example.root.shared;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by mwas on 10/16/17.
 */

public class DashboardItemAdapter extends RecyclerView.Adapter<DashboardItemAdapter.ViewHolder> {
    private ArrayList<Category> categoryData;
    private Context mContext;

    public DashboardItemAdapter(Context context, ArrayList<Category> data){
        this.categoryData = data;
        this.mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

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

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public DashboardItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View dashboard = inflater.inflate(R.layout.dashboard_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(dashboard);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(DashboardItemAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
//        Category category = categoryData.get(position);

        // Set item views based on your views and data model
        TextView categoryName = viewHolder.iconTitle;
        ImageView categoryImage = viewHolder.imageIcon;

        categoryName.setText(categoryData.get(position).getName());
        categoryImage.setImageResource(categoryData.get(position).getImage());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return categoryData.size();
    }

}
