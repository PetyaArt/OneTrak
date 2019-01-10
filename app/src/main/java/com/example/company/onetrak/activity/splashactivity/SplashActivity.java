package com.example.company.onetrak.activity.splashactivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.company.onetrak.R;
import com.example.company.onetrak.activity.goal.GoalActivity;
import com.example.company.onetrak.activity.main.MainActivity;
import com.example.company.onetrak.util.storage.Preferences;


public class SplashActivity extends AppCompatActivity {

    public static final int SPLASH_DURATION = 3000;

    private ConstraintLayout mConstraintLayout;
    private AnimationDrawable mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mConstraintLayout = findViewById(R.id.constraint);
        ImageView imageViewAnim = findViewById(R.id.image_view_anim);
        imageViewAnim.setBackgroundResource(R.drawable.animation);
        mAnimation = (AnimationDrawable) imageViewAnim.getBackground();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAnimation.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initFunctionality();
    }

    private void initFunctionality() {
        mConstraintLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (new Preferences(getApplicationContext()).getGoal().isEmpty()) {
                    startActivity(new Intent(SplashActivity.this, GoalActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        }, SPLASH_DURATION);
    }



}
