package com.mobile.pos.iago.taskmanager.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobile.pos.iago.taskmanager.database.tables.TableAppointment;
import com.mobile.pos.iago.taskmanager.database.tables.TableTask;

/**
 * Created by iago on 18/05/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "taskmanager.db";
    private static final int VERSION = 1;

    private String SQL_TASK = "CREATE TABLE "+ TableTask.TABLE_NAME+" (\n" +
            "                        "+TableTask.ID+" integer primary key autoincrement,\n" +
            "                        "+TableTask.TASK_TITLE+" text,\n" +
            "                        "+TableTask.TASK_DESCRIPTION+" text,\n" +
            "                        "+TableTask.TASK_PRIORITY+" text,\n" +
            "                        "+TableTask.TASK_STATUS+" text,\n" +
            "                        "+TableTask.TASK_DATE_FINISHED+" text\n" +
            "                        )";

    private String SQL_APPOINTMENT = "CREATE TABLE "+ TableAppointment.TABLE_NAME+" (\n" +
            "                        "+ TableAppointment.ID+" integer primary key autoincrement,\n" +
            "                        "+ TableAppointment.APPOINTMENT_TITLE +" text,\n" +
            "                        "+ TableAppointment.APPOINTMENT_DESCRIPTION +" text,\n" +
            "                        "+ TableAppointment.APPOINTMENT_DATE +" text,\n" +
            "                        "+ TableAppointment.APPOINTMENT_STATUS +" text,\n" +
            "                        "+ TableAppointment.APPOINTMENT_DATE_FINISHED +" text\n" +
            "                        )";


    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null ,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TASK);
        db.execSQL(SQL_APPOINTMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableTask.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TableAppointment.TABLE_NAME);
        onCreate(db);
    }
}
