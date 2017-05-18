package com.mobile.pos.iago.taskmanager.views.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.pos.iago.taskmanager.R;
import com.mobile.pos.iago.taskmanager.adapters.TaskAdapter;
import com.mobile.pos.iago.taskmanager.database.controllers.TaskDBController;
import com.mobile.pos.iago.taskmanager.models.Task;
import com.mobile.pos.iago.taskmanager.views.activities.CreateTaskActivity;
import com.mobile.pos.iago.taskmanager.views.activities.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by iago on 18/05/17.
 */

public class TaskListFragment extends Fragment {

    @BindView(R.id.rv_list_of_task)
    protected RecyclerView mListOfTasks;

    public static List<Task> mTasks;
    private static TaskAdapter mTaskAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        ButterKnife.bind(this, view);
        configureRecyclerView();
        updateValues(getActivity());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateValues(getActivity());
    }

    private void configureRecyclerView(){
        mListOfTasks.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListOfTasks.setLayoutManager(linearLayoutManager);
        mTasks = new TaskDBController(getActivity()).getAllTask();
        mTaskAdapter = new TaskAdapter(getActivity(), mTasks);
        mListOfTasks.setAdapter(mTaskAdapter);
    }

    public static void updateValues(Context context){

        mTasks = new TaskDBController(context).getAllTask();
        mTaskAdapter.notifyDataSetChanged();
    }



}
