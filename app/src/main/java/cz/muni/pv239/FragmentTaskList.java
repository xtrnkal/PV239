package cz.muni.pv239;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentTaskList extends Fragment implements View.OnClickListener {
    View view;

    public FragmentTaskList() {
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.task_list_fragment, container, false);
        Button btnNew = (Button) view.findViewById(R.id.button5);
        btnNew.setOnClickListener(this);
        return view;
    }

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
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.brain1:
                //getFragmentManager().beginTransaction().show(new FragmentTime());
                //changeFragment();

                break;
            case R.id.button5:

                getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentTime()).commit();

/*
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.task_fragment_container, new FragmentTime());
                ft.commit();
*/

                break;
        }

    }

}
