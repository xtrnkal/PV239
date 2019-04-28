package cz.muni.pv239;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    //private AppBarLayout appBarLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentTask(), "clock");
        adapter.AddFragment(new FragmentCity(), "Second");
        adapter.AddFragment(new FragmentAnalysis(), "Third");
        adapter.AddFragment(new FragmentSettings(), "Fourth");
        //adapter.AddFragment(new FragmentTime(), "time");

        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        //appBarLayout = (AppBarLayout) findViewById(R.id.appbar_id);




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
