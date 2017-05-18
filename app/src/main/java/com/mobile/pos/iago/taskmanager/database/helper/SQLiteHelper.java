package com.mobile.pos.iago.taskmanager.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobile.pos.iago.taskmanager.database.tables.TableTask;

/**
 * Created by iago on 18/05/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "banco.db";
    private static final int VERSION = 1;

    private String SQL_TASK = "CREATE TABLE "+ TableTask.TABLE_NAME+" (\n" +
            "                        "+TableTask.ID+"integer primary key autoincrement,\n" +
            "                        "+TableTask.TASK_TITLE+" text,\n" +
            "                        "+TableTask.TASK_DESCRIPTION+" text,\n" +
            "                        "+TableTask.TASK_PRIORITY+" text\n" +
            "                        )";


    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null ,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TableTask.TABLE_NAME);
        onCreate(db);
    }
}
