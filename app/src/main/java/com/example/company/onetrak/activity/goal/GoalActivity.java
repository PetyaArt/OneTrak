package com.example.company.onetrak.activity.goal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.company.onetrak.R;
import com.example.company.onetrak.util.Utils;

public class GoalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        GoalFragment goalFragment =
                (GoalFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (goalFragment == null) {
            goalFragment = new GoalFragment();
            Utils.addFragmentToActivity(
                    getSupportFragmentManager(), goalFragment, R.id.contentFrame);
        }
    }
}
