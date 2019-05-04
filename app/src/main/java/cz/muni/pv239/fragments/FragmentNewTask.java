package cz.muni.pv239.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cz.muni.pv239.R;
import cz.muni.pv239.fragments.FragmentTaskList;

public class FragmentNewTask extends Fragment implements View.OnClickListener {
    View view;

    public FragmentNewTask() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_task_fragment, container, false);
/*
        Button btnCreate = (Button) view.findViewById(R.id.btn_create_task);
        btnCreate.setOnClickListener(this);
        */
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        Button btnBack = (Button) view.findViewById(R.id.btn_back_to_list);
        btnBack.setOnClickListener(this);
        Button btnCreate = (Button) view.findViewById(R.id.btn_create_task);
        btnCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_back_to_list:
                getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentTaskList()).commit();
                break;
            case R.id.btn_create_task:
                getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentTaskList()).commit();
                break;
        }

    }

}
