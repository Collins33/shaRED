package com.example.root.shared;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.createUserButton) Button mCreateUserButton;
    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @Bind(R.id.loginTextView) TextView mLogIn;

    private ProgressDialog mDialog;
    private FirebaseAuth.AuthStateListener mListener;

    //firebase authentication object
    private FirebaseAuth mAuth;
    public static final String TAG = SignupActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        //listen for authentication
        mListener=new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    Intent dashboard=new Intent(getApplicationContext(),DashboardActivity.class);
                    startActivity(dashboard);
                    finish();
                }
            }
        };

        createProgressDialog();
        mLogIn.setOnClickListener(this);
        mCreateUserButton.setOnClickListener(this);
        //get instance of firebase authentication
        mAuth=FirebaseAuth.getInstance();
    }
    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mListener);
    }
    @Override
    public void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(mListener);
    }
    //create the progress dialog
    public void createProgressDialog(){
        mDialog=new ProgressDialog(this);
        mDialog.setTitle("LOADING....");
        mDialog.setMessage("Authenticating account");
        mDialog.setCancelable(false);
    }
    //on click listener
    @Override
    public void onClick(View view){
        //intent to go to sign in activity
        if(view == mLogIn){
            Intent intent=new Intent(getApplicationContext(),SigninActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if(view == mCreateUserButton){
            //create new user to firebase
            createNewUser();
        }
    }
    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmailEditText.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mNameEditText.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPasswordEditText.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }
    private void createNewUser(){
        //get input and save to variables
        final String name = mNameEditText.getText().toString().trim();
        final String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();
        //variables to store boolean values
        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        //check if credentials are right
        if (!validEmail || !validName || !validPassword) return;
        mDialog.show();

        //method to get email and password
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.dismiss();
                if(task.isSuccessful()){
                    //check if authentication was successful
                    Log.d(TAG, "Authentication successful");
                    Intent intent=new Intent(getApplicationContext(),CreateProfileActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(SignupActivity.this,"Authentication failed",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}