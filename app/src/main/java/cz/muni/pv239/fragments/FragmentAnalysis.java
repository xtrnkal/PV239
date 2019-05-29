package cz.muni.pv239.fragments;

import android.graphics.Rect;
import android.icu.util.MeasureUnit;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Templates;

import cz.muni.pv239.MyValueFormatter;
import cz.muni.pv239.R;
import cz.muni.pv239.Statistics;

import static cz.muni.pv239.MainActivity.dataManager;

public class FragmentAnalysis extends Fragment {
    View view;
    private HashMap<String, Integer> statistics;

    public FragmentAnalysis() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.analysis_fragment, container, false);

        Statistics stats = dataManager.getCurrentStatistics();

        PieChart chart = view.findViewById(R.id.pie_chart);
        chart.setUsePercentValues(true);
        chart.setHoleRadius(40f);
        chart.setTransparentCircleRadius(40f);

        chart.setDescription(null);    // Hide the description
        chart.getLegend().setEnabled(false);
        chart.setEntryLabelTextSize(0f);

        List<PieEntry> values = new ArrayList<>();
        TextView text = view.findViewById(R.id.pie_chart_name);
        text.setText(stats.getMonth().getMonthName(getContext()) + " "+ stats.getYear());

        TableLayout tl = view.findViewById(R.id.pie_chart_table);
        tl.setShrinkAllColumns(false);
        PieDataSet dataSet = new PieDataSet(values, "");

        if (stats != null && !stats.getValues().isEmpty()) {
            int i = 0;
            for (Map.Entry<String, Integer> entry : stats.getValues().entrySet()) {
                //chart stats
                values.add(new PieEntry(entry.getValue(), entry.getKey()));
                //dataSet.addEntry(new PieEntry(entry.getValue(), entry.getKey()));
                //dataSet.getColor(i);

                // table rows
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (value < 1) {
                    value = 20;
                }

                TableRow row = new TableRow(getContext());
                TextView text1 = new TextView(getContext());
                text1.setText(key);
                text1.setTextSize(20);

                TextView text2 = new TextView(getContext());
                text2.setText(value.toString());
                text2.setTextSize(20);

                System.out.println(key + " " + value.toString());

                row.addView(text1);
                row.addView(text2);

                row.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                row.setGravity(Gravity.CENTER);
                tl.addView(row);
                i++;
            }

        }


        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(20);
        dataSet.setValueFormatter(new MyValueFormatter());

        PieData data = new PieData(dataSet);
        //data.setValueTextSize(0f);
        //data.setValueFormatter(new MyValueFormatter());
        chart.setData(data);

/*
        TableRow row = new TableRow(getContext());
        row.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        row.setGravity(Gravity.CENTER);
        TextView text1 = new TextView(getContext());
        text1.setText("AHOJ");
        text1.setTextSize(20);
        TextView text2 = new TextView(getContext());
        text2.setText("30");
        text2.setTextSize(20);

        row.addView(text1);
        row.addView(text2);
        tl.addView(row);

        TableRow row2 = new TableRow(getContext());
        //row2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        row2.setGravity(Gravity.CENTER);
        text1 = new TextView(getContext());
        text1.setText("NEI OS ");
        text1.setTextSize(20);
        text2 = new TextView(getContext());
        text2.setText("456");
        text2.setTextSize(20);



        row2.addView(text1);
        row2.addView(text2);
        tl.addView(row2);
*/


        /*ll.addView(chart);
        return ll;*/
/*
        Bundle b = this.getArguments();
        statistics = (HashMap<String, Integer>) b.getSerializable("statistics");
*/
        return view;
    }
}
