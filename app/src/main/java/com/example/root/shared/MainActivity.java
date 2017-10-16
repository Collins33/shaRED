package com.example.root.shared;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.bloodDriveButton) Button mFindBloodDriveButton;
    @Bind(R.id.requestDonationButton) Button mRequestDonation;
    @Bind(R.id.dashboardButton) Button mDashboardbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFindBloodDriveButton.setOnClickListener(this);
        mRequestDonation.setOnClickListener(this);
        mDashboardbutton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if(view==mFindBloodDriveButton){
            Intent intent=new Intent(getApplicationContext(),RequestListActivity.class);
            startActivity(intent);
        }

        if(view==mRequestDonation){
            Intent intent=new Intent(getApplicationContext(),DonationRequestActivity.class);
            startActivity(intent);
        }
        if(view==mDashboardbutton){
            Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
            startActivity(intent);
        }
    }
}
