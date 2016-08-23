package com.codeclan.example.lifetracker;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by user on 22/08/2016.
 */

class TaskArrayAdapter extends ArrayAdapter<Task> {

    private Context context;
    private ArrayList<Task> savedTasks;
    Typeface pencil;


    //constructor
    public TaskArrayAdapter(Context context, int resource, ArrayList<Task> objects) {
        super(context, resource, objects);
        this.context = context;
        this.savedTasks = objects;
    }

    //called when rendering the list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = savedTasks.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.task_layout, null);
        Context context = view.getContext();
        pencil = Typeface.createFromAsset(context.getAssets(), "fonts/pencil.ttf");
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView text = (TextView) view.findViewById(R.id.text);
        title.setTypeface(pencil);
        text.setTypeface(pencil);
        title.setText(task.getTitle());
        int textLength = task.getText().length();
        if(textLength >= 100){
            String textTrim = task.getText().substring(0, 100) + "...";
            text.setText(textTrim);
        }else{
            text.setText(task.getText());
        }

        return view;
    }


}
