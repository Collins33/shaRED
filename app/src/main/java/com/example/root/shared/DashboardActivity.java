package com.example.root.shared;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

   @Bind(R.id.card_view) CardView mProfile;
   @Bind(R.id.bloodDrive) CardView mBloodDrive;
   @Bind(R.id.bloodRequest) CardView mBloodRequest;
   @Bind(R.id.bloodRequestListCard) CardView mBloodRequestList;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard_activity);
        ButterKnife.bind(this);
        mProfile.setOnClickListener(this);
        mBloodDrive.setOnClickListener(this);
        mBloodRequest.setOnClickListener(this);
        mBloodRequestList.setOnClickListener(this);
    }
    @Override
    public void onStart(){
        super.onStart();
        onChildAdded();
    }
    public void onChildAdded() {
        mRef= FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_REQUEST);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sendNotification();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void sendNotification() {
        NotificationCompat.Builder mBuilder = (android.support.v7.app.NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notificationicon)
                .setContentTitle("Blood Request")
                .setContentText("Urgent Need For Blood!!");

        Intent resultIntent = new Intent(this, RequestListActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, mBuilder.build());
        Log.v("example", "notification");
    }
    @Override
    public void onClick(View view){
        if(view==mProfile){
            Intent profileIntent=new Intent(getApplicationContext(),UserProfileActivity.class);
            startActivity(profileIntent);
        }
        else if(view==mBloodDrive){
            Intent bloodDriveIntent=new Intent(getApplicationContext(),BloodDriveActivity.class);
            startActivity(bloodDriveIntent);
        }
        else if(view==mBloodRequest){
            Intent bloodRequestForm=new Intent(getApplicationContext(),DonationRequestActivity.class);
            startActivity(bloodRequestForm);
        }
        else if(view==mBloodRequestList){
            Intent BloodRequestListIntent=new Intent(getApplicationContext(),RequestListActivity.class);
            startActivity(BloodRequestListIntent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(getApplicationContext(), SigninActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
