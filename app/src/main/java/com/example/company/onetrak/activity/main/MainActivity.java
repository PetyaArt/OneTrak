package com.example.company.onetrak.activity.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.company.onetrak.R;
import com.example.company.onetrak.util.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mainFragment == null) {
            mainFragment = new MainFragment();
            Utils.addFragmentToActivity(
                    getSupportFragmentManager(), mainFragment, R.id.contentFrame);
        }

    }
}
