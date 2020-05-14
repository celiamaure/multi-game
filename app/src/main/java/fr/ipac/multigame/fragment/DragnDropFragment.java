package fr.ipac.multigame.fragment;

import android.content.ClipData;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Timer;
import java.util.TimerTask;

import fr.ipac.multigame.R;
import fr.ipac.multigame.activity.FinishActivity;

public class DragnDropFragment extends Fragment {

    final static int UP_LEFT = 0;
    final static int UP_RIGHT = 1;
    final static int CENTER_LEFT = 2;
    final static int CENTER_RIGHT = 3;
    final static int DOWN_LEFT = 4;
    final static int DOWN_RIGHT = 5;

    final static int TIME_BEFORE_FIRST_EXECUTION = 0;
    final static int DELAY_BETWEEN_TRIGGER = 1000;


    private int score = -1;
    private TextView scoreTv;
    private int dragZone = 0;
    private FrameLayout circle;

    //dragzone
    private FrameLayout topLeft;
    private FrameLayout centerLeft;
    private FrameLayout topRight;
    private FrameLayout centerRight;
    private FrameLayout bottomLeft;
    private FrameLayout bottomRight;

    //time zone
    private TextView time;
    private Timer timer;
    private int timeLeft = 20;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drag_n_drop, container, false);
        scoreTv = view.findViewById(R.id.drag_score);
        circle = view.findViewById(R.id.drag_target);
        topLeft = view.findViewById(R.id.drag_frame_top_left);
        topRight = view.findViewById(R.id.drag_frame_bottom_right);
        bottomRight = view.findViewById(R.id.drag_frame_top_right);
        bottomLeft = view.findViewById(R.id.drag_frame_bottom_left);
        centerLeft = view.findViewById(R.id.drag_frame_center_left);
        centerRight = view.findViewById(R.id.drag_frame_center_right);
        time = view.findViewById(R.id.drag_time);

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
                            FinishActivity.newInstance(getActivity(), getString(R.string.drag_n_drop_name), score);
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


        circle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(data, shadowBuilder, view, 0);
                    view.setVisibility(View.INVISIBLE);
                    return true;
                }
                return false;
            }
        });

        topLeft.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && dragZone == UP_LEFT) {
                    setAction();
                }

                if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                    circle.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        centerLeft.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && dragZone == CENTER_LEFT) {
                    setAction();
                }
                if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                    circle.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        topRight.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && dragZone == UP_RIGHT) {
                    setAction();
                }
                if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                    circle.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        centerRight.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && dragZone == CENTER_RIGHT) {
                    setAction();
                }
                if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                    circle.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        bottomLeft.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && dragZone == DOWN_LEFT) {
                    setAction();
                }
                if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                    circle.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        bottomRight.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DROP && dragZone == DOWN_RIGHT) {
                    setAction();
                }
                if (event.getAction() == DragEvent.ACTION_DRAG_ENDED) {
                    circle.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        return view;
    }

    private void setAction() {
        score++;
        scoreTv.setText(getString(R.string.score, score));
        if (Math.random() < 0.16) {
            dragZone = UP_LEFT;
            circle.getBackground().setColorFilter(getResources().getColor(R.color.light_grey), PorterDuff.Mode.SRC_OVER);
        } else if (Math.random() < 0.32) {
            dragZone = UP_RIGHT;
            circle.getBackground().setColorFilter(getResources().getColor(R.color.dark_blue), PorterDuff.Mode.SRC_OVER);
        } else if (Math.random() < 0.48) {
            dragZone = CENTER_LEFT;
            circle.getBackground().setColorFilter(getResources().getColor(R.color.pink), PorterDuff.Mode.SRC_OVER);
        } else if (Math.random() < 0.64) {
            dragZone = CENTER_RIGHT;
            circle.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_OVER);
        } else if (Math.random() < 0.80) {
            dragZone = DOWN_LEFT;
            circle.getBackground().setColorFilter(getResources().getColor(R.color.color_black), PorterDuff.Mode.SRC_OVER);
        } else {
            dragZone = DOWN_RIGHT;
            circle.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_OVER);
        }
    }
}
