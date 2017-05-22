package com.mobile.pos.iago.taskmanager.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.pos.iago.taskmanager.R;
import com.mobile.pos.iago.taskmanager.database.controllers.TaskDBController;
import com.mobile.pos.iago.taskmanager.models.Task;
import com.mobile.pos.iago.taskmanager.utils.TaskUtils;
import com.mobile.pos.iago.taskmanager.views.activities.CreateTaskActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by iago on 17/05/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context mContext;
    private List<Task> mTaskList;

    public TaskAdapter(Context context, List<Task> tasks) {
        this.mContext = context;
        this.mTaskList = tasks;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_adapter_item2, null);

        TaskViewHolder holder = new TaskViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, int position) {

        final Task task = this.mTaskList.get(position);
        holder.mTaskName.setText(task.getTaskTitle());
        holder.mTaskNumber.setText(Integer.toString(position+1));
        if (task.getPriority() == CreateTaskActivity.Priority.High) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.mTaskName.setTextColor(mContext.getColor(R.color.red));
                holder.mTaskNumber.setTextColor(mContext.getColor(R.color.red));
            }else{
                holder.mTaskName.setTextColor(mContext.getResources().getColor(R.color.red));
                holder.mTaskNumber.setTextColor(mContext.getResources().getColor(R.color.red));
            }
        } else if (task.getPriority() == CreateTaskActivity.Priority.Medium) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.mTaskName.setTextColor(mContext.getColor(R.color.yellow));
                holder.mTaskNumber.setTextColor(mContext.getColor(R.color.yellow));
            }else{
                holder.mTaskName.setTextColor(mContext.getResources().getColor(R.color.yellow));
                holder.mTaskNumber.setTextColor(mContext.getResources().getColor(R.color.yellow));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.mTaskName.setTextColor(mContext.getColor(R.color.gray));
                holder.mTaskNumber.setTextColor(mContext.getColor(R.color.gray));
            }else{
                holder.mTaskName.setTextColor(mContext.getResources().getColor(R.color.gray));
                holder.mTaskNumber.setTextColor(mContext.getResources().getColor(R.color.gray));
            }
        }

        if (task.getTaskStatus()){
            holder.mTaskStatus.setChecked(true);
        } else {
            holder.mTaskStatus.setChecked(false);
        }

        holder.mTaskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskUtils.showTaskDetails(mContext,task);
            }
        });

        holder.mTaskStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskDBController db = new TaskDBController(mContext);
                boolean result = db.setTaskStatus(task.getId(), holder.mTaskStatus.isChecked());
                if(result){
                    boolean withOnlyCompletedTask = holder.mTaskStatus.isChecked();
                    mTaskList = db.getAllTask(withOnlyCompletedTask);
                    notifyDataSetChanged();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.task_name)
        protected TextView mTaskName;
        @BindView(R.id.task_status)
        protected CheckBox mTaskStatus;
        @BindView(R.id.task_number)
        protected TextView mTaskNumber;

        public TaskViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
