package com.example.root.shared;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DonationRequestActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.nameEditView) EditText mRecipientName;
    @Bind(R.id.hospitalName) EditText mhospitalName;
    @Bind(R.id.recipientContact) EditText mRecipientContact;


    @Bind(R.id.submitRequest) Button mRequestButton;
    private Spinner spinnerListView1,spinnerListView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_request);
        ButterKnife.bind(this);
        mRequestButton.setOnClickListener(this);
        addItemsToSpinner();
        addItemsToSpinner2();
    }
    private boolean isValidName(String name){
        if(name.equals("")){
            mRecipientName.setError("enter a name");
            return false;
        }
        return true;
    }
    private boolean isValidHospital(String hospital){
        if(hospital.equals("")){
            mhospitalName.setError("enter a valid hospital");
            return false;
        }
        return true;
    }
    private boolean isValidContact(String contact){
        if(contact.equals("")){
            mRecipientContact.setError("enter a valid number");
            return false;
        }
        return true;
    }

    //add items to spinner
    public void addItemsToSpinner(){
        spinnerListView1=(Spinner) findViewById(R.id.spinnerViewBlood);
        List<String> bloodGroup = new ArrayList<String>();
        bloodGroup.add("A+");
        bloodGroup.add("A-");
        bloodGroup.add("B+");
        bloodGroup.add("B-");
        bloodGroup.add("AB+");
        bloodGroup.add("AB-");
        bloodGroup.add("O+");
        bloodGroup.add("O-");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, bloodGroup);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerListView1.setAdapter(dataAdapter);
    }

    public void addItemsToSpinner2(){
        spinnerListView2=(Spinner) findViewById(R.id.spinnerpatientState);
        List<String> condition=new ArrayList<String>();
        condition.add("critical");
        condition.add("not-critical");
        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,condition);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerListView2.setAdapter(dataAdapter);
    }
    @Override
    public void onClick(View view){
        if(view == mRequestButton){
            makeBloodRequest();
        }
    }

    public void makeBloodRequest(){
        String name=mRecipientName.getText().toString().trim();
        String hospital=mhospitalName.getText().toString().trim();
        String contact=mRecipientContact.getText().toString().trim();
        //get details from spinner menu
        String bloodType=String.valueOf(spinnerListView1.getSelectedItem());
        String condition=String.valueOf(spinnerListView2.getSelectedItem());
        //check credentials
        boolean validName=isValidName(name);
        boolean validHospital=isValidHospital(hospital);
        boolean validContact=isValidContact(contact);

        if(!validName||!validContact||!validHospital)return;
        //create new instance
        Request newRequest=new Request(name,hospital,contact,bloodType,condition);
        //push to firebase
        DatabaseReference mRef= FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_REQUEST);
        mRef.push().setValue(newRequest);
        Intent newIntent=new Intent(getApplicationContext(),DashboardActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
        Toast.makeText(getApplicationContext(),"your request has been sent",Toast.LENGTH_LONG).show();

    }
}
