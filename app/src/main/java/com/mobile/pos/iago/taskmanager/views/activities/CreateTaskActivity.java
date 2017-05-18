package com.mobile.pos.iago.taskmanager.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.pos.iago.taskmanager.R;
import com.mobile.pos.iago.taskmanager.database.controllers.TaskDBController;
import com.mobile.pos.iago.taskmanager.models.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by iago on 17/05/17.
 */

public class CreateTaskActivity extends AppCompatActivity {

    public enum Priority {
        High,
        Medium,
        Low;

    }

    @BindView(R.id.check_high)
    protected CheckBox mCheckBoxHigh;
    @BindView(R.id.check_medium)
    protected CheckBox mCheckBoxMedium;
    @BindView(R.id.check_low)
    protected CheckBox mCheckBoxLow;
    @BindView(R.id.error_message)
    protected TextView mErrorMessage;
    @BindView(R.id.et_task_name)
    protected EditText mEtTaskName;
    @BindView(R.id.et_task_description)
    protected EditText mEtTaskDescription;
    private Priority mPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task_activity);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.check_high)
    public void onClickHighPriority() {
        checkPriority(Priority.High);
    }

    @OnClick(R.id.check_medium)
    public void onClickMediumPriority() {
        checkPriority(Priority.Medium);
    }

    @OnClick(R.id.check_low)
    public void onClickLowPriority() {
        checkPriority(Priority.Low);
    }

    /**
     * Check priority action
     * @param priority
     */
    public void checkPriority(Priority priority) {
        if (priority == Priority.High) {
            this.mCheckBoxMedium.setChecked(false);
            this.mCheckBoxLow.setChecked(false);

        } else if (priority == Priority.Medium) {
            this.mCheckBoxHigh.setChecked(false);
            this.mCheckBoxLow.setChecked(false);
        } else {
            this.mCheckBoxHigh.setChecked(false);
            this.mCheckBoxMedium.setChecked(false);
        }
        this.mPriority = priority;
    }

    @OnClick(R.id.btn_create_task)
    public void createTaskButton(){
        if(this.checkTaskName()){
            this.mErrorMessage.setVisibility(View.INVISIBLE);
            this.createTask(this.mEtTaskName.getText().toString(),
                    mEtTaskDescription.getText().toString());
        }else{
            this.mErrorMessage.setVisibility(View.VISIBLE);
            this.mErrorMessage.setText(getString(R.string.task_name_error));
        }
    }

    @OnClick(R.id.btn_cancel_task)
    public void cancelTask(){
        CreateTaskActivity.this.finish();
    }

    /**
     * Checf if task name has valid entries
     * @return
     */
    public boolean checkTaskName(){
        if(this.mEtTaskName.getText().toString().equals("")){
            return false;
        }
        return true;
    }

    /**
     * Create task and save on db
     * @param taskName
     * @param taskDescription
     */
    public void createTask(String taskName, String taskDescription){
        Task newTask = new Task();
        newTask.setPriority(this.mPriority);
        newTask.setTaskTitle(taskName);
        newTask.setTaskDescription(taskDescription);
        newTask.setTaskStatus(false);

        TaskDBController db = new TaskDBController(this);
        boolean insertResult = db.addNewTask(newTask);

        if (insertResult){
            Toast.makeText(this, "Created task successfully", Toast.LENGTH_SHORT).show();
        }

        resetValues();
    }

    /**
     * Clear form data
     */
    private void resetValues(){
        this.mEtTaskName.setText(" ");
        this.mEtTaskDescription.setText(" ");
        this.mCheckBoxHigh.setChecked(false);
        this.mCheckBoxMedium.setChecked(false);
        this.mCheckBoxLow.setChecked(false);
    }



}
