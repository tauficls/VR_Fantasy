package com.taufic.vr_fantasy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.taufic.vr_fantasy.R;

import butterknife.BindView;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.splashscreen)
    ImageView mSplashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), VR_FantasyActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
