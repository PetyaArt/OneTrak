package com.example.company.onetrak.activity.main;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.company.onetrak.activity.goal.GoalActivity;
import com.example.company.onetrak.data.Fit;
import com.example.company.onetrak.network.RetrofitInstance;
import com.example.company.onetrak.util.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {


    private MainContract.View mView;
    private Context mContext;

    MainPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void takeView(MainContract.View view) {
        this.mView = view;
        updateData();
    }

    @Override
    public void dropView() {
        mView = null;
    }


    @Override
    public void updateData() {
        mView.showProgress();
        if (!Utils.isOnline((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE))) {
            mView.showNoInternet();
            return;
        }

        RetrofitInstance.getApiServer().getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonArray>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonArray jsonArray) {
                        Type fitType  = new TypeToken<ArrayList<Fit>>(){}.getType();
                        ArrayList<Fit> fit = new Gson().fromJson(jsonArray, fitType);
                        mView.setAdapter(fit);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }

    @Override
    public void setGoal() {
        Intent intent = new Intent(mContext, GoalActivity.class);
        mContext.startActivity(intent);
        mView.closeActivity();
    }
}
