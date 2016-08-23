package com.codeclan.example.lifetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by user on 21/08/2016.
 */
public class SavedTaskPreferences {
    private static final String TASKS = "tasks";

    public static void setStoredTasks(Context context, String text) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TASKS, text);
        editor.apply();

    }

    public static String getStoredTasks(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String text = sharedPreferences.getString(TASKS, null);
        return text;
    }

    public static void clearPrefs(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public static ArrayList<Task> convertToObjectArray(Context context, String json){
        Gson gson = new Gson();
        Type objectType = new TypeToken<ArrayList<Task>>() {
        }.getType();
        ArrayList<Task> taskArray = gson.fromJson(json, objectType);
        Log.d("Saved Tasks : ", taskArray + " array contents");
        return taskArray;
    }
}
