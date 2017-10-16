package com.example.root.shared;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.submitAccount)
    Button mButton;
    @Bind(R.id.dateOfBirthView)
    EditText mBithday;


    @Bind(R.id.residenceEditView)
    EditText mResidence;
    @Bind(R.id.emailEditView)
    EditText mEmail;
    @Bind(R.id.nameEditView)
    EditText mName;
    private Spinner spinnerListView1, spinnerListView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        ButterKnife.bind(this);
        mButton.setOnClickListener(this);
        addItemsOnSpinner1();
        addItemsOnSpinner2();
    }

    public void addItemsOnSpinner1() {
        spinnerListView1 = (Spinner) findViewById(R.id.spinnerView1);
        List<String> gender = new ArrayList<String>();
        gender.add("Male");
        gender.add("Female");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, gender);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerListView1.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinner2() {
        spinnerListView2 = (Spinner) findViewById(R.id.spinnerView2);
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
        spinnerListView2.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view == mButton) {
            saveProfile();
            Log.v("check", String.valueOf(spinnerListView1.getSelectedItem()));
        }
    }

    //methods to check if form is filled correctly
    public boolean isValidName(String name) {
        if (name.equals("")) {
            mName.setError("Enter valid name");
            return false;
        }
        return true;
    }

    public boolean isValidBirthday(String birthday) {
        if (birthday.equals("")) {
            mBithday.setError("Enter date of birth");
            return false;
        }
        return true;
    }


    public boolean isValidResidence(String residence) {
        if (residence.equals("")) {
            mResidence.setError("Enter valid residence");
            return false;
        }
        return true;
    }

    public boolean isValidEmail(String email) {
        if (email.equals("")) {
            mEmail.setError("Enter valid email");
            return false;
        }
        return true;
    }

    public void saveProfile() {
        String name = mName.getText().toString().trim();
        String dateOfBirth = mBithday.getText().toString().trim();
        String sex = String.valueOf(spinnerListView1.getSelectedItem());
        String bloodType = String.valueOf(spinnerListView2.getSelectedItem());
        String residence = mResidence.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        Account account = new Account(name, dateOfBirth, sex, bloodType, residence, email);
        DatabaseReference profileRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_PROFILE);
        profileRef.push().setValue(account);
        Toast.makeText(getApplicationContext(), "profile saved", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}