package com.example.root.shared;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);

        SharedPreferences mSharedPreferences;

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mSharedPreferences = MainActivity.this.getSharedPreferences("account", Context.MODE_PRIVATE);
        final String missing = "missing";
        final String name = mSharedPreferences.getString("name", missing);
        final String email = mSharedPreferences.getString("email", missing);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT < 21) {
                    if(name == missing && email == missing){
                        Intent intent = new Intent(MainActivity.this, SelectLoginActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }
                } else {
                    getWindow().setExitTransition(new Explode());
                    if(name == missing && email == missing){
                        Intent intent = new Intent(MainActivity.this, SelectLoginActivity.class);
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                    } else {
                        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                    }
                }
            }
        }, 3000);
    }
}
