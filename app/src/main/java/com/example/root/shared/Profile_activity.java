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
    //methods to check if form is filled correctly
    public boolean isValidName(String name){
        if(name.equals("")){
            mName.setError("Enter valid name");
            return false;
        }
        return true;
    }
    public boolean isValidBirthday(String birthday){
        if(birthday.equals("")){
            mBithday.setError("Enter date of birth");
            return false;
        }
        return true;
    }
    public boolean isValidGender(String gender){
        if(gender.equals("")){
            mGender.setError("Enter valid gender");
            return false;
        }
        return true;
    }
    public boolean isBloodType(String bloodtype){
        if(bloodtype.equals("")){
            mBloodType.setError("Enter valid bloodtype");
            return false;
        }
        return true;
    }
    public boolean isValidResidence(String residence){
        if(residence.equals("")){
            mResidence.setError("Enter valid residence");
            return false;
        }
        return true;
    }
    public boolean isValidEmail(String email){
        if(email.equals("")){
            mEmail.setError("Enter valid email");
            return false;
        }
        return true;
    }

    public void saveProfile(){
        String name=mName.getText().toString().trim();
        String dateOfBirth=mBithday.getText().toString().trim();
        String sex=mGender.getText().toString().trim();
        String bloodType=mBloodType.getText().toString().trim();
        String residence=mResidence.getText().toString().trim();
        String email=mEmail.getText().toString().trim();
        Account account=new Account(name,dateOfBirth,sex,bloodType,residence,email);
 //this will check the inputs and ensure they are correct
        boolean validName=isValidName(name);
        boolean validBirthday=isValidBirthday(dateOfBirth);
        boolean validGender=isValidGender(sex);
        boolean validBloodtype=isBloodType(bloodType);
        boolean validResidence=isValidResidence(residence);
        boolean validEmail=isValidEmail(email);
        //if they are not correct the code will not go past this section
        if(!validName|!validBirthday|!validGender|!validBloodtype|!validResidence|!validEmail) return;

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
