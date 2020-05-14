package fr.ipac.multigame.fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Timer;
import java.util.TimerTask;

import fr.ipac.multigame.R;
import fr.ipac.multigame.activity.FinishActivity;

public class TapOrSwipeGameFragment extends Fragment {

    final static int TIME_BEFORE_FIRST_EXECUTION = 0;
    final static int DELAY_BETWEEN_TRIGGER = 1000;
    final static int SWIPE_UP = 0;
    final static int SWIPE_DOWN = 1;
    final static int SWIPE_LEFT= 2;
    final static int SWIPE_RIGHT= 3;
    private LinearLayout detection;
    private TextView time;
    private TextView action;
    private TextView scoreTv;
    private int score = -1;
    private Timer timer;
    private int timeLeft = 20;
    private boolean isShortTap;
    private int swipeType;
    private boolean isTapGame;

    private float initialX, initialY;

    public static TapOrSwipeGameFragment newInstance(boolean isTap) {
        TapOrSwipeGameFragment  fragment = new TapOrSwipeGameFragment ();
        Bundle bundle = new Bundle();
        bundle.putBoolean(TapOrSwipeFragment.IS_TAP, isTap);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fast_tap_game, container, false);
        detection = view.findViewById(R.id.tap_main_detection);
        time = view.findViewById(R.id.tap_main_time);
        action = view.findViewById(R.id.tap_main_action);
        scoreTv = view.findViewById(R.id.tap_main_score);
        isTapGame = getArguments().getBoolean(TapOrSwipeFragment.IS_TAP, false);

        // Time handling
        time.setText(getString(R.string.remaining_time, timeLeft));
        final Handler handler = new Handler();
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        timeLeft--;
                        if (timeLeft == 0) {
                            timer.cancel();
                            FinishActivity.newInstance(getActivity(), getString(R.string.fast_tap_name), score);
                            getFragmentManager().popBackStack();
                        } else {
                            time.setText(getString(R.string.remaining_time, timeLeft));
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, TIME_BEFORE_FIRST_EXECUTION, DELAY_BETWEEN_TRIGGER);
        setAction();
        if (isTapGame){
            detection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isShortTap) {
                        setAction();
                    }
                }
            });
            detection.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (!isShortTap) {
                        setAction();
                    }
                    return true;
                }
            });
        } else {
            detection.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getActionMasked();
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            initialX = event.getX();
                            initialY = event.getY();
                            break;
                        case MotionEvent.ACTION_UP:
                            float finalX = event.getX();
                            float finalY = event.getY();
                            if (initialX < finalX && swipeType == SWIPE_RIGHT) {
                                setAction();
                            } else if (initialX > finalX && swipeType == SWIPE_LEFT) {
                                setAction();
                            } else if (initialY < finalY  && swipeType == SWIPE_DOWN) {
                                setAction();
                            }else if (initialY > finalY && swipeType == SWIPE_UP) {
                                setAction();
                            }
                            break;
                    }
                    return false;
                }
            });
        }
        return view;
    }
    private void setAction() {
        score++;
        scoreTv.setText(getString(R.string.score, score));
        if (isTapGame){
            if (Math.random() < 0.5) {
                action.setText(getString(R.string.tap));
                isShortTap = true;
            } else {
                action.setText(getString(R.string.tap_long));
                isShortTap = false;
            }
        } else {
            if (Math.random() < 0.25) {
                action.setText(getString(R.string.right));
                swipeType = SWIPE_RIGHT;
            } else if (Math.random() < 0.5){
                action.setText(getString(R.string.left));
                swipeType = SWIPE_LEFT;
            } else if (Math.random() < 0.75) {
                action.setText(getString(R.string.up));
                swipeType = SWIPE_UP;
            } else {
                action.setText(getString(R.string.down));
                swipeType = SWIPE_DOWN;
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}