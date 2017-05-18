package com.mobile.pos.iago.taskmanager.views;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mobile.pos.iago.taskmanager.R;
import com.mobile.pos.iago.taskmanager.adapters.TaskAdapter;
import com.mobile.pos.iago.taskmanager.models.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.rv_list_of_task)
    protected RecyclerView mListOfTasks;

    @BindView(R.id.total_of_task)
    protected TextView mTotalOfTasks;

    public static List<Task> mTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTasks = new ArrayList<>();


    }

    @Override
    protected void onResume() {
        super.onResume();
        String totalOfTask = getString(R.string.total_of_task);
        mTotalOfTasks.setText(totalOfTask + " " + mTasks.size());

        configureRecyclerView();
    }

    private void configureRecyclerView(){
        mListOfTasks.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListOfTasks.setLayoutManager(linearLayoutManager);

        TaskAdapter adapter = new TaskAdapter(this, mTasks);
        mListOfTasks.setAdapter(adapter);
    }

    @OnClick(R.id.btn_view_task)
    protected  void onClick(){
        Intent it = new Intent(MainActivity.this, CreateTaskActivity.class);
        startActivity(it);
    }

    @OnClick(R.id.btn_close)
    protected  void onClose(){
        MainActivity.this.finish();
    }

}
