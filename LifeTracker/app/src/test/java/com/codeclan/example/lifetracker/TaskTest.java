package com.codeclan.example.lifetracker;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by user on 21/08/2016.
 */
public class TaskTest {

    @Test
    public void getTitleTest()
    {
        Task task = new Task ("test_title", "test_type", "test_text", "test_initdate");
        assertEquals("test_title", task.getTitle());
    }

    @Test
    public void getTypeTest()
    {
        Task task = new Task ("test_title", "test_type", "test_text", "test_initdate");
        assertEquals("test_type", task.getType());
    }

    @Test
    public void getTextTest()
    {
        Task task = new Task ("test_title", "test_type", "test_text", "test_initdate");
        assertEquals("test_text", task.getText());
    }

    @Test
    public void getInitDateTest()
    {
        Task task = new Task ("test_title", "test_type", "test_text", "test_initdate");
        assertEquals("test_initdate", task.getInitDate());
    }
}
