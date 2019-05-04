package cz.muni.pv239.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cz.muni.pv239.R;
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
        //view = inflater.inflate(R.layout.task_list_fragment, container, false);

        LinearLayout ll = generateContent();



        //like this, add all buttons and other views
        //you can use a loop for adding multiple similar views

        container.addView(ll);
        view = inflater.inflate(R.layout.task_list_fragment, container, false);

        return view;




/*

        //FragmentManager fm = getFragmentManager();


        //getFragmentManager().beginTransaction().add(new TextView(inflater.getContext()), this);
        getChildFragmentManager().beginTransaction().

        Fragment a = new Fragment();
       // a.getContext().add
        //getFragmentManager().beginTransaction()

        ll = view.findViewById(R.id.task_list_fragment_container);
        for (int i = 1; i <= 2; i++) {
            TextView textView = new TextView(inflater.getContext());
            textView.setText("TextView " + i);
            ll.addView(textView);
            //fm.beginTransaction(). add(textView);
        }

        return ll;
*/

/*
        TextView tv = new TextView();
        tv.setLayoutParams(new LayoutParams());
        Button a = new Button();
        inflater.
*/

        /*Button btnNew = (Button) view.findViewById(R.id.btn_add_new_task);
        btnNew.setOnClickListener(this);
        */
        //return view;
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
            Button button = new Button(getActivity());
            button.setText(task.getName());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b = new Bundle();
                    b.putString("name", task.getName());

                    FragmentTime fTime = new FragmentTime();
                    fTime.setArguments(b);
                    getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, fTime).commit();
                }
            });
            linearLayout.addView(button);
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
