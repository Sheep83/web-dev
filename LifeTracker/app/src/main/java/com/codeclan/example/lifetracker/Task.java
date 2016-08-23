package com.codeclan.example.lifetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by user on 21/08/2016.
 */
public class Task implements Doable {

    private String mTitle;
    private String mText;
    private String mCreatedDate;
    private String mType;
    private Boolean mComplete;
    private Date mDeadlineDate;
    private String mCompletedDate;

    public Task (String title, String type, String text, String initDate) {
        mTitle = title;
        mType = type;
        mText = text;
        mCreatedDate = initDate;
        mComplete = false;
        mDeadlineDate = null;
        mCompletedDate = null;

    }

    public String getType(){
        return this.mType;
    }

    public String getTitle(){
        return this.mTitle;
    }

    public void setTitle(String title){
        this.mTitle = title;
    }

    public String getText(){
        return this.mText;
    }

    public void setText(String text){
        this.mText = text;
    }

    public String getInitDate(){
        return this.mCreatedDate;
    }

    public void setCompleted(Boolean value){
        this.mComplete = value;
    }

    public String getCompletedDate(){
        return this.mCompletedDate;
    }

    public void setCompletedDate(String date){
        this.mCompletedDate = date;
    }

    public Boolean getCompleted(){
        return this.mComplete;
    }

}


