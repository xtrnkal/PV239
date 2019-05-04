package cz.muni.pv239.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cz.muni.pv239.R;
import cz.muni.pv239.Task;
import cz.muni.pv239.fragments.FragmentTaskList;

public class FragmentTask extends Fragment {
    View view;
    private ArrayList<Task> tasks;

    public FragmentTask() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.task_fragment, container, false);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.task_fragment_container, new FragmentTaskList());
        ft.commit();

/*
        Bundle b = this.getArguments();
        tasks = (ArrayList<Task>) b.getSerializable("tasks");

        System.out.println("TADY" + tasks.get(0).getName() + tasks.get(1).getName());
*/
        return view;
    }
}
