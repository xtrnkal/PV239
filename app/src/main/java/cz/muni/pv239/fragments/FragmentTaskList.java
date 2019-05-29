package cz.muni.pv239.fragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import java.util.ArrayList;

import cz.muni.pv239.R;
import cz.muni.pv239.R.drawable;
import cz.muni.pv239.Task;

import static cz.muni.pv239.MainActivity.BUTTON_HEIGHT;
import static cz.muni.pv239.MainActivity.dataManager;

public class FragmentTaskList extends Fragment implements View.OnClickListener {
    View view;
    LinearLayout ll;

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
        headline.setPadding(0,15,0,15);
        headline.setTextSize(30f);

        linearLayout.addView(headline);

        LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

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
                        //System.out.println(task.getName());
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

        Button justBackground = (Button) view.findViewById(R.id.button123);

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
        addNew.setPadding(0,30,0,30);

        linearLayout.addView(addNew);

        return linearLayout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        //Button brain1 = (Button) view.findViewById(R.id.brain1);
        //brain1.setOnClickListener(this);
        //Button btnNew = (Button) view.findViewById(R.id.btn_add_new_task);
        //btnNew.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //case R.id.brain:
            //    getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentTime()).commit();
            //    break;
            /*case R.id.btn_add_new_task:
                getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentEditTask()).commit();
                break;*/
        }

    }

}
