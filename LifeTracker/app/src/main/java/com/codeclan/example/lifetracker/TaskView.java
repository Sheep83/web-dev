package com.codeclan.example.lifetracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import java.util.ArrayList;

/**
 * Created by user on 22/08/2016.
 */
public class TaskView extends AppCompatActivity {
    TextView mTitleText;
    TextView mDescriptionText;
    TextView mCompleted;
    ImageButton mEditTaskButton;
    ImageButton mDeleteTaskButton;
    ArrayList<Task> savedTasks;
    Task selectedTask;
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
        String savedJson = SavedTaskPreferences.getStoredTasks(this);
        savedTasks = SavedTaskPreferences.convertToObjectArray(this, savedJson);
        mTitleText = (TextView) findViewById(R.id.title);
        mDescriptionText = (TextView) findViewById(R.id.text);
        mCompleted = (TextView) findViewById(R.id.complete);
        mEditTaskButton = (ImageButton) findViewById(R.id.edit_task_button);
        mDeleteTaskButton = (ImageButton) findViewById(R.id.delete_task_button);
        Typeface pencil_font = Typeface.createFromAsset(getAssets(), "fonts/pencil.ttf");
        mTitleText.setTypeface(pencil_font);
        mDescriptionText.setTypeface(pencil_font);
        mCompleted.setTypeface(pencil_font);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        json = extras.getString("object");
        Log.d("Object JSON String : ", "" + json);
        Gson gson = new Gson();
        selectedTask = gson.fromJson(json, Task.class);
        mTitleText.setText(selectedTask.getTitle());
        mDescriptionText.setText(selectedTask.getText());
        if (!selectedTask.getCompleted()){
            mCompleted.setText("Not yet Complete");
        }else{
            mCompleted.setText("Completed on " + selectedTask.getCompletedDate());
        }

        mDeleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0; i<savedTasks.size(); i++){
                    if (savedTasks.get(i).getText().equals(selectedTask.getText())) {
                        savedTasks.remove(savedTasks.get(i));
                        String newJsonArrayString = new Gson().toJson(savedTasks);
                        SavedTaskPreferences.setStoredTasks(view.getContext(), newJsonArrayString);
                    }
                }
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.bounce));
                Log.d("New Array : ", "" + savedTasks);
                Intent intent = new Intent(TaskView.this, LifeTracker.class);
                intent.putExtra("object", json);
                Log.d("LifeTracker:", " Deleted, returning to main menu...");

                startActivity(intent);
            }
        });

        mEditTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.bounce));
                Log.d("New Array : ", "" + savedTasks);
                Intent intent = new Intent(TaskView.this, TaskEdit.class);
                intent.putExtra("object", json);
                Log.d("LifeTracker:", " Edit menu incoming...");
                startActivity(intent);
            }
        });
    }
}
