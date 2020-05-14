package fr.ipac.multigame.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.ipac.multigame.R;
import fr.ipac.multigame.utils.ActivityUtils;

public class DragNDropHomeFragment extends Fragment {

    private RelativeLayout mainContainer;
    private TextView start;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drag_n_drop_home, container, false);
        mainContainer = view.findViewById(R.id.fast_tap_container);
        ((TextView) view.findViewById(R.id.drag_n_drop_title)).setText(getString(R.string.drag_n_drop_name));
        view.findViewById(R.id.fast_tap_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.addFragmentToActivity(DragNDropHomeFragment.this,
                        new DragnDropFragment(), mainContainer.getId());
            }
        });
        return view;
    }
}
