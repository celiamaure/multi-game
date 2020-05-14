package fr.ipac.multigame.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.ipac.multigame.R;
import fr.ipac.multigame.activity.FinishActivity;

public class IpacGameFragment extends Fragment {

    private TextView numberOfTry;
    private EditText numberEd;
    private TextView moreOrLess;
    private Button validate;

    private int numberOfTryLeft = 10;
    private int numberToFind;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ipac_game, container, false);

        numberOfTry = view.findViewById(R.id.number_of_try);
        numberEd = view.findViewById(R.id.number_ed);
        moreOrLess = view.findViewById(R.id.more_or_less);
        validate = view.findViewById(R.id.validate);

        numberOfTry.setText(getString(R.string.try_left, numberOfTryLeft));

        numberToFind = (int) (Math.random() * 100);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = numberEd.getText().toString();

                if (!input.isEmpty()){
                    numberOfTryLeft--;
                    numberOfTry.setText(getString(R.string.try_left, numberOfTryLeft));


                    if (Integer.valueOf(input) > numberToFind) {
                        moreOrLess.setText(R.string.less);
                    } else if(Integer.valueOf(input) == numberToFind) {
                        FinishActivity.newInstance(getActivity(), getString(R.string.ipac_game), numberOfTryLeft);
                    } else {
                        moreOrLess.setText(R.string.more);
                    }
                }
                displayTextWithFade();
                numberEd.setText("");
            }
        });

        numberEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    validate.performClick();
                }
                return false;
            }
        });


        return view;
    }

    private void displayTextWithFade() {
        moreOrLess.setVisibility(View.VISIBLE);
        moreOrLess.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideTextWithFade();
            }
        }, 400);
    }
    private void hideTextWithFade(){
        moreOrLess.setVisibility(View.INVISIBLE);
        moreOrLess.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
    }


}
