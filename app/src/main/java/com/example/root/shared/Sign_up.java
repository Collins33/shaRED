package com.example.root.shared;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Sign_up extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.createUserButton) Button mCreateUserButton;
    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @Bind(R.id.loginTextView) TextView mLogIn;

    //firebase authentication object
    private FirebaseAuth mAuth;
    public static final String TAG = Sign_up.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mLogIn.setOnClickListener(this);
        mCreateUserButton.setOnClickListener(this);
        //get instance of firebase authentication
        mAuth=FirebaseAuth.getInstance();
    }
    //on click listener
    @Override
    public void onClick(View view){
        //intent to go to sign in activity
        if(view == mLogIn){
            Intent intent=new Intent(getApplicationContext(),Sign_in.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if(view == mCreateUserButton){
            //create new user to firebase
            createNewUser();
        }
    }
    private void createNewUser(){
        //get input and save to variables
        final String name = mNameEditText.getText().toString().trim();
        final String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        //method to get email and password
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             if(task.isSuccessful()){
                 //check if authentication was successful
                 Log.d(TAG, "Authentication successful");
                 Intent intent=new Intent(getApplicationContext(),Dashboard_activity.class);
                 startActivity(intent);
             }
             else{
                 Toast.makeText(Sign_up.this,"Authentication failed",Toast.LENGTH_LONG).show();
             }
            }
        });


    }

}
