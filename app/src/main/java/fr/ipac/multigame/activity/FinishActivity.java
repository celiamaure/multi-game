package fr.ipac.multigame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.ipac.multigame.R;
import fr.ipac.multigame.utils.ActivityUtils;


public class FinishActivity extends AppCompatActivity {


    public static final String SCORE = "score";
    public static final String NAME = "name";

    public static void newInstance(Activity activity, String name, int score) {
        Intent intent = new Intent(activity, FinishActivity.class);

        intent.putExtra(FinishActivity.NAME, name);
        intent.putExtra(FinishActivity.SCORE, score);

        ActivityUtils.launchActivity(activity, intent, ActivityUtils.TYPE_FADE, false);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_activity);

        String nameOfGame = getIntent().getStringExtra(NAME);
        int scoreOfGame = getIntent().getIntExtra(SCORE, 0);


        TextView name = findViewById(R.id.finish_name);
        TextView score = findViewById(R.id.finish_score);

        name.setText(nameOfGame);
        score.setText(String.valueOf(scoreOfGame));

        findViewById(R.id.finish_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.finish_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

}
