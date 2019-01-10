package com.example.company.onetrak.activity.goal;

import android.content.Context;
import android.content.Intent;

import com.example.company.onetrak.activity.main.MainActivity;
import com.example.company.onetrak.util.storage.Preferences;

public class GoalPresenter implements GoalContract.Presenter {


    private GoalContract.View mView;

    private Context mContext;

    public GoalPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void takeView(GoalContract.View view) {
        this.mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void changeStepCounter(int progress) {
        mView.showStepCounter(progress);
    }

    @Override
    public void saveData(Preferences preferences, String s) {
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
        preferences.setGoal(s);
        mView.closeActivity();
    }


}
