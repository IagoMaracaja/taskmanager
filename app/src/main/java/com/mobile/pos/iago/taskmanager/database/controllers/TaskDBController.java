package com.mobile.pos.iago.taskmanager.database.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mobile.pos.iago.taskmanager.database.helper.SQLiteHelper;
import com.mobile.pos.iago.taskmanager.database.tables.TableTask;
import com.mobile.pos.iago.taskmanager.models.Task;
import com.mobile.pos.iago.taskmanager.views.activities.CreateTaskActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
     * @param withOnlyNotCompleted false case return must be all data
     * @return list of Tasks
     */
    public List<Task> getAllTask(boolean withOnlyNotCompleted){
        List<Task> tasks = new ArrayList<>();
        Cursor cursor;
        String[] fields =  {TableTask.ID,TableTask.TASK_TITLE,TableTask.TASK_DESCRIPTION,TableTask.TASK_PRIORITY, TableTask.TASK_STATUS, TableTask.TASK_DATE_FINISHED};
        db = helper.getReadableDatabase();
        if(withOnlyNotCompleted){
            cursor = db.query(TableTask.TABLE_NAME, fields, TableTask.TASK_STATUS+"=?", new String[] { "0" }, null, null, null, null);
        }else{
            cursor = db.query(TableTask.TABLE_NAME, fields, null, null, null, null, null, null);
        }

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
                task.setTaskStatus( taskStatus.equals("1")? true: false);
                task.setTaskFinished(cursor.getString(cursor.getColumnIndex(TableTask.TASK_DATE_FINISHED)));
                tasks.add(task);
                cursor.moveToNext();
            }
        }
        db.close();
        return tasks;
    }

    /**
     * Change the task status
     * @param id
     * @param checked
     */
    public boolean setTaskStatus(int id, boolean checked){
        ContentValues values;
        String where;

        db = helper.getWritableDatabase();

        where = TableTask.ID + "=" + id;

        String date = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());

        values = new ContentValues();
        values.put(TableTask.TASK_STATUS, checked ? "1": "0");
        values.put(TableTask.TASK_DATE_FINISHED, checked ? date : "");

        int result = db.update(TableTask.TABLE_NAME,values,where,null);
        db.close();
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }
}