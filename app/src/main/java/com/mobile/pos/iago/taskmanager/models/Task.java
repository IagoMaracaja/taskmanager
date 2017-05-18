package com.mobile.pos.iago.taskmanager.models;

import com.mobile.pos.iago.taskmanager.views.CreateTaskActivity;

/**
 * Created by iago on 17/05/17.
 */

public class Task {
    private int id;
    private CreateTaskActivity.Priority priority;
    private String taskTitle;
    private String taskDescription;
    private Boolean taskStatus;

    public Task(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CreateTaskActivity.Priority getPriority() {
        return priority;
    }

    public void setPriority(CreateTaskActivity.Priority priority) {
        this.priority = priority;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Boolean getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Boolean taskStatus) {
        this.taskStatus = taskStatus;
    }
}
