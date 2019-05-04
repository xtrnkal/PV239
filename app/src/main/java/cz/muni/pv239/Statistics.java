package cz.muni.pv239;


import java.util.HashMap;

import cz.muni.pv239.enums.BuildingType;
import cz.muni.pv239.enums.Month;

public class Statistics {
    HashMap<String, Integer> values = new HashMap<>();
    HashMap<BuildingType, Integer> city = new HashMap<>();
    int year;
    Month month;

    public Statistics(int year, Month month) {
        this.month = month;
        this.year = year;
        city.put(BuildingType.SMALL, new Integer(0));
        city.put(BuildingType.MEDIUM, new Integer(0));
        city.put(BuildingType.LARGE, new Integer(0));
        city.put(BuildingType.HUGE, new Integer(0));
    }

    /*
     * time is in minutes
     */
    public void addValue (String name, Integer time) {
        Integer val = values.get(name);
        if (val == null) {
            values.put(name, time);
        } else {
            values.put(name, time+val);
            //values.replace(name, time + val);
            //TODO upravit
        }


        if (time < 10) {
            addBuilding(BuildingType.SMALL);
        } else if (time < 30) {
            addBuilding(BuildingType.MEDIUM);
        } else if (time < 60) {
            addBuilding(BuildingType.LARGE);
        } else {
            addBuilding(BuildingType.HUGE);
        }
    }

    public void addBuilding(BuildingType type) {
        Integer count = city.get(type);
        city.put(type, ++count);
    }

    public HashMap<String, Integer> getValues() {
        return values;
    }

    public void setValues(HashMap<String, Integer> values) {
        this.values = values;
    }

    public HashMap<BuildingType, Integer> getCity() {
        return city;
    }

    public void setCity(HashMap<BuildingType, Integer> city) {
        this.city = city;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}