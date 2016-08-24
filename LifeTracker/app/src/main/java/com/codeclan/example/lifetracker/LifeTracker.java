package com.codeclan.example.lifetracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 21/08/2016.
 */
public class LifeTracker extends AppCompatActivity {
    TextView mTitleText, mBoringText, mWorkText, mSocialText, mRecText, mLifeText, mClearText;
//    TextView mBoringText;
//    TextView mWorkText;
//    TextView mSocialText;
//    TextView mRecText;
//    TextView mLifeText;
//    TextView mClearText;
    ImageButton mBoringButton;
    ImageButton mSocialButton;
    ImageButton mWorkButton;
    ImageButton mRecButton;
    ImageButton mLifeButton;
    Button mClearMemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitleText = (TextView) findViewById(R.id.title_text);
        mBoringButton = (ImageButton)findViewById(R.id.boring_button);
        mWorkButton = (ImageButton)findViewById(R.id.work_button);
        mSocialButton = (ImageButton)findViewById(R.id.social_button);
        mRecButton = (ImageButton)findViewById(R.id.recreation_button);
        mLifeButton = (ImageButton)findViewById(R.id.life_button);
        mClearMemButton = (Button)findViewById(R.id.clear_mem_button);
        mBoringText = (TextView) findViewById(R.id.boring_text);
        mWorkText = (TextView) findViewById(R.id.work_text);
        mSocialText = (TextView) findViewById(R.id.social_text);
        mRecText = (TextView) findViewById(R.id.recreation_text);
        mLifeText = (TextView) findViewById(R.id.life_text);
        Typeface pencil_font = Typeface.createFromAsset(getAssets(), "fonts/pencil.ttf");
        mTitleText.setTypeface(pencil_font);
        mBoringText.setTypeface(pencil_font);
        mWorkText.setTypeface(pencil_font);
        mSocialText.setTypeface(pencil_font);
        mRecText.setTypeface(pencil_font);
        mLifeText.setTypeface(pencil_font);

        mBoringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.bounce));
                Intent intent = new Intent(LifeTracker.this, TaskList.class);
                intent.putExtra("type", "Boring");
                Log.d("LifeTracker:", "Boring Button Clicked!");
                startActivity(intent);
            }
        });

        mWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.bounce));
                Intent intent = new Intent(LifeTracker.this, TaskList.class);
                intent.putExtra("type", "Work");
                Log.d("LifeTracker: ", "Work Button Clicked!");
                startActivity(intent);
            }
        });

        mSocialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.bounce));
                Intent intent = new Intent(LifeTracker.this, TaskList.class);
                intent.putExtra("type", "Social");
                Log.d("LifeTracker: ", "Social Button Clicked!");
                startActivity(intent);
            }
        });

        mRecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.bounce));
                Intent intent = new Intent(LifeTracker.this, TaskList.class);
                intent.putExtra("type", "Recreation");
                Log.d("LifeTracker: ", "Recreation Button Clicked!");
                startActivity(intent);
            }
        });

        mLifeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.bounce));
                Intent intent = new Intent(LifeTracker.this, TaskList.class);
                intent.putExtra("type", "Life");
                Log.d("LifeTracker: ", "Life Button Clicked!");
                startActivity(intent);
            }
        });

        mClearMemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                SavedTaskPreferences.clearPrefs(context);
                Toast.makeText(getApplicationContext(), "Memory Wiped!", Toast.LENGTH_SHORT).show();
                Log.d("LifeTracker:", "Memory Cleared!");
            }
        });

    }


}

