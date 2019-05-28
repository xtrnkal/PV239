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
import android.widget.TextView;

import cz.muni.pv239.R;

import static cz.muni.pv239.MainActivity.*;

public class FragmentEditTask extends Fragment {
    View view;

    public FragmentEditTask() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.task_fragment, container, false);
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

        TextView headline = new TextView(getActivity());
        headline.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        headline.setLinkTextColor(getResources().getColor(R.color.dark_purple));
        headline.setPadding(0,15,0,15);
        headline.setTextSize(30f);

        if (exists) {
            headline.setText("Edit task");
        } else {
            headline.setText("Create new task");
        }

        linearLayout.addView(headline);


        final EditText text = new EditText(getActivity());
        if (exists) {
            text.setText(name);
        }
        linearLayout.addView(text);

        //hack pro background
        Button justBackground = (Button) view.findViewById(R.id.button1234);

        Button btnCreate = new Button(getActivity());
        btnCreate.setBackground(justBackground.getBackground());
        btnCreate.setTextColor(getResources().getColor(R.color.dark_purple));
        btnCreate.setTextSize(20f);
        btnCreate.setElegantTextHeight(true);
        btnCreate.setPadding(0,30,0,30);

        if (exists) {
            btnCreate.setText("Save");
        } else {
            btnCreate.setText("Create");
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

        Button btnDelete = new Button(getActivity());
        btnDelete.setText("Delete task");
        btnDelete.setBackground(justBackground.getBackground());
        btnCreate.setTextColor(getResources().getColor(R.color.dark_purple));
        btnDelete.setTextSize(20f);
        btnDelete.setElegantTextHeight(true);
        btnDelete.setPadding(0,30,0,30);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(getActivity());

                dataManager.deleteTask(nameFin, getContext());
                linearLayout.removeAllViews();
                getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentTaskList()).commit();
            }
        });
        linearLayout.addView(btnDelete);

        Button btnBack = new Button(getActivity());
        btnBack.setText("Go back to list");
        btnBack.setBackground(justBackground.getBackground());
        btnBack.setTextColor(getResources().getColor(R.color.dark_purple));
        btnBack.setTextSize(20f);
        btnBack.setElegantTextHeight(true);
        btnBack.setPadding(0,30,0,30);
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
