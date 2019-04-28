package cz.muni.pv239;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentTask extends Fragment implements View.OnClickListener {
    View view;


    Button openTimerButton = null;

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

/*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        Button brain1 = (Button) view.findViewById(R.id.brain1);
        brain1.setOnClickListener(this);
        Button btnNew = (Button) view.findViewById(R.id.button5);
        btnNew.setOnClickListener(this);
    }*/

    private void changeFragment() {
        //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setAction(R.layout.time_fragment);
        //:w
        // startActivity(intent);
        //FragmentTime time = new FragmentTime();

        Intent intent = new Intent(getContext(), FragmentTime.class);
        intent.putExtra("EXTRA_MESSAGE", "AHOJ");
        startActivity(intent);


        //getFragmentManager().beginTransaction().add(R.id.time_fragment_container, time).commit();
        //getFragmentManager().beginTransaction().add(new FragmentTime()).show(new FragmentTime()).commit();
        //getChildFragmentManager().beginTransaction().add(time, null).commit();
        //getFragmentManager().beginTransaction().show(new FragmentTime());
                //replace(R.id.task_fragment_container, new FragmentTime()).addToBackStack(null).commit();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.brain1:
                //getFragmentManager().beginTransaction().show(new FragmentTime());
                //changeFragment();

                break;
            case R.id.button5:
                /*
                FragmentManager fm = getFragmentManager();
                Fragment a = fm.findFragmentById(R.id.task_fragment_container);
                getChildFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                    .show(new FragmentTime())
                    .commit()  ;
                */
                //getView().setVisibility(View.GONE);
                //view = getLayoutInflater().inflate(R.layout.time_fragment, null);

                //getFragmentManager().beginTransaction().hide(this).commit();
                getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentTime()).commit();

                //getChildFragmentManager()

                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.task_fragment_container, new FragmentTime());
                ft.commit();

                //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                //ft.addToBackStack(null);
               /* FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.task_fragment_container, new FragmentTime());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
*/
                //getFragmentManager().beginTransaction().show(new FragmentTime());
                //view = getLayoutInflater().inflate(R.layout.time_fragment, null);
                //changeFragment();
                break;
        }

    }
}
