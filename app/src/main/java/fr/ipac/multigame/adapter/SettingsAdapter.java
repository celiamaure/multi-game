package fr.ipac.multigame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.ipac.multigame.R;
import fr.ipac.multigame.model.Game;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {

    private ArrayList<Game> games;
    private Context context;

    public SettingsAdapter(ArrayList<Game> games){

        this.games = games;
    }

    @NonNull
    @Override
    public SettingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context = viewGroup.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.settings_row, viewGroup, false);
        return new SettingsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsAdapter.ViewHolder viewHolder, int position) {
        final Game game = games.get(position);
        viewHolder.name.setText(game.getName());
        if (game.getHighestScore() < 10) {
            viewHolder.highestScore.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
        } else if(game.getHighestScore() > 10 && game.getHighestScore() < 30) {
            viewHolder.highestScore.setBackgroundColor(ContextCompat.getColor(context, R.color.orange));
        } else if(game.getHighestScore() > 30) {
            viewHolder.highestScore.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
        }
        viewHolder.highestScore.setText(game.getHighestScore().toString());
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView highestScore;
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.game_name);
            highestScore = itemView.findViewById(R.id.highest_score);
        }
    }

}
