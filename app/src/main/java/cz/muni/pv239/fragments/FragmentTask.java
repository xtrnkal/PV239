package cz.muni.pv239.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.muni.pv239.R;

public class FragmentTask extends Fragment {
    View view;

    public FragmentTask() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.task_fragment, container, false);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.task_fragment_container, new FragmentTaskList());
        ft.commit();
        return view;
    }
}
