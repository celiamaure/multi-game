package fr.ipac.multigame.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.ipac.multigame.R;
import fr.ipac.multigame.adapter.SettingsAdapter;
import fr.ipac.multigame.manager.ProfileManager;
import fr.ipac.multigame.model.Game;

public class SettingsFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button logout;
    private Button players;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        logout = view.findViewById(R.id.logout);
        players = view.findViewById(R.id.players);

        recyclerView = view.findViewById(R.id.display_setting_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Game> games = new ArrayList<>();

        games.add(new Game("Drag n drop", ProfileManager.getInstance().getPlayer().getScores().get(0)));
        games.add(new Game("Swipe", ProfileManager.getInstance().getPlayer().getScores().get(1)));
        games.add(new Game("Fast tap", ProfileManager.getInstance().getPlayer().getScores().get(2)));
        games.add(new Game("Ipac Game", ProfileManager.getInstance().getPlayer().getScores().get(3)));


        recyclerView.setAdapter(new SettingsAdapter(games));

        //TODO: ajouter des couleurs en fonction du score
        //recyclerView.setBackgroundColor(2);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ActivityUtils.launchActivity(ShowPlayerActivity, , );
            }
        });

        players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: retourner a la vue des players
            }
        });

        return view;
    }
}
