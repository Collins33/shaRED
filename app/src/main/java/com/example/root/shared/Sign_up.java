package com.example.root.shared;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Sign_up extends AppCompatActivity implements View.OnClickListener{
@Bind(R.id.loginTextView) TextView mLogIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mLogIn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if(view == mLogIn){
            Intent intent=new Intent(getApplicationContext(),Sign_in.class);
            startActivity(intent);
            finish();
        }
    }
}
