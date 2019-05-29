package cz.muni.pv239.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import cz.muni.pv239.R;
import cz.muni.pv239.R.drawable;
import cz.muni.pv239.Task;

import static cz.muni.pv239.MainActivity.BUTTON_HEIGHT;
import static cz.muni.pv239.MainActivity.dataManager;

public class FragmentTaskList extends Fragment {
    View view;

    public FragmentTaskList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.task_list_fragment, container, false);
        LinearLayout ll = generateContent();

        ScrollView s = new ScrollView(getActivity());
        s.addView(ll);

        container.addView(s);

        return view;
    }

    @SuppressLint("ResourceAsColor")
    private LinearLayout generateContent() {

        final LinearLayout linearLayout = new LinearLayout(getActivity());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LayoutParams lineParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        TextView headline = new TextView(getActivity());
        headline.setText(R.string.h_tasks);
        headline.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        headline.setLinkTextColor(R.color.dark_purple);
        headline.setPadding(0, 15, 0, 15);
        headline.setTextSize(30f);

        linearLayout.addView(headline);

        ArrayList<Task> tasks = dataManager.getTasks();
        if (!tasks.isEmpty()) {
            for (final Task task : tasks) {
                LinearLayout taskLayout = new LinearLayout(getActivity());
                taskLayout.setOrientation(LinearLayout.HORIZONTAL);
                taskLayout.setBackgroundResource(R.color.colorwhite);

                taskLayout.setGravity(Gravity.CENTER_VERTICAL);
                taskLayout.setPadding(40, 20, 0, 20);
                TextView text = new TextView(getActivity());

                text.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()));
                text.setText(task.getName());
                text.setTextColor(R.color.dark_purple);
                text.setLinkTextColor(R.color.dark_purple);
                text.setTextSize(30);
                text.setPadding(30, 0, 0, 0);
                taskLayout.addView(text);

                Button brain = new Button(getActivity());
                brain.setBackgroundResource(drawable.btn_brain_2);

                brain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b = new Bundle();
                        b.putString("name", task.getName());
                        FragmentTime fTime = new FragmentTime();
                        fTime.setArguments(b);
                        linearLayout.removeAllViews();
                        getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, fTime).commit();
                    }
                });
                taskLayout.addView(brain);

                Button gears = new Button(getActivity());
                gears.setBackgroundResource(drawable.btn_gears_2);
                gears.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b = new Bundle();
                        b.putString("name", task.getName());
                        FragmentEditTask fTask = new FragmentEditTask();
                        fTask.setArguments(b);
                        linearLayout.removeAllViews();
                        getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, fTask).commit();
                    }
                });
                taskLayout.addView(gears);

                View line = new View(getActivity());
                lineParams.height = 3;
                line.setBackgroundColor(R.color.dark_purple);
                line.setLayoutParams(lineParams);
                linearLayout.addView(line);
                linearLayout.addView(taskLayout);
            }
        }

        View line = new View(getActivity());
        lineParams.height = 3;
        line.setBackgroundColor(R.color.dark_purple);
        line.setLayoutParams(lineParams);
        linearLayout.addView(line);

        Button justBackground = view.findViewById(R.id.button123);

        Button addNew = new Button(getActivity());
        addNew.setText(R.string.create_new);


        addNew.setBackground(justBackground.getBackground());
        addNew.setHeight(BUTTON_HEIGHT);
        addNew.setTextSize(20f);
        addNew.setTextColor(getResources().getColor(R.color.dark_purple));
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeAllViews();
                getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentEditTask()).commit();
            }
        });
        addNew.setElegantTextHeight(true);
        addNew.setPadding(0, 30, 0, 30);

        linearLayout.addView(addNew);

        return linearLayout;
    }
}
