package cz.muni.pv239.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cz.muni.pv239.R;

import static cz.muni.pv239.MainActivity.dataManager;

public class FragmentSettings extends Fragment {
    View view;

    public FragmentSettings() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings_fragment, container, false);
        Button delete = (Button) view.findViewById(R.id.delete_all_data);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataManager.deteleAllData(getContext());
                Toast.makeText(getContext(), R.string.reset_data_toast, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
