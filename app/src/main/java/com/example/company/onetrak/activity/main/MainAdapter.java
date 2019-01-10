package com.example.company.onetrak.activity.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.company.onetrak.R;
import com.example.company.onetrak.data.Fit;
import com.txusballesteros.widgets.FitChart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Fit> mFit;
    private Integer mGoal;

    MainAdapter(ArrayList<Fit> fit, String goal) {
        mFit = fit;
        mGoal = Integer.parseInt(goal);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_steps, viewGroup,  false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(mFit.get(i));
    }

    @Override
    public int getItemCount() {
        return mFit.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        FitChart large;
        FitChart middle;
        FitChart small;

        TextView time;
        TextView currentStep;

        TextView walk;
        TextView aerobic;
        TextView run;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            large = itemView.findViewById(R.id.fit_large);
            middle = itemView.findViewById(R.id.fit_middle);
            small = itemView.findViewById(R.id.fit_small);

            large.setMaxValue(mGoal);
            middle.setMaxValue(mGoal);
            small.setMaxValue(mGoal);

            time = itemView.findViewById(R.id.text_view_time);
            currentStep = itemView.findViewById(R.id.text_view_cur_step);
            walk = itemView.findViewById(R.id.text_view_walk);
            aerobic = itemView.findViewById(R.id.text_view_aerobic);
            run = itemView.findViewById(R.id.text_view_run);
        }

        void bind(Fit fit) {
            large.setValue(fit.getWalk());
            middle.setValue(fit.getAerobic());
            small.setValue(fit.getRun());

            walk.setText(String.valueOf(fit.getWalk()));
            aerobic.setText(String.valueOf(fit.getAerobic()));
            run.setText(String.valueOf(fit.getRun()));

            time.setText(getTime(fit));
            currentStep.setText(getCurrentStep(fit));

            walk.setText(String.valueOf(fit.getWalk()));
            aerobic.setText(String.valueOf(fit.getAerobic()));
            run.setText(String.valueOf(fit.getRun()));
        }

        String getTime(Fit fit) {
            Long yourSeconds = fit.getDate();
            Date date = new Date(yourSeconds);
            SimpleDateFormat  df = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
            return df.format(date);
        }

        String getCurrentStep(Fit fit) {
            Integer sum = fit.getWalk() + fit.getAerobic() + fit.getRun();
            return mGoal + " / " +  sum + " steps";
        }
    }
}
