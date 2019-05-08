package cz.muni.pv239.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.triggertrap.seekarc.SeekArc;
import com.triggertrap.seekarc.SeekArc.OnSeekArcChangeListener;

import java.util.Locale;

import cz.muni.pv239.R;

import static java.lang.Integer.parseInt;

public class FragmentTime extends Fragment {
    View view;

    private static final long START_TIME_IN_MILLIS = 6000;

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private TextView mTimeTask;



    private SeekArc mSeekArc;
    private TextView mSeekArcProgress;

    public FragmentTime() {
        //MainActivity.dataManager.getTasks();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.time_fragment, container, false);

        String name = null;
        if (this.getArguments()!= null) {
            name = this.getArguments().getString("name");
            System.out.println(name);
        }
        final boolean exists = (name != null);
        final String nameFin = name;

        mTimeTask = (TextView) view.findViewById(R.id.time_task);
        mTimeTask.setText(name);
        mTextViewCountDown = mTimeTask;

        mSeekArc = (SeekArc) view.findViewById(R.id.seek_arc);
        mSeekArcProgress = (TextView) view.findViewById(R.id.seek_arc_progress);
        mSeekArc.setOnSeekArcChangeListener(new OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int i, boolean b) {
                mSeekArcProgress.setText(String.valueOf(i) + ":00");
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {

            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {

            }
        });


        mButtonStartPause = view.findViewById(R.id.btn_start);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    mSeekArc.setVisibility(View.VISIBLE);
                    pauseTimer();
                } else {
                    String textTime = (String) mSeekArcProgress.getText();
                    mTimeLeftInMillis = parseInt(textTime.split(":")[0]) * 60 * 1000;
                    mSeekArc.setVisibility(View.INVISIBLE);
                    startTimer();
                }
            }
        });


        return view;
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                //mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        //mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        //mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        //mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mSeekArcProgress.setText(timeLeftFormatted);
    }
}
