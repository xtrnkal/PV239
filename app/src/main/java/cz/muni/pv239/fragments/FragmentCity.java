package cz.muni.pv239.fragments;

//import android.app.Fragment;
//import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import cz.muni.pv239.R;
import cz.muni.pv239.Statistics;
import cz.muni.pv239.Task;
import cz.muni.pv239.enums.BuildingType;
import cz.muni.pv239.enums.Month;

import static cz.muni.pv239.MainActivity.dataManager;

public class FragmentCity extends Fragment {
    private static int SMALL_SIZE_X = 47;
    private static int SMALL_SIZE_Y = 42;


    View view;
    private HashMap<String, Integer> statistics;

    public FragmentCity() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.city_fragment, container, false);
/*
        Bundle b = this.getArguments();
        statistics = (HashMap<String, Integer>) b.getSerializable("statistics");
*/

        //view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        //int viewWidth = view.getMeasuredWidth();
        //int viewHeight = view.getMeasuredHeight();


        //FrameLayout fl = view.findViewById(R.id.frame_huge);
        //ameLayout.LayoutParams a = new FrameLayout.LayoutParams(1,1);
        //fl.setLayoutParams(a);

        //fl.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        //int flWidth = view.getMeasuredWidth();
        //int flHeight = view.getMeasuredHeight();

/*

        ImageView im = new ImageView(getContext());
        im.setImageResource(R.drawable.huge);

        ImageView im2 = new ImageView(getContext());
        im2.setImageResource(R.drawable.huge);

        TableLayout table = new TableLayout(getContext());
        TableRow row1 = new TableRow(getContext());
        table.setShrinkAllColumns(true);
        for (int i = 0; i < 20; i++) {
            ImageView image = new ImageView(getContext());
            image.setImageResource(R.drawable.huge);
            image.setScaleType(ImageView.ScaleType.FIT_END);
             // image.setMaxWidth(viewWidth/64);
            //image.getAdjustViewBounds();
            if (i % 2 == 0) {
                image.setVisibility(View.INVISIBLE);
            }
            row1.addView(image);
        }
        table.addView(row1);
        fl.addView(table);
*/

        //im.setMaxHeight(40);
        //im.setMaxWidth(20);

       // view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
 /*       int width=view.getMeasuredWidth();
        int height=view.getMeasuredHeight();
*/
        //System.out.println("HUGE:" + im.getMaxWidth() + ";" + im.getWidth() + " " + width + " " + height);

        //im.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        //im.getLayoutParams().height = 20;
        //System.out.println(im.getMeasuredHeightAndState() + " ; " + im.getMeasuredWidth());

        //int width = 200;
        //int height = 700;
        /*
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(1,1);
        parms.width = 200;//(getResources().getDisplayMetrics().widthPixels);
        parms.height = 350;//(getResources().getDisplayMetrics().heightPixels);
        parms.gravity = Gravity.BOTTOM;
        parms.bottomMargin = 0;

        im.setLayoutParams(parms);
        System.out.println(parms.height);
*/
        //im.setLayoutParams(parms);

        //im.setPadding(0,0,50,0);

       // table.addView(im,0);






        //fl.addView(table);
/*
        im = new ImageView(getContext());
        im.setImageResource(R.drawable.huge);
        im.setPadding(450,0,0,0);
        fl.addView(im);

        im = new ImageView(getContext());
        im.setImageResource(R.drawable.huge);
        fl.addView(im);

*//*
        fl = view.findViewById(R.id.frame_large);

        im = new ImageView(getContext());
        im.setImageResource(R.drawable.large);
        im.setPadding(0,0,0,0);
        fl.addView(im);*/
/*
        im = new ImageView(getContext());
        im.setImageResource(R.drawable.large);
        im.setPadding(450,0,0,0);
        fl.addView(im);

        im = new ImageView(getContext());
        im.setImageResource(R.drawable.large);
        fl.addView(im);
*/
/*
        fl = view.findViewById(R.id.frame_medium);
        //fl.setForegroundGravity(Gravity.BOTTOM);


        float size = viewWidth / 10;
        int imHeight = (int)((size/47) * 64);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams((int)size/2, imHeight/2);
        parms.width = FrameLayout.LayoutParams.MATCH_PARENT;

        Statistics s = dataManager.getStatistics().get(Month.MARCH + "2019");
        if (s != null) {
            int a = s.getCity().get(BuildingType.MEDIUM);

            a = 5;

            if (a > 10) {
                a = 10;
            }
            int plusNum = 0;
            int minusNum = 0;
            if (a % 2 == 0) {
                plusNum = a/2;
                minusNum = - a/2;
            } else {
                plusNum = a/2 + 1;
                minusNum = - a/2;
            }
            System.out.println(a + " " + minusNum + " " + plusNum);
            for (int i = minusNum; i < plusNum; i++) {
                im = new ImageView(getContext());
                im.setImageResource(R.drawable.medium);
                im.setPadding((int) (i * size), 0, 0, 0);
                im.setLayoutParams(parms);

                //System.out.println(size + " " + (int) (i * size) + " " + viewWidth + " " + flWidth + flHeight + parms.width);
                fl.addView(im);
            }
        }
*/
/*
        fl = view.findViewById(R.id.frame_small);

        im = new ImageView(getContext());
        im.setImageResource(R.drawable.small);
        im.setPadding(-450,0,0,0);
        fl.addView(im);
*/



        createRowOfBuildings(R.id.frame_huge, BuildingType.HUGE, R.drawable.huge, 10);
        createRowOfBuildings(R.id.frame_large, BuildingType.LARGE, R.drawable.large, 15);
        createRowOfBuildings(R.id.frame_medium, BuildingType.MEDIUM, R.drawable.medium, 20);
        createRowOfBuildings(R.id.frame_small, BuildingType.SMALL, R.drawable.small, 20);


        return view;
    }

    private void createRowOfBuildings(int frame, BuildingType buildingType, int type, int maxInRow) {
        FrameLayout fl = view.findViewById(frame);
        //fl.setForegroundGravity(Gravity.BOTTOM);


        //float size = viewWidth / maxInRow;
        //int imHeight = (int)((size/imgWidth) * imgHeight);
        //LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams((int)size/2, imHeight/2);
        //parms.width = FrameLayout.LayoutParams.MATCH_PARENT;

        ImageView im;

        Statistics s = dataManager.getStatistics().get(Month.MARCH + "2019");
        if (s != null) {
            int a = s.getCity().get(buildingType);

            if (a > 10) {
                a = 10;
            }
            int plusNum = 0;
            int minusNum = 0;
            if (a % 2 == 0) {
                plusNum = a/2;
                minusNum = - a/2;
            } else {
                plusNum = a/2 + 1;
                minusNum = - a/2;
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
                // image.setMaxWidth(viewWidth/64);
                //image.getAdjustViewBounds();
                if (i == 0) {
                    image.setVisibility(View.INVISIBLE);
                }
                row.addView(image);
            }
            table.addView(row);
            fl.addView(table);












            //System.out.println(a + " " + minusNum + " " + plusNum);
            /*for (int i = minusNum; i < plusNum; i++) {
                im = new ImageView(getContext());
                im.setImageResource(type);

                im.setPadding((int) (i * size), 0, 0, 0);
                im.setLayoutParams(parms);

                //System.out.println(size + " " + (int) (i * size) + " " + viewWidth + " " + flWidth + flHeight + parms.width);
                fl.addView(im);
            }*/
        }

    }

    private int[] createRandomArray(int size, int count) {
        int[] array = new int[size];
        Random rand = new Random();

        if (size == count) {
            Arrays.fill(array, 1);
            return array;
        }

        System.out.println(array.length + " " + size + " " + count);

        int numOfCycles = 0;

        while (count != 0) {
            int n = rand.nextInt(50);
            if (array[((count * n) % size)] != 1 ){
                array[((count * n) % size)] = 1;
                count--;
            }
            numOfCycles++;
        }

        String tmp = "[";
        for(int i : array) {
            tmp += i + ",";
        }
        tmp += "]";

        System.out.println(tmp + " " + numOfCycles);

        return array;
    }
}
