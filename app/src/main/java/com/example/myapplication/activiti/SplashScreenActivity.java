package com.example.myapplication.activiti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;

import com.example.myapplication.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(
                        new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        },1500);
    }
}

