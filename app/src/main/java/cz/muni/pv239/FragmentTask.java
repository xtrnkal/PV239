package cz.muni.pv239;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentTask extends Fragment {
    View view;
    Button openTimerButton = null;

    public FragmentTask() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.task_fragment, container, false);

        openTimerButton = (Button) view.findViewById(R.id.brain1);
        openTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), FragmentTime.class);
                startActivity(intent);
            }
        });
        openTimerButton = (Button) view.findViewById(R.id.brain2);
        openTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), FragmentTime.class);
                startActivity(intent);
            }
        });
        openTimerButton = (Button) view.findViewById(R.id.brain3);
        openTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), FragmentTime.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void openTimer(){


    }
}
