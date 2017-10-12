package com.example.root.shared;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Sign_in extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.signUpTextView) TextView mRegisterTextView;
    @Bind(R.id.emailEditText) TextView mEmailTextView;
    @Bind(R.id.passwordEditText) TextView mPasswordTextView;
    @Bind(R.id.LogIn) TextView mLogInButton;
    private FirebaseAuth mAuth;
    public static final String TAG = Sign_up.class.getSimpleName();
    private FirebaseAuth.AuthStateListener mAuthstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        mRegisterTextView.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();
        mLogInButton.setOnClickListener(this);
        mAuthstate=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthstate);
    }
    @Override
    public void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(mAuthstate);
    }

    @Override
    public void onClick(View view){
        if(view==mRegisterTextView){
        Intent intent=new Intent(getApplicationContext(),Sign_up.class);
        startActivity(intent);
        finish();}
        if(view==mLogInButton){
            logInWithPassword();
        }

    }
    private void logInWithPassword(){
        String email=mEmailTextView.getText().toString().trim();
        String password=mPasswordTextView.getText().toString().trim();

        if(email.equals("")){
            mEmailTextView.setError("enter valid email");
            return;
        }
        if(password.equals("")){
            mPasswordTextView.setError("enter valid password");
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(!task.isSuccessful()){
                  Log.w(TAG, "signInWithEmail", task.getException());
                  Toast.makeText(Sign_in.this, "Authentication failed.",
                          Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
}
