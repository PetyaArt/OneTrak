package com.example.company.onetrak.activity.goal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.company.onetrak.R;
import com.example.company.onetrak.util.storage.Preferences;
import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.marcinmoskala.arcseekbar.ProgressListener;

public class GoalFragment extends Fragment implements GoalContract.View, ProgressListener{

    GoalContract.Presenter mPresenter;

    private TextView mCounterGoal;

    public GoalFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPresenter = new GoalPresenter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.dropView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_goal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArcSeekBar arcSeekBar = view.findViewById(R.id.seek_goal);
        mCounterGoal = view.findViewById(R.id.counter);

        arcSeekBar.setMaxProgress(10000);
        arcSeekBar.setOnProgressChangedListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_goal, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (getActivity() == null)
            return false;
        switch (item.getItemId()) {
            case R.id.save:
                mPresenter.saveData(
                        new Preferences(getActivity()),
                        String.valueOf(mCounterGoal.getText()));
                break;
        }
        return true;
    }

    @Override
    public void invoke(int progress) {
        mPresenter.changeStepCounter(progress);
    }

    @Override
    public void showStepCounter(int progress) {
        mCounterGoal.setText(String.valueOf(Math.round(progress / 1000) * 1000));
    }

    @Override
    public void closeActivity() {
        if (getActivity() != null)
            getActivity().finish();
    }
}
