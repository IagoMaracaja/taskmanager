package com.mobile.pos.iago.taskmanager.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.pos.iago.taskmanager.R;
import com.mobile.pos.iago.taskmanager.models.Task;

/**
 * Created by iago on 18/05/17.
 */

public class TaskUtils {


    /**
     * Show dialog with task details
     * @param context
     * @param task
     */
    public static void showTaskDetails(Context context, Task task){
        // custom dialog
        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.task_details);

        // set the custom dialog components - text, image and button
        TextView taskTitle = (TextView) dialog.findViewById(R.id.task_title);
        TextView taskDescription = (TextView) dialog.findViewById(R.id.et_task_description);
        TextView taskFinishedAt = (TextView) dialog.findViewById(R.id.tv_finished_at);
        TextView taskPriority = (TextView) dialog.findViewById(R.id.tv_detail_priority);

        taskTitle.setText(task.getTaskTitle());
        taskDescription.setText(task.getTaskDescription());
        taskPriority.setText(task.getPriority().toString());
        String taskFinished = task.getTaskFinished();
        if(taskFinished == null || taskFinished.equals("")){
            taskFinished = context.getString(R.string.open);
        }

        taskFinishedAt.setText(taskFinished);

        Button btnOk = (Button) dialog.findViewById(R.id.btn_ok_task_detail);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
