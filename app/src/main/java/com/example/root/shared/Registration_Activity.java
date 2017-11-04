package com.example.root.shared;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Registration_Activity extends AppCompatActivity implements View.OnClickListener{
@Bind(R.id.button2) Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);
        ButterKnife.bind(this);
        mButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if(view == mButton){
            Intent intent=new Intent(getApplicationContext(),SignupActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
