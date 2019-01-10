package com.example.company.onetrak.activity.goal;

import com.example.company.onetrak.BasePresenter;
import com.example.company.onetrak.BaseView;
import com.example.company.onetrak.util.storage.Preferences;

class GoalContract {

    interface View extends BaseView<Presenter> {

        void showStepCounter(int progress);

        void closeActivity();
    }

    interface Presenter extends BasePresenter<View> {

        void changeStepCounter(int progress);

        void saveData(Preferences preferences, String s);
    }
}
