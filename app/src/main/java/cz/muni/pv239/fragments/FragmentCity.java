package cz.muni.pv239.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import cz.muni.pv239.R;
import cz.muni.pv239.Task;

public class FragmentCity extends Fragment {
    View view;
    private HashMap<String, Integer> statistics;

    public FragmentCity() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.city_fragment, container, false);
/*
        Bundle b = this.getArguments();
        statistics = (HashMap<String, Integer>) b.getSerializable("statistics");
*/
        return view;
    }
}