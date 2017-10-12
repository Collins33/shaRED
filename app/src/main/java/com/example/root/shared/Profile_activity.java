package com.example.root.shared;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Profile_activity extends AppCompatActivity implements View.OnClickListener{
   @Bind(R.id.submitAccount) Button mButton;
    @Bind(R.id.dateOfBirthView) EditText mBithday;
    @Bind(R.id.sexEditView) EditText mGender;
    @Bind(R.id.bloodTypeEditView) EditText mBloodType;
    @Bind(R.id.residenceEditView) EditText mResidence;
    @Bind(R.id.emailEditView) EditText mEmail;
    @Bind(R.id.nameEditView) EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        ButterKnife.bind(this);
        mButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if(view == mButton){
            saveProfile();
        }
    }
    public void saveProfile(){
        String name=mName.getText().toString().trim();
        String dateOfBirth=mBithday.getText().toString().trim();
        String sex=mGender.getText().toString().trim();
        String bloodType=mBloodType.getText().toString().trim();
        String residence=mResidence.getText().toString().trim();
        String email=mEmail.getText().toString().trim();
        Account account=new Account(name,dateOfBirth,sex,bloodType,residence,email);

        DatabaseReference profileRef= FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_PROFILE);
        profileRef.push().setValue(account);

        Toast.makeText(getApplicationContext(),"profile saved",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getApplicationContext(),Dashboard_activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
