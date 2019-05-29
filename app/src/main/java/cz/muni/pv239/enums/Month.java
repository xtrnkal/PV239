package cz.muni.pv239.enums;

import android.content.Context;

import cz.muni.pv239.R;

public enum Month {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;

    public String getMonthName(Context c) {
        switch (this) {
            case JANUARY:
                return c.getString(R.string.january);
            case FEBRUARY:
                return c.getString(R.string.february);
            case MARCH:
                return c.getString(R.string.march);
            case APRIL:
                return c.getString(R.string.april);
            case MAY:
                return c.getString(R.string.may);
            case JUNE:
                return c.getString(R.string.june);
            case JULY:
                return c.getString(R.string.july);
            case AUGUST:
                return c.getString(R.string.august);
            case SEPTEMBER:
                return c.getString(R.string.september);
            case OCTOBER:
                return c.getString(R.string.october);
            case NOVEMBER:
                return c.getString(R.string.november);
            case DECEMBER:
                return c.getString(R.string.december);
        }
        return "UNKNOWN";
    }
}