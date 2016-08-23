package com.codeclan.example.lifetracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by user on 22/08/2016.
 */
public class TaskEdit extends AppCompatActivity {
    int year, month, day;
    DatePicker mDatePicker;
    Button mChangeDate;
    Calendar mCalendar;
    EditText mTitleEdit;
    EditText mTextEdit;
    TextView mPageTitle;
    TextView mTitleText;
    TextView mDescriptionText;
    Button mApplyButton;
    Button mCompleteTaskButton;
    ArrayList<Task> savedTasks;
    Task selectedTask;
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        String savedJson = SavedTaskPreferences.getStoredTasks(this);
        savedTasks = SavedTaskPreferences.convertToObjectArray(this, savedJson);
        mPageTitle = (TextView) findViewById(R.id.page_header);
        mTitleEdit = (EditText) findViewById(R.id.task_title);
        mTextEdit = (EditText) findViewById(R.id.task_text);
        mDescriptionText = (TextView) findViewById(R.id.edit_text_header);
        mTitleText = (TextView) findViewById(R.id.edit_title_header);
        mApplyButton = (Button) findViewById(R.id.apply_button);
        mCompleteTaskButton = (Button) findViewById(R.id.complete_task_button);
        mCalendar = Calendar.getInstance();
        year = mCalendar.get(Calendar.YEAR);
        month = mCalendar.get(Calendar.MONTH);
        day = mCalendar.get(Calendar.DAY_OF_MONTH);
//        showDate(year, month+1, day);
        Typeface pencil_font = Typeface.createFromAsset(getAssets(), "fonts/pencil.ttf");
        mTitleEdit.setTypeface(pencil_font);
        mTextEdit.setTypeface(pencil_font);
        mDescriptionText.setTypeface(pencil_font);
        mTitleText.setTypeface(pencil_font);
        mPageTitle.setTypeface(pencil_font);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        json = extras.getString("object");
        Log.d("Object JSON String : ", "" + json);
        Gson gson = new Gson();
        selectedTask = gson.fromJson(json, Task.class);
        mTitleEdit.setText(selectedTask.getTitle());
        mTextEdit.setText(selectedTask.getText());

        mApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldTitle = selectedTask.getTitle();
                String oldText = selectedTask.getText();
                String newTitle = mTitleEdit.getText().toString();
                String newText = mTextEdit.getText().toString();
//                selectedTask.setText(newText);
//                selectedTask.setTitle(newTitle);
                for (Task task : savedTasks) {
                    if (task.getTitle().equals(oldTitle) && task.getText().equals(oldText)) {
                        task.setText(newText);
                        task.setTitle(newTitle);
                        String newJsonArrayString = new Gson().toJson(savedTasks);
                        SavedTaskPreferences.setStoredTasks(view.getContext(), newJsonArrayString);
                    }
                }
                Log.d("New Array : ", "" + savedTasks);
                Intent intent = new Intent(TaskEdit.this, LifeTracker.class);
                intent.putExtra("type", selectedTask.getType());
                Log.d("LifeTracker:", " Edited, returning to main menu...");
                startActivity(intent);
            }
        });

        mCompleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldTitle = selectedTask.getTitle();
                String oldText = selectedTask.getText();
                String newTitle = mTitleEdit.getText().toString();
                String newText = mTextEdit.getText().toString();
//                selectedTask.setText(newText);
                for (Task task : savedTasks) {
                    if (task.getTitle().equals(oldTitle) && task.getText().equals(oldText)) {
                        if(task.getCompleted() == false) {
                            task.setCompleted(true);
                            String completedDate = getCurrentDate();
                            task.setCompletedDate(completedDate);

                        }else {
                            task.setCompleted(false);
                        }
                        String newJsonArrayString = new Gson().toJson(savedTasks);
                        SavedTaskPreferences.setStoredTasks(view.getContext(), newJsonArrayString);
                    }
                }
                Intent intent = new Intent(TaskEdit.this, LifeTracker.class);
                Log.d("LifeTracker:", " Task marked completed, returning to main menu...");
                startActivity(intent);
            }
            public String getCurrentDate(){
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                return df.format(c.getTime());
            }
        });


//        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                // TODO Auto-generated method stub
//                mCalendar.set(Calendar.YEAR, year);
//                mCalendar.set(Calendar.MONTH, monthOfYear);
//                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
////                updateLabel();
//            }
//
//        };

//        mChangeDate.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                new DatePickerDialog(TaskEdit.this, date, mCalendar
//                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
//                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });

    }
}

