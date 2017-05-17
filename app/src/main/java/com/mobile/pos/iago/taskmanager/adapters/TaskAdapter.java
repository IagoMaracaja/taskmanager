package com.mobile.pos.iago.taskmanager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import com.mobile.pos.iago.taskmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iago on 17/05/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context mContext;
    private String[] mTaskList;

    public TaskAdapter(Context context, String[] tasks) {
        this.mContext = context;
        this.mTaskList = tasks;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_adapter_item,null);

        TaskViewHolder holder = new TaskViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {

        String taskName = this.mTaskList[position];
        holder.mTaskName.setText(taskName);

    }

    @Override
    public int getItemCount() {
        return mTaskList.length;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.task_name)
        protected TextView mTaskName;
        @BindView(R.id.task_status)
        protected CheckBox mTaskStatus;

        public TaskViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
