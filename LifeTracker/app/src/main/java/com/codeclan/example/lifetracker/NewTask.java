package com.codeclan.example.lifetracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by user on 21/08/2016.
 */
public class NewTask extends AppCompatActivity {

    TextView mPageHeader;
    ListView mListView;
    Button mAddButton;
    Button mClearButton;
    EditText mTaskTitle;
    EditText mTaskText;
    String mTaskType;
    ArrayList<Task> mTaskArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        String json_saved = SavedTaskPreferences.getStoredTasks(this);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mTaskType = extras.getString("type");
        if(json_saved == null) {
            mTaskArray = new ArrayList<>();
        }
        else {
            Log.d("Array String : ", json_saved);
            mTaskArray = SavedTaskPreferences.convertToObjectArray(this, json_saved);
        }
        mPageHeader = (TextView) findViewById(R.id.new_task_header);
        mAddButton = (Button)findViewById(R.id.add_task_button);
        mClearButton = (Button)findViewById(R.id.clear_mem_button);
        mTaskTitle = (EditText)findViewById(R.id.task_title);
        mTaskText = (EditText)findViewById(R.id.task_text);
        mListView = (ListView)findViewById(R.id.task_list_view);
        Typeface pencil_font = Typeface.createFromAsset(getAssets(), "fonts/pencil.ttf");
        mTaskTitle.setTypeface(pencil_font);
        mTaskText.setTypeface(pencil_font);
        mPageHeader.setTypeface(pencil_font);


        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                String json_saved = SavedTaskPreferences.getStoredTasks(context);
                if(json_saved == null){
                    mTaskArray = new ArrayList<>();
                }
                else {
                    Gson gson = new Gson();
                    Type objectType = new TypeToken<ArrayList<Task>>() {
                    }.getType();
                    mTaskArray = gson.fromJson(json_saved, objectType);
                    Log.d("Saved Tasks : ", mTaskArray + " array contents");
                }

                String newTitle = mTaskTitle.getText().toString();
                String newText = mTaskText.getText().toString();
                String newType = mTaskType;
                String newDate = getCurrentDate();
                Intent newIntent = new Intent(NewTask.this, LifeTracker.class);
                Task task = new Task(newTitle, newType, newText, newDate);
                mTaskArray.add(task);
                String json = new Gson().toJson(mTaskArray);
                Log.d("New Task Array : ", json + " array contents");
                SavedTaskPreferences.setStoredTasks(context, json);
                newIntent.putExtra("array_string", json);
                startActivity(newIntent);
            }
            public String getCurrentDate(){
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                return df.format(c.getTime());
            }
        });
    }

}
