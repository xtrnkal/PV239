package cz.muni.pv239;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class MyValueFormatter extends ValueFormatter {
    private DecimalFormat mFormat;

    public MyValueFormatter() {
        mFormat = new DecimalFormat("##0.0"); // use one decimal
    }

    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(value) + "%";
    }
}