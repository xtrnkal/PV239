package cz.muni.pv239.enums;

import android.content.Context;

import cz.muni.pv239.R;

public enum BuildingType {
    SMALL, MEDIUM, LARGE, HUGE;

    public String getBuildingName(Context c) {
        switch (this) {
            case SMALL:
                return c.getString(R.string.small);
            case MEDIUM:
                return c.getString(R.string.medium);
            case LARGE:
                return c.getString(R.string.large);
            case HUGE:
                return c.getString(R.string.huge);
        }
        return "UNKNOWN";
    }
}
