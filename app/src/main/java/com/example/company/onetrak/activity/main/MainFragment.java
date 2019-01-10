package com.example.company.onetrak.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.company.onetrak.R;
import com.example.company.onetrak.data.Fit;
import com.example.company.onetrak.util.storage.Preferences;

import java.util.ArrayList;

public class MainFragment extends Fragment implements MainContract.View{

    MainContract.Presenter mPresenter;

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;

    public MainFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPresenter = new MainPresenter(getContext());
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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgressBar = view.findViewById(R.id.progress_bar);
        mRecyclerView = view.findViewById(R.id.recycler_steps);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (getActivity() == null)
            return false;
        switch (item.getItemId()) {
            case R.id.update:
                mPresenter.updateData();
                break;
            case R.id.set_goal:
                mPresenter.setGoal();
                break;
        }
        return true;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNoInternet() {
        showMessage(getString(R.string.no_internet));
    }

    @Override
    public void showError() {
        showMessage(getString(R.string.error));
    }

    private void showMessage(String message) {
        if (getActivity() != null) {
            Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setAdapter(ArrayList<Fit> fit) {
        if (getContext() == null)
            return;
        MainAdapter mainAdapter = new MainAdapter(fit, new Preferences(getContext()).getGoal());
        mRecyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void closeActivity() {
        if (getActivity() != null)
            getActivity().finish();
    }
}
