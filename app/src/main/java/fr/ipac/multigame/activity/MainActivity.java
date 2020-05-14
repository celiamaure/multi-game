package fr.ipac.multigame.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import fr.ipac.multigame.R;
import fr.ipac.multigame.fragment.DragNDropHomeFragment;
import fr.ipac.multigame.fragment.DragnDropFragment;
import fr.ipac.multigame.fragment.TapOrSwipeFragment;
import fr.ipac.multigame.fragment.IpacGameFragment;
import fr.ipac.multigame.fragment.SettingsFragment;
import fr.ipac.multigame.fragment.SwipeFragment;
import fr.ipac.multigame.utils.SwipableViewPager;

public class MainActivity extends AppCompatActivity {

    private SwipableViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        viewPager = findViewById(R.id.main_view_pager);
        tabLayout = findViewById(R.id.main_tab_layout);


        final ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new DragNDropHomeFragment());
        fragments.add(TapOrSwipeFragment.newInstance(false));
        fragments.add(TapOrSwipeFragment.newInstance(true));
        fragments.add(new IpacGameFragment());
        fragments.add(new SettingsFragment());

        viewPager.setPagingEnabled(false);

        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Drag\nNDrop";
                    case 1:
                        return "Swipe";
                    case 2:
                        return "Fast\nTap";
                    case 3:
                        return "Ipac\nGame";
                    case 4:
                        return "Setting";
                    default:
                        return "";
                }
            }
        };

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
