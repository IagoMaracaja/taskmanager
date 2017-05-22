package com.mobile.pos.iago.taskmanager.database.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mobile.pos.iago.taskmanager.database.helper.SQLiteHelper;
import com.mobile.pos.iago.taskmanager.database.tables.TableAppointment;
import com.mobile.pos.iago.taskmanager.database.tables.TableTask;
import com.mobile.pos.iago.taskmanager.models.Appointment;
import com.mobile.pos.iago.taskmanager.models.Task;
import com.mobile.pos.iago.taskmanager.views.activities.CreateTaskActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by iago on 18/05/17.
 */

public class AppointmentDBController {

    private SQLiteDatabase db;
    private SQLiteHelper helper;

    public AppointmentDBController(Context context){
        helper = new SQLiteHelper(context);
    }

    /**
     * Create a new appointment
     * @param appointment the object reference of appointment
     * @return true if was saved on db, false otherwise
     */
    public boolean addNewAppointment(Appointment appointment){

        ContentValues values;
        long result;

        db = helper.getWritableDatabase();
        values = new ContentValues();
        values.put(TableAppointment.APPOINTMENT_TITLE, appointment.getAppointmentTitle());
        values.put(TableAppointment.APPOINTMENT_DESCRIPTION, appointment.getAppointmentDescription());
        values.put(TableAppointment.APPOINTMENT_DATE, appointment.getAppointmentDate());
        values.put(TableAppointment.APPOINTMENT_STATUS, appointment.getAppointmentStatus()? "1" : "0");

        result = db.insert(TableAppointment.TABLE_NAME, null, values);
        db.close();

        if (result == -1)
            return false;
        else
            return true;

    }

    /**
     * Get all appointments in database
     * @param withOnlyNotCompleted false case return must be all data
     * @return list of Appointments
     */
    public List<Appointment> getAllAppointments(boolean withOnlyNotCompleted){
        List<Appointment> appointments = new ArrayList<>();
        Cursor cursor;
        String[] fields =  {TableAppointment.ID,TableAppointment.APPOINTMENT_TITLE,TableAppointment.APPOINTMENT_DESCRIPTION,
                TableAppointment.APPOINTMENT_DATE, TableAppointment.APPOINTMENT_STATUS, TableAppointment.APPOINTMENT_DATE_FINISHED};
        db = helper.getReadableDatabase();
        if(withOnlyNotCompleted){
            cursor = db.query(TableAppointment.TABLE_NAME, fields, TableAppointment.APPOINTMENT_STATUS+"=?", new String[] { "0" }, null, null, null, null);
        }else{
            cursor = db.query(TableAppointment.TABLE_NAME, fields, null, null, null, null, null, null);
        }

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Appointment appointment = new Appointment();
                appointment.setId(cursor.getInt(cursor.getColumnIndex(TableAppointment.ID)));
                appointment.setAppointmentTitle(cursor.getString(cursor.getColumnIndex(TableAppointment.APPOINTMENT_TITLE)));
                appointment.setAppointmentDescription(cursor.getString(cursor.getColumnIndex(TableAppointment.APPOINTMENT_DESCRIPTION)));
                appointment.setAppointmentDate(cursor.getString(cursor.getColumnIndex(TableAppointment.APPOINTMENT_DATE)));

                String taskStatus = cursor.getString(cursor.getColumnIndex(TableAppointment.APPOINTMENT_STATUS));
                appointment.setAppointmentStatus( taskStatus.equals("1")? true: false);
                appointment.setAppointmentFinished(cursor.getString(cursor.getColumnIndex(TableAppointment.APPOINTMENT_DATE_FINISHED)));

                appointments.add(appointment);
                cursor.moveToNext();
            }
        }
        db.close();
        return appointments;
    }

    /**
     * Change the task status
     * @param id
     * @param checked
     */
    public boolean setAppointmentStatus(int id, boolean checked){
        ContentValues values;
        String where;

        db = helper.getWritableDatabase();

        where = TableTask.ID + "=" + id;

        String date = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());

        values = new ContentValues();
        values.put(TableAppointment.APPOINTMENT_STATUS, checked ? "1": "0");
        values.put(TableAppointment.APPOINTMENT_DATE_FINISHED, checked ? date : "");

        int result = db.update(TableAppointment.TABLE_NAME,values,where,null);
        db.close();
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }
}