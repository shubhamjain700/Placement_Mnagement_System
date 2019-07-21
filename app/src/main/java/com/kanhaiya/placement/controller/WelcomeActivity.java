package com.kanhaiya.placement.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.kanhaiya.placement.R;

public class WelcomeActivity extends AppCompatActivity {
    private final Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, SignInActivity.class));
            }
        };
        handler.postDelayed(runnable,3000);
    }
}
