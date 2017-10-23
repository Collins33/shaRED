package com.example.root.shared;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ThankyouActivity extends AppCompatActivity implements View.OnClickListener{
@Bind(R.id.backHome) Button mBackHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
        ButterKnife.bind(this);
        mBackHome.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}
