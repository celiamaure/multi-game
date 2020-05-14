package fr.ipac.multigame.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.ipac.multigame.R;
import fr.ipac.multigame.adapter.PlayerAdapter;
import fr.ipac.multigame.model.Player;

public class ShowPlayerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Integer> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_player_activity);
        recyclerView = findViewById(R.id.display_player_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scores = new ArrayList<>();
        scores.add(0);
        scores.add(0);
        scores.add(0);
        scores.add(0);
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < 200; i++){
            players.add(new Player("Vaudey " + i, "Baptiste", "12", "o", "loc", scores));
        }
        recyclerView.setAdapter(new PlayerAdapter(this, players));
    }
}
