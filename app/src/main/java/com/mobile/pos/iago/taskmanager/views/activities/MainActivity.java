package com.mobile.pos.iago.taskmanager.views.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mobile.pos.iago.taskmanager.R;
import com.mobile.pos.iago.taskmanager.database.controllers.TaskDBController;
import com.mobile.pos.iago.taskmanager.views.fragments.TaskListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mobile.pos.iago.taskmanager.views.fragments.TaskListFragment.BUNDLE_TASK_NOT_COMPLETED;

public class MainActivity extends Activity {

    @BindView(R.id.total_of_task)
    protected TextView mTotalOfTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inflateFragment();


    }

    @Override
    protected void onResume() {
        super.onResume();

        String totalOfTask = getString(R.string.total_of_task);
        int taskSize = new TaskDBController(this).getAllTask(true).size();
        mTotalOfTasks.setText(totalOfTask + " " + taskSize);

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
        bundle.putBoolean(BUNDLE_TASK_NOT_COMPLETED, true);
        taskListFragment.setArguments(bundle);

        ft.add(R.id.ll_fragment_list, taskListFragment);
        ft.commit();

    }

    public void updateFragment(){

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        TaskListFragment taskListFragment = new TaskListFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean(BUNDLE_TASK_NOT_COMPLETED, true);
        taskListFragment.setArguments(bundle);

        ft.replace(R.id.ll_fragment_list, taskListFragment);
        ft.commit();
    }


    @OnClick(R.id.btn_close)
    protected void onClose() {
        MainActivity.this.finish();
    }

    @OnClick(R.id.btn_view_task)
    protected void onClick() {
        Intent it = new Intent(MainActivity.this, ViewTaskActivity.class);
        startActivity(it);
    }

}
