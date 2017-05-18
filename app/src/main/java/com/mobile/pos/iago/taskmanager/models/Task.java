package com.mobile.pos.iago.taskmanager.models;

import com.mobile.pos.iago.taskmanager.views.CreateTaskActivity;

/**
 * Created by iago on 17/05/17.
 */

public class Task {

    private CreateTaskActivity.Priority priority;
    private String name;

    public Task(){}

    public CreateTaskActivity.Priority getPriority() {
        return priority;
    }

    public void setPriority(CreateTaskActivity.Priority priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
