package cz.muni.pv239.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import cz.muni.pv239.R;

import static cz.muni.pv239.MainActivity.*;

public class FragmentEditTask extends Fragment {
    View view;

    public FragmentEditTask() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_task_fragment, container, false);
        String name = null;
        if (this.getArguments()!= null) {
            name = this.getArguments().getString("name");
            System.out.println(name);
        }
        final boolean exists = (name != null);
        final String nameFin = name;

        final LinearLayout linearLayout = new LinearLayout(getActivity());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.colorwhite));

        final EditText text = new EditText(getActivity());
        if (exists) {
            text.setText(name);
        }
        linearLayout.addView(text);

        Button btnCreate = new Button(getActivity());
        if (exists) {
            btnCreate.setText("Save");
        } else {
            btnCreate.setText("Create new task");
        }

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!text.getText().toString().isEmpty()) {
                    if (exists) {
                        dataManager.editTask(nameFin, text.getText().toString(), getContext());
                    } else {
                        dataManager.addTask(text.getText().toString(), getContext());
                    }

                    hideKeyboard(getActivity());

                    linearLayout.removeAllViews();
                    getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentTaskList()).commit();
                }
            }
        });
        linearLayout.addView(btnCreate);

        Button btnBack = new Button(getActivity());
        btnBack.setText("Go back to list");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(getActivity());
                linearLayout.removeAllViews();
                getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentTaskList()).commit();
            }
        });
        linearLayout.addView(btnBack);

        container.addView(linearLayout);
        view = inflater.inflate(R.layout.task_list_fragment, container, false);
        return view;
    }

    public static void hideKeyboard(FragmentActivity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(FragmentActivity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
