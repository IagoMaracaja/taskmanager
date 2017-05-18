package com.mobile.pos.iago.taskmanager.views.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mobile.pos.iago.taskmanager.R;
import com.mobile.pos.iago.taskmanager.views.fragments.TaskListFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by iago on 18/05/17.
 */

public class ViewTaskActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        ButterKnife.bind(this);

        inflateFragment();
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateFragment();
    }

    /**
     * Inflate Fragment
     */
    public void inflateFragment() {
        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.ll_fragment_list_view, new TaskListFragment());
        ft.commit();

    }

    public void updateFragment(){
        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.ll_fragment_list_view, new TaskListFragment());
        ft.commit();
    }

    @OnClick(R.id.btn_create_new_task)
    public void createNewTask() {
        Intent createTask = new Intent(ViewTaskActivity.this, CreateTaskActivity.class);
        startActivity(createTask);
    }
}
