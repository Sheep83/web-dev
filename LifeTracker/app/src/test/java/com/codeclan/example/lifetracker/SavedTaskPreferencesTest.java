package com.codeclan.example.lifetracker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * Created by user on 21/08/2016.
 */
public class SavedTaskPrefencesTest {
    @Before{
        Task task = new Task ("test_title", "test_type", "test_text", "test_initdate");
        ArrayList<Task> = new ArrayList<Task>();

    }
    @Test
    public void addObjectTest()
    {
        SavedTaskPreferences.setStoredTasks();
        assertEquals("test_title", task.getTitle());
    }
}
