package cz.muni.pv239.fragments;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Layout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cz.muni.pv239.R;
import cz.muni.pv239.R.drawable;
import cz.muni.pv239.Task;

import static cz.muni.pv239.MainActivity.dataManager;

public class FragmentTaskList extends Fragment implements View.OnClickListener {
    View view;
    LinearLayout ll;

    public FragmentTaskList() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout ll = generateContent();
        container.addView(ll);
        view = inflater.inflate(R.layout.task_list_fragment, container, false);
        return view;
    }

    @SuppressLint("ResourceAsColor")
    private LinearLayout generateContent() {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        TextView headline = new TextView(getActivity());
        headline.setText("Choose your task");
        headline.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        headline.setTextColor(R.color.dark_purple);
        headline.setPadding(0,15,0,15);
        headline.setTextSize(30f);


        linearLayout.addView(headline);

        LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        ArrayList<Task> tasks = dataManager.getTasks();
        for (final Task task:tasks) {
            System.out.println(task.getName());

            LinearLayout taskLayout = new LinearLayout(getActivity());
            taskLayout.setOrientation(LinearLayout.HORIZONTAL);
            taskLayout.setBackgroundResource(R.color.colorwhite);

            taskLayout.setGravity(Gravity.CENTER_VERTICAL);
            taskLayout.setPadding(40,20,0,20);
            TextView text = new TextView(getActivity());

            text.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()));
            text.setText(task.getName());
            text.setTextColor(R.color.dark_purple);
            text.setTextSize(30);
            text.setPadding(30,0,0,0);
            taskLayout.addView(text);

            Button brain = new Button(getActivity());
            brain.setBackgroundResource(drawable.btn_brain_2);

            ImageView img = new ImageView(getActivity());
            img.setImageResource(drawable.btn_gears_2);

            brain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putString("name", task.getName());

                    FragmentTime fTime = new FragmentTime();
                    fTime.setArguments(b);
                    getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, fTime).commit();
                }
            });
            taskLayout.addView(brain);
            taskLayout.addView(img);

            linearLayout.addView(taskLayout);
        }

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
                getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentNewTask()).commit();
                break;*/
        }

    }

}
