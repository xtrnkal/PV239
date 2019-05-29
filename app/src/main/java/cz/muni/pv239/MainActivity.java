package cz.muni.pv239;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import cz.muni.pv239.fragments.FragmentAnalysis;
import cz.muni.pv239.fragments.FragmentCity;
import cz.muni.pv239.fragments.FragmentSettings;
import cz.muni.pv239.fragments.FragmentTask;


public class MainActivity extends AppCompatActivity {

    public static int BUTTON_HEIGHT;
    public static DataManager dataManager;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BUTTON_HEIGHT = (int) (70 * getResources().getDisplayMetrics().density + 0.5f);

        dataManager = new DataManager(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentTask(), "clock");
        adapter.AddFragment(new FragmentCity(), "Second");
        adapter.AddFragment(new FragmentAnalysis(), "Third");
        adapter.AddFragment(new FragmentSettings(), "Fourth");

        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tablayout_id);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.clockinv);
        tabLayout.getTabAt(0).setText(null);
        tabLayout.getTabAt(1).setIcon(R.drawable.skylineinv);
        tabLayout.getTabAt(1).setText(null);

        tabLayout.getTabAt(2).setIcon(R.drawable.computerinv);
        tabLayout.getTabAt(2).setText(null);

        tabLayout.getTabAt(3).setIcon(R.drawable.gearsinv);
        tabLayout.getTabAt(3).setText(null);
    }
}
