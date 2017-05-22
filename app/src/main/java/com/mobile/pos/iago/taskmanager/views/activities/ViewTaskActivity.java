package com.mobile.pos.iago.taskmanager.views.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mobile.pos.iago.taskmanager.R;
import com.mobile.pos.iago.taskmanager.views.fragments.TaskListFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mobile.pos.iago.taskmanager.views.fragments.TaskListFragment.BUNDLE_TASK_NOT_COMPLETED;

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

        TaskListFragment taskListFragment = new TaskListFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean(BUNDLE_TASK_NOT_COMPLETED, false);
        taskListFragment.setArguments(bundle);

        ft.add(R.id.ll_fragment_list_view, taskListFragment);
        ft.commit();

    }

    public void updateFragment() {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        TaskListFragment taskListFragment = new TaskListFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean(BUNDLE_TASK_NOT_COMPLETED, false);
        taskListFragment.setArguments(bundle);

        ft.replace(R.id.ll_fragment_list_view, taskListFragment);
        ft.commit();
    }

    @OnClick(R.id.btn_create_new_task)
    public void createNewTask() {
        Intent createTask = new Intent(ViewTaskActivity.this, CreateTaskActivity.class);
        startActivity(createTask);
    }
}
