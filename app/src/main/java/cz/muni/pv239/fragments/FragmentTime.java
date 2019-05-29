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
import android.widget.TextView;

import com.triggertrap.seekarc.SeekArc;
import com.triggertrap.seekarc.SeekArc.OnSeekArcChangeListener;

import java.util.Locale;

import cz.muni.pv239.R;

import static cz.muni.pv239.MainActivity.dataManager;
import static java.lang.Integer.parseInt;

public class FragmentTime extends Fragment {
    View view;

    private static final long START_TIME_IN_MILLIS = 20 * 60 * 1000;

    private TextView mTextViewCountDown;
    private Button mButtonStartReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mTimeStartInMillis;
    private TextView mTimeTask;
    private String taskName;

    private Button goBack;

    private SeekArc mSeekArc;
    private TextView mSeekArcProgress;

    public FragmentTime() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.time_fragment, container, false);

        taskName = null;
        if (this.getArguments()!= null) {
            taskName = this.getArguments().getString("name");
        }

        mTimeTask = (TextView) view.findViewById(R.id.time_task);
        mTimeTask.setText(taskName);
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

        mButtonStartReset = view.findViewById(R.id.btn_start);

        mButtonStartReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    mSeekArc.setVisibility(View.VISIBLE);
                    resetTimer();
                } else {
                    String textTime = (String) mSeekArcProgress.getText();
                    mTimeStartInMillis = parseInt(textTime.split(":")[0]) * 60 * 1000;
                    if (mTimeStartInMillis > 0) {
                        mTimeLeftInMillis = mTimeStartInMillis;
                        mSeekArc.setVisibility(View.INVISIBLE);
                        startTimer();
                    }
                }
            }
        });

        goBack = view.findViewById(R.id.btn_back_to_list);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.task_fragment_container, new FragmentTaskList()).commit();
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
                //mCountDownTimer.cancel();
                mButtonStartReset.setText(R.string.start_timer);
                //mButtonStartReset.setVisibility(View.VISIBLE);
                mSeekArc.setVisibility(View.VISIBLE);
                mSeekArcProgress.setText(Long.toString(START_TIME_IN_MILLIS/(60*1000)) + ":00");
                dataManager.editStatistics(taskName, (int)(mTimeStartInMillis/(60*1000)), getContext());
            }
        }.start();

        mTimerRunning = true;
        mButtonStartReset.setText(R.string.reset_timer);
    }
    /*
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartReset.setText("Start");
    }
    */

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        mCountDownTimer.cancel();
        updateCountDownText();
        mTimerRunning = false;
        //mButtonStartReset.setVisibility(View.VISIBLE);
        mButtonStartReset.setText(R.string.start_timer);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mSeekArcProgress.setText(timeLeftFormatted);
    }
}
