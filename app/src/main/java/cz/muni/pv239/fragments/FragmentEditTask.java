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
import android.widget.Toast;

import cz.muni.pv239.R;

import static cz.muni.pv239.MainActivity.BUTTON_HEIGHT;
import static cz.muni.pv239.MainActivity.dataManager;

public class FragmentEditTask extends Fragment {
    View view;

    public FragmentEditTask() {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.task_fragment, container, false);
        String name = null;
        if (this.getArguments() != null) {
            name = this.getArguments().getString("name");
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
        headline.setPadding(0, 15, 0, 15);
        headline.setTextSize(30f);

        if (exists) {
            headline.setText(R.string.edit);
        } else {
            headline.setText(R.string.create_new);
        }

        linearLayout.addView(headline);


        final EditText text = new EditText(getActivity());
        if (exists) {
            text.setText(name);
        }
        linearLayout.addView(text);

        //hack pro background
        Button justBackground = view.findViewById(R.id.button1234);

        Button btnCreate = new Button(getActivity());
        btnCreate.setBackground(justBackground.getBackground());
        btnCreate.setHeight(BUTTON_HEIGHT);
        btnCreate.setTextColor(getResources().getColor(R.color.dark_purple));
        btnCreate.setTextSize(20f);
        btnCreate.setElegantTextHeight(true);
        btnCreate.setPadding(0, 30, 0, 30);

        if (exists) {
            btnCreate.setText(R.string.save);
        } else {
            btnCreate.setText(R.string.create);
        }

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!text.getText().toString().isEmpty()) {
                    if (exists) {
                        dataManager.editTask(nameFin, text.getText().toString(), getContext());
                        Toast.makeText(getContext(), R.string.edit_task_toast, Toast.LENGTH_SHORT).show();
                    } else {
                        dataManager.addTask(text.getText().toString(), getContext());
                        Toast.makeText(getContext(), R.string.add_task_toast, Toast.LENGTH_SHORT).show();
                    }

                    hideKeyboard(getActivity());

                    linearLayout.removeAllViews();
                    getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentTaskList()).commit();
                } else {
                    Toast.makeText(getContext(), R.string.empty_task_toast, Toast.LENGTH_LONG).show();
                }
            }
        });
        linearLayout.addView(btnCreate);

        if (exists) {
            Button btnDelete = new Button(getActivity());
            btnDelete.setText(R.string.delete);
            btnDelete.setBackground(justBackground.getBackground());
            btnDelete.setHeight(BUTTON_HEIGHT);
            btnDelete.setTextColor(getResources().getColor(R.color.dark_purple));
            btnDelete.setTextSize(20f);
            btnDelete.setElegantTextHeight(true);
            btnDelete.setPadding(0, 30, 0, 30);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideKeyboard(getActivity());

                    dataManager.deleteTask(nameFin, getContext());
                    Toast.makeText(getContext(), R.string.delete_task_toast, Toast.LENGTH_SHORT).show();
                    linearLayout.removeAllViews();
                    getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentTaskList()).commit();
                }
            });
            linearLayout.addView(btnDelete);
        }
        Button btnBack = new Button(getActivity());
        btnBack.setText(R.string.go_back);
        btnBack.setBackground(justBackground.getBackground());
        btnBack.setHeight(BUTTON_HEIGHT);
        btnBack.setTextColor(getResources().getColor(R.color.dark_purple));
        btnBack.setTextSize(20f);
        btnBack.setElegantTextHeight(true);
        btnBack.setPadding(0, 30, 0, 30);
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
}
