package com.example.root.shared;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Sign_in extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.signUpTextView) TextView mRegisterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        mRegisterTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Intent intent=new Intent(getApplicationContext(),Sign_up.class);
        startActivity(intent);
        finish();
    }
}
