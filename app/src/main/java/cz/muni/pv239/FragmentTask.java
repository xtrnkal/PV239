package cz.muni.pv239;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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


        openTimerButton = (Button) view.findViewById(R.id.brain1);
        openTimerButton.setOnClickListener(this);

        openTimerButton = (Button) view.findViewById(R.id.brain2);
        openTimerButton.setOnClickListener(this);
        openTimerButton = (Button) view.findViewById(R.id.brain3);
        openTimerButton.setOnClickListener(this);

        return view;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.brain1:

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.task_fragment_container, new FragmentTime());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                break;
        }
    }
}
