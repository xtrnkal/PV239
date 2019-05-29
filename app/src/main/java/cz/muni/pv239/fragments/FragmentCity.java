package cz.muni.pv239.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cz.muni.pv239.R;
import cz.muni.pv239.Statistics;
import cz.muni.pv239.enums.BuildingType;

import static cz.muni.pv239.MainActivity.dataManager;

public class FragmentCity extends Fragment {
    View view;

    public FragmentCity() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.city_fragment, container, false);

        createRowOfBuildings(R.id.frame_huge, BuildingType.HUGE, R.drawable.huge, 10);
        createRowOfBuildings(R.id.frame_large, BuildingType.LARGE, R.drawable.large, 15);
        createRowOfBuildings(R.id.frame_medium, BuildingType.MEDIUM, R.drawable.medium, 20);
        createRowOfBuildings(R.id.frame_small, BuildingType.SMALL, R.drawable.small, 20);

        Statistics stats = dataManager.getCurrentStatistics();
        TextView text = view.findViewById(R.id.city_name);
        text.setText(stats.getMonth().getMonthName(getContext()) + " "+ stats.getYear());

        TableLayout tl = view.findViewById(R.id.city_table);
        tl.setShrinkAllColumns(false);

        if (stats != null && !stats.getValues().isEmpty()) {
            int i = 0;
            for (Map.Entry<BuildingType, Integer> entry : stats.getCity().entrySet()) {
                // table rows
                BuildingType key = entry.getKey();
                Integer value = entry.getValue();

                TableRow row = new TableRow(getContext());
                TextView text1 = new TextView(getContext());
                text1.setText(key.getBuildingName(getContext()));
                text1.setTextSize(20);
                text1.setTextColor(getResources().getColor(R.color.colorwhite));

                TextView text2 = new TextView(getContext());
                text2.setText(value.toString());
                text2.setTextSize(20);
                text2.setTextColor(getResources().getColor(R.color.colorwhite));

                row.addView(text1);
                row.addView(text2);

                row.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                row.setGravity(Gravity.CENTER);
                tl.addView(row);
                i++;
            }

        }

        return view;
    }

    private void createRowOfBuildings(int frame, BuildingType buildingType, int type, int maxInRow) {
        FrameLayout fl = view.findViewById(frame);
        Statistics s = dataManager.getCurrentStatistics();
        if (s != null) {
            int a = s.getCity().get(buildingType);

            if (a > maxInRow) {
                a = maxInRow;
            }

            int[] array = createRandomArray(maxInRow, a);

            TableLayout table = new TableLayout(getContext());
            TableRow row = new TableRow(getContext());
            ImageView image;
            table.setShrinkAllColumns(true);
            for (int i : array) {
                image = new ImageView(getContext());
                image.setImageResource(type);
                image.setScaleType(ImageView.ScaleType.FIT_END);
                if (i == 0) {
                    image.setVisibility(View.INVISIBLE);
                }
                row.addView(image);
            }
            table.addView(row);
            fl.addView(table);
        }
    }

    private int[] createRandomArray(int size, int count) {
        int[] array = new int[size];
        Random rand = new Random();

        if (size == count) {
            Arrays.fill(array, 1);
            return array;
        }

        while (count != 0) {
            int n = rand.nextInt(50);
            if (array[((count * n) % size)] != 1 ){
                array[((count * n) % size)] = 1;
                count--;
            }
        }

        return array;
    }
}
