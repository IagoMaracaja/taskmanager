package com.mobile.pos.iago.taskmanager.views.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


import com.mobile.pos.iago.taskmanager.R;
import com.mobile.pos.iago.taskmanager.views.fragments.TaskListFragment;

import butterknife.ButterKnife;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inflateFragment();

    }


    public void inflateFragment() {
        FragmentManager fm = getFragmentManager();

        // Abre uma transação e adiciona
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.ll_fragment_list, new TaskListFragment());
        ft.commit();

    }

}
