package com.mobile.pos.iago.taskmanager.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mobile.pos.iago.taskmanager.R;
import com.mobile.pos.iago.taskmanager.adapters.TaskAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.rv_list_of_task)
    protected RecyclerView mListOfTasks;

    String[] arrayOfTasks = {
            "Lavar o carro",
            "Fazer feira",
            "Dormir",
            "Jogar Bola",
            "Comprar toalhas",
            "Jogar o lixo fora",
            "Tomar chá",
            "Beber 6 copos de água",
            "Trocar luz do quarto",
            "Fazer sopa"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        configureRecyclerView();


    }

    private void configureRecyclerView(){
        mListOfTasks.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListOfTasks.setLayoutManager(linearLayoutManager);

        TaskAdapter adapter = new TaskAdapter(this, arrayOfTasks);
        mListOfTasks.setAdapter(adapter);
    }
}
