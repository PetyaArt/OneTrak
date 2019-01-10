package com.example.company.onetrak.util.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private final static String FILE_NAME = "preferences";

    private final static String GOAL = "goal";

    private SharedPreferences preferences;

    public Preferences(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, 0);
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void setGoal(String data) {
        getEditor().putString(GOAL, data).commit();
    }

    public String getGoal() {
        return preferences.getString(GOAL, "");
    }

}
