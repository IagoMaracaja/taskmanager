package com.mobile.pos.iago.taskmanager.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mobile.pos.iago.taskmanager.R;

import butterknife.ButterKnife;

/**
 * Created by iago on 17/05/17.
 */

public class CreateTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task_activity);
        ButterKnife.bind(this);



    }
}
