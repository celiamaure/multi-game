package fr.ipac.multigame.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fr.ipac.multigame.R;
import fr.ipac.multigame.activity.MainActivity;
import fr.ipac.multigame.activity.ShowPlayerActivity;
import fr.ipac.multigame.manager.ProfileManager;
import fr.ipac.multigame.model.Player;
import fr.ipac.multigame.utils.ActivityUtils;


public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private ShowPlayerActivity activity;
    private ArrayList<Player> players;

    public PlayerAdapter(ShowPlayerActivity activity, ArrayList<Player> players){

        this.players = players;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.show_player_row, viewGroup, false);
        return new PlayerAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Player player = players.get(position);
        Picasso.get().load(player.getPicture()).into(viewHolder.image);
        viewHolder.name.setText(player.getName());
        viewHolder.firstName.setText(player.getFirstName());
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileManager.getInstance().setPlayer(player);
                ActivityUtils.launchActivity(activity, MainActivity.class, ActivityUtils.TYPE_SLIDE);
            }

        });
    }
    @Override
    public int getItemCount() {
        return players.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout container;
        private ImageView image;
        private TextView name;
        private TextView firstName;
        ViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            image = itemView.findViewById(R.id.picture);
            name = itemView.findViewById(R.id.name);
            firstName = itemView.findViewById(R.id.firstname);
        }
    }
}
