package com.codeclan.example.lifetracker;

import android.app.LauncherActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;

/**
 * Created by user on 21/08/2016.
 */
public class TaskList extends AppCompatActivity {
    ListView mListView;
    ArrayList<Task> mTaskArray;
    ArrayList<Task> mTaskTypedArray;
    TextView mHeaderText;
    ImageButton mAddTaskButton;
    String taskType;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);
        Typeface pencil = Typeface.createFromAsset(getAssets(), "fonts/pencil.ttf");
        mHeaderText = (TextView)findViewById(R.id.task_list);
        mHeaderText.setTypeface(pencil);
        mTaskTypedArray = new ArrayList<Task>();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        taskType = extras.getString("type");
        String type = (taskType + " Stuff");
        mHeaderText.setText(type);
        String json_saved = SavedTaskPreferences.getStoredTasks(this);
        if(json_saved == null) {
            mTaskArray = new ArrayList<>();
            mListView = (ListView)findViewById(R.id.task_list_view);
        }
        else {
            Log.d("Array String : ", json_saved);
            mTaskArray = SavedTaskPreferences.convertToObjectArray(this, json_saved);
            mListView = (ListView)findViewById(R.id.task_list_view);
        }
        for(Task task : mTaskArray){
            if(task.getType().equals(taskType)){
                mTaskTypedArray.add(task);
            }
        }
        ArrayAdapter<Task> arrayAdapter = new TaskArrayAdapter(this, android.R.layout.simple_list_item_1, mTaskTypedArray);
//        ArrayAdapter<Task> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, mTaskArray);
        mListView.setAdapter(arrayAdapter);
        mAddTaskButton = (ImageButton)findViewById(R.id.add_task_button);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
                Task selectedTask = mTaskTypedArray.get(position);
                Intent intent = new Intent(TaskList.this, TaskView.class);
                String json = new Gson().toJson(selectedTask);
                intent.putExtra("object", json);
                intent.putExtra("type", taskType);
                Log.d("LifeTracker:", " Viewing individual task...");
                Toast.makeText(getApplicationContext(), "Task Selected : " + selectedTask.getTitle(), Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });

        mAddTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.bounce));
                Intent intent = new Intent(TaskList.this, NewTask.class);
                intent.putExtra("type", taskType);
                Log.d("LifeTracker:", " New Task started...");
                startActivity(intent);
            }
        });
    }

}
