package com.example.githubusers.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.githubusers.MainActivity;
import com.example.githubusers.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //membuatsplashscreen dengan delay 3 detik

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //yg terjadi setelah disetting delay 3 detik
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                //splashscreen hilang
                finish();
            }
        }, 3000);
    }
}
