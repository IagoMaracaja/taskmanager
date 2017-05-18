package com.mobile.pos.iago.taskmanager.database.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mobile.pos.iago.taskmanager.database.helper.SQLiteHelper;
import com.mobile.pos.iago.taskmanager.models.Task;

/**
 * Created by iago on 18/05/17.
 */

public class TaskDBController {

    private SQLiteDatabase db;
    private SQLiteHelper helper;

    public TaskDBController(Context context){
        helper = new SQLiteHelper(context);
    }

    public void addNewTask(Task task){
        
    }

}
