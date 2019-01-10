package com.example.company.onetrak.activity.main;

import com.example.company.onetrak.BasePresenter;
import com.example.company.onetrak.BaseView;
import com.example.company.onetrak.data.Fit;

import java.util.ArrayList;

class MainContract {

    interface View extends BaseView<Presenter> {

        void showProgress();

        void hideProgress();

        void showNoInternet();

        void showError();

        void setAdapter(ArrayList<Fit> fit);

        void closeActivity();

    }

    interface Presenter extends BasePresenter<View> {

        void updateData();

        void setGoal();
    }
}
