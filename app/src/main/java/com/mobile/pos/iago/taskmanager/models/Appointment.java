package com.mobile.pos.iago.taskmanager.models;

import com.mobile.pos.iago.taskmanager.views.activities.CreateTaskActivity;

/**
 * Created by iago on 22/05/17.
 */

public class Appointment {
    private int id;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentDate;
    private Boolean appointmentStatus;
    private String appointmentFinished;

    public Appointment(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Boolean getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(Boolean appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentFinished() {
        return appointmentFinished;
    }

    public void setAppointmentFinished(String appointmentFinished) {
        this.appointmentFinished = appointmentFinished;
    }
}
