package com.example.root.shared;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.shared.models.Accounts;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Profile_activity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.nameEditView) EditText mNameEditView;
    @Bind(R.id.dateOfBirthView) EditText mDateOfBirthView;
    @Bind(R.id.sexEditView) EditText mSexEditView;
    @Bind(R.id.bloodTypeEditView) EditText mBloodTypeEditView;
    @Bind(R.id.residenceEditView) EditText mResidenceEditView;
    @Bind(R.id.emailEditView) EditText mEmailEditView;
    @Bind(R.id.submitAccount) Button mSubmitAccount;

    Accounts account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        ButterKnife.bind(this);

        mSubmitAccount.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v == mSubmitAccount) {
            String name = mNameEditView.getText().toString().trim();
            String dateOfBirth = mDateOfBirthView.getText().toString().trim();
            String sex = mSexEditView.getText().toString().trim();
            String bloodType = mBloodTypeEditView.getText().toString().trim();
            String residence = mResidenceEditView.getText().toString().trim();
            String email = mEmailEditView.getText().toString().trim();

            account = new Accounts(name,dateOfBirth,sex,bloodType,residence,email);
            DatabaseReference userRef = FirebaseDatabase.getInstance()
                    .getReference(Constants.FIREBASE_USER_ACCOUNTS);
            userRef.push().setValue(account);
            Log.v("checkout",account.getName());
        }
    }
}
