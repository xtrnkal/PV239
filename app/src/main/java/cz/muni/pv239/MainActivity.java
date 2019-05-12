package cz.muni.pv239;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import cz.muni.pv239.enums.BuildingType;
import cz.muni.pv239.enums.Month;
import cz.muni.pv239.fragments.FragmentAnalysis;
import cz.muni.pv239.fragments.FragmentCity;
import cz.muni.pv239.fragments.FragmentSettings;
import cz.muni.pv239.fragments.FragmentTask;


public class MainActivity extends AppCompatActivity {

    public static DataManager dataManager;

    private TabLayout tabLayout;
    private ViewPager viewPager;


    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataManager = new DataManager(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
/*
        Bundle bt = new Bundle();
        Bundle bs = new Bundle();
        bs.putSerializable("statistics", dataManager.getStatistics());
        bt.putSerializable("tasks", tasks);

        FragmentTask fTask = new FragmentTask();
        fTask.setArguments(bt);

        FragmentCity fCity = new FragmentCity();
        fCity.setArguments(bs);

        FragmentCity fAnalysis = new FragmentCity();
        fCity.setArguments(bs);
*/
        adapter.AddFragment(new FragmentTask(), "clock");
        adapter.AddFragment(new FragmentCity(), "Second");
        adapter.AddFragment(new FragmentAnalysis(), "Third");
        adapter.AddFragment(new FragmentSettings(), "Fourth");

        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);

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
