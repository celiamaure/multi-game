package fr.ipac.multigame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import fr.ipac.multigame.R;
import fr.ipac.multigame.utils.ActivityUtils;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityUtils.launchActivity(SplashScreenActivity.this, CreatePlayerActivity.class, ActivityUtils.TYPE_FADE);
            }
        }, 3000);
    }
}
