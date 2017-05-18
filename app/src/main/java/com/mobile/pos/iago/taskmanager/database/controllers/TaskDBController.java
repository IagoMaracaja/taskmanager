package com.mobile.pos.iago.taskmanager.database.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mobile.pos.iago.taskmanager.database.helper.SQLiteHelper;
import com.mobile.pos.iago.taskmanager.database.tables.TableTask;
import com.mobile.pos.iago.taskmanager.models.Task;
import com.mobile.pos.iago.taskmanager.views.activities.CreateTaskActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iago on 18/05/17.
 */

public class TaskDBController {

    private SQLiteDatabase db;
    private SQLiteHelper helper;

    public TaskDBController(Context context){
        helper = new SQLiteHelper(context);
    }

    /**
     * Create a new task
     * @param task the object reference of task
     * @return true if was saved on db, false otherwise
     */
    public boolean addNewTask(Task task){

        ContentValues values;
        long result;

        db = helper.getWritableDatabase();
        values = new ContentValues();
        values.put(TableTask.TASK_TITLE, task.getTaskTitle());
        values.put(TableTask.TASK_DESCRIPTION, task.getTaskDescription());
        values.put(TableTask.TASK_PRIORITY, task.getPriority().toString());
        values.put(TableTask.TASK_STATUS, task.getTaskStatus()? "1" : "0");

        result = db.insert(TableTask.TABLE_NAME, null, values);
        db.close();

        if (result == -1)
            return false;
        else
            return true;

    }

    /**
     * Get all task in database
     * @return
     */
    public List<Task> getAllTask(){
        List<Task> tasks = new ArrayList<>();
        Cursor cursor;
        String[] fields =  {TableTask.ID,TableTask.TASK_TITLE,TableTask.TASK_DESCRIPTION,TableTask.TASK_PRIORITY, TableTask.TASK_STATUS};
        db = helper.getReadableDatabase();
        cursor = db.query(TableTask.TABLE_NAME, fields, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex(TableTask.ID)));
                task.setTaskTitle(cursor.getString(cursor.getColumnIndex(TableTask.TASK_TITLE)));
                task.setTaskDescription(cursor.getString(cursor.getColumnIndex(TableTask.TASK_DESCRIPTION)));

                String priority = cursor.getString(cursor.getColumnIndex(TableTask.TASK_PRIORITY));

                if (priority.equals(CreateTaskActivity.Priority.High.toString())) {
                    task.setPriority(CreateTaskActivity.Priority.High);
                } else if (priority.equals(CreateTaskActivity.Priority.Medium.toString())) {
                    task.setPriority(CreateTaskActivity.Priority.Medium);
                } else {
                    task.setPriority(CreateTaskActivity.Priority.Low);
                }
                String taskStatus = cursor.getString(cursor.getColumnIndex(TableTask.TASK_STATUS));
                task.setTaskStatus( taskStatus == "1"? true: false);

                tasks.add(task);
                cursor.moveToNext();
            }
        }
        db.close();
        return tasks;
    }
}